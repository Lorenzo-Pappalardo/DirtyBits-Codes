#include <dirent.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <wait.h>

// Constants
#define MAX_PATH_LEN 512
#define SEMNUM 3
// Semaphores
#define S_SCANNER 0
#define S_STATER 1
#define S_TERMINAL 2

int WAIT(int sem_des, int num_semaforo) {
  struct sembuf operazioni[1] = {{num_semaforo, -1, 0}};
  return semop(sem_des, operazioni, 1);
}

int SIGNAL(int sem_des, int num_semaforo) {
  struct sembuf operazioni[1] = {{num_semaforo, +1, 0}};
  return semop(sem_des, operazioni, 1);
}

typedef struct shm {
  int folderIndex;
  char path[MAX_PATH_LEN];
  int dim;
} shm_t;

int scanner(shm_t *shm, int semDesc, int id, char *directory);
int stater(shm_t *shm, int semDesc, int foldersCount);
int terminal(shm_t *shm, int semDesc, char **argv);

int main(int argc, char **argv) {
  if (argc < 2) {
    fprintf(stderr, "Usage: %s <directory> <...>\n", argv[0]);
    return 1;
  }

  int shmDesc, semDesc;

  shmDesc = shmget(IPC_PRIVATE, sizeof(shm_t), IPC_CREAT | IPC_EXCL | 0600);
  if (shmDesc == -1) {
    fprintf(stderr, "Failed to get shared memory descriptor\n");
    exit(1);
  }

  shm_t *shmZone = (shm_t *)shmat(shmDesc, NULL, 0);

  if (shmZone == (void *)-1) {
    fprintf(stderr, "Failed to attach shared memory\n");
    return 1;
  }

  semDesc = semget(IPC_PRIVATE, SEMNUM, IPC_CREAT | IPC_EXCL | 0600);
  if (semDesc == -1) {
    fprintf(stderr, "Failed to get semaphores array descriptor\n");
    exit(1);
  }

  // Initializing semaphores
  if (semctl(semDesc, S_SCANNER, SETVAL, 1) == -1) {
    fprintf(stderr, "Failed to set semaphore n.%d\n", S_SCANNER);
  }
  if (semctl(semDesc, S_STATER, SETVAL, 0) == -1) {
    fprintf(stderr, "Failed to set semaphore n.%d\n", S_STATER);
  }
  if (semctl(semDesc, S_TERMINAL, SETVAL, 0) == -1) {
    fprintf(stderr, "Failed to set semaphore n.%d\n", S_TERMINAL);
  }

  pid_t pid;
  for (int i = 1; i < argc; i++) {
    pid = fork();
    if (pid == 0)
      return scanner(shmZone, semDesc, i, argv[i]);
  }

  pid = fork();
  if (pid == 0)
    return stater(shmZone, semDesc, argc - 1);

  int returnCode = terminal(shmZone, semDesc, argv);

  // Removing semahores array
  if (semctl(semDesc, 0, IPC_RMID, 0) == -1) {
    fprintf(stderr, "Failed to remove semaphores array\n");
    return 1;
  }

  // Removing shared memory
  if (shmctl(shmDesc, IPC_RMID, NULL) != 0) {
    fprintf(stderr, "Failed to remove shared memory\n");
    return 1;
  }

  return returnCode;
}

int scanner(shm_t *shm, int semDesc, int id, char *directory) {
  DIR *dir = opendir(directory);
  if (dir == NULL) {
    fprintf(stderr, "Scanner n.%d failed to open its directory\n", id);
    return 1;
  }

  struct dirent *item;
  struct stat itemStats;
  char fullPath[MAX_PATH_LEN];

  while ((item = readdir(dir)) != NULL) {
    char *filename = item->d_name;
    if (!strcmp(filename, ".") || !strcmp(filename, ".."))
      continue;

    // Getting full path
    strcpy(fullPath, directory);
    strcat(fullPath, filename);

    if (lstat(fullPath, &itemStats) != 0) {
      fprintf(stderr, "Failed to get item stats\n");
      return 1;
    }

    if (S_ISREG(itemStats.st_mode)) {
      WAIT(semDesc, S_SCANNER);

      shm->folderIndex = id;
      strcpy(shm->path, fullPath);

      SIGNAL(semDesc, S_STATER);
    } else if (S_ISDIR(itemStats.st_mode))
      scanner(shm, semDesc, id, fullPath);
  }

  // Closing Stater
  WAIT(semDesc, S_SCANNER);
  shm->folderIndex = -1;
  SIGNAL(semDesc, S_STATER);

  // Closing directory
  if (closedir(dir)) {
    fprintf(stderr, "Failed to close dir\n");
    return 1;
  }

  return 0;
}

int stater(shm_t *shm, int semDesc, int foldersCount) {
  int counters[foldersCount + 1];
  for (int i = 1; i <= foldersCount; i++)
    counters[i] = 0;

  while (1) {
    WAIT(semDesc, S_STATER);

    struct stat itemStats;
    if (lstat(shm->path, &itemStats) != 0) {
      fprintf(stderr, "Failed to get item stats\n");
      return 1;
    }

    if (shm->folderIndex == -1)
      break;
    counters[shm->folderIndex] += itemStats.st_size;

    SIGNAL(semDesc, S_SCANNER);
  }

  for (int i=1; i <= foldersCount; i++) {
    shm->folderIndex = i;
    shm->dim = counters[i];

    SIGNAL(semDesc, S_TERMINAL);
    WAIT(semDesc, S_STATER);
  }

  shm->folderIndex = -1;
  SIGNAL(semDesc, S_TERMINAL);

  return 0;
}

int terminal(shm_t *shm, int semDesc, char **argv) {
  while(1) {
    WAIT(semDesc, S_TERMINAL);

    if (shm->folderIndex == -1) break;

    printf("%d      %s\n", shm->dim, argv[shm->folderIndex]);
    SIGNAL(semDesc, S_STATER);
  }
  return 0;
}