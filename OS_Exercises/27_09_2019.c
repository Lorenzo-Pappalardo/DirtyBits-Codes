#include <dirent.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <wait.h>

#define EXIT -1
#define FILES_NUM 0
#define TOTAL_SIZE 1
#define SEARCH_CHAR 2

typedef struct {
  long mtype;
  int command;
  char dirPath[1024];
  int result;
} msg;

int getCommand();

int child(int msgDesc);

int sendFilesNum(int msgDesc, DIR *dirStream);

int sendTotalSize(int msgDesc, DIR *dirStream);

int sendCharOccurrencies();

int main(int argc, char **argv) {
  if (argc < 2) {
    fprintf(stderr, "Usage: %s <directory> <another-directory> <...>\n",
            argv[0]);
    return -1;
  }

  key_t key = 2019;

  int msgDesc = msgget(key, IPC_CREAT | IPC_EXCL | 0600);
  if (msgDesc == -1) {
    fprintf(stderr, "Failed to create a messages queue\n");
    return -1;
  }

  printf("Possible commands:\n");
  printf("files-num <directory-number>\n");
  printf("total-size <directory-number>\n");
  printf("search-char <directory-number> <file-name> <character>\n");
  printf("exit\n\n");

  while (1) {
    int command = getCommand();

    if (command == EXIT)
      break;

    int dirNum;
    scanf("%d", &dirNum);

    if ((command == FILES_NUM) || (command == TOTAL_SIZE)) {
      msg instruction;
      instruction.command = command;
      strcpy(instruction.dirPath, argv[dirNum]);

      if (msgsnd(msgDesc, &instruction, sizeof(instruction) - sizeof(long),
                 IPC_NOWAIT) == -1) {
        fprintf(stderr, "Unable to send message from Parent\n");
        break;
      }

      pid_t pid;
      if (pid == 0)
        child(msgDesc);

      int originalCommand = instruction.command;

      if (msgrcv(msgDesc, &instruction, sizeof(instruction) - sizeof(long), 0,
                 0) == -1) {
        fprintf(stderr, "Failed to receive message from child\n");
        break;
      }

      if (originalCommand == FILES_NUM)
        printf("\t%d files\n", instruction.result);
      else
        printf("\t%d bytes\n", instruction.result);
    } else {
      char *filename;
      scanf("%s", filename);

      char character;
      scanf("%c", &character);

      printf("%s\n", filename);
      printf("%c\n", character);
    }
  }

  // Removing messages queue
  if (msgctl(msgDesc, IPC_RMID, NULL) == -1) {
    fprintf(stderr, "Failed to remove messages queue\n");
    return -1;
  }

  return 0;
}

int getCommand() {
  printf("Shell > ");

  char *command;
  scanf("%s", command);

  if (!strcmp(command, "exit"))
    return EXIT;

  if (!strcmp(command, "files-num"))
    return FILES_NUM;

  if (!strcmp(command, "total-size"))
    return TOTAL_SIZE;

  if (!strcmp(command, "search-char"))
    return SEARCH_CHAR;

  printf("Unknown command, please try again\n\n");

  return getCommand();
}

int child(int msgDesc) {
  msg instruction;
  if (msgrcv(msgDesc, &instruction, sizeof(instruction) - sizeof(long), 0, 0) ==
      -1) {
    fprintf(stderr, "Failed to receive message\n");
    return -1;
  }

  struct stat dirStats;
  stat(instruction.dirPath, &dirStats);

  if (!S_ISDIR(dirStats.st_mode)) {
    fprintf(stderr, "Not a directory!\n");
    return -1;
  }

  DIR *dirStream = opendir(instruction.dirPath);
  if (dirStream == NULL) {
    fprintf(stderr, "Failed to open directory\n");
    return -1;
  }

  if (instruction.command == FILES_NUM)
    return sendFilesNum(msgDesc, dirStream);

  if (instruction.command == TOTAL_SIZE)
    return sendTotalSize(msgDesc, dirStream);

  return sendCharOccurrencies();
}

int sendFilesNum(int msgDesc, DIR *dirStream) {
  int count = 0;

  struct dirent *file;
  while ((file = readdir(dirStream)) != NULL) {
    char *filename;
    strcpy(filename, file->d_name);

    struct stat fileStats;
    lstat(filename, &fileStats);

    if (S_ISREG(fileStats.st_mode))
      count++;
  }

  msg result;
  result.result = count;

  if (msgsnd(msgDesc, &result, sizeof(result) - sizeof(long), 0) == -1) {
    fprintf(stderr, "Failed to send result\n");
    return -1;
  }
}

int sendTotalSize(int msgDesc, DIR *dirStream) {
  int count = 0;

  struct dirent *file;
  while ((file = readdir(dirStream)) != NULL) {
    char *filename;
    strcpy(filename, file->d_name);

    struct stat fileStats;
    lstat(filename, &fileStats);

    if (S_ISREG(fileStats.st_mode))
      count += fileStats.st_size;
  }

  msg result;
  result.result = count;

  if (msgsnd(msgDesc, &result, sizeof(result) - sizeof(long), 0) == -1) {
    fprintf(stderr, "Failed to send result\n");
    return -1;
  }
}

int sendCharOccurrencies() {}