/*
    Homework n.2

    Estendere l'esempio 'move.c' visto a lezione per supportare i 2 casi
   speciali:
    - spostamento cross-filesystem: individuato tale caso, il file deve essere
      spostato utilizzando la strategia "copia & cancella";
    - spostamento di un link simbolico: individuato tale caso, il link simbolico
      deve essere ricreato a destinazione con lo stesso contenuto (ovvero il
   percorso che denota l'oggetto referenziato); notate come tale spostamento
   potrebbe rendere il nuovo link simbolico non effettivamente valido.

    La sintassi da supportare e' la seguente:
     $ homework-2 <pathname sorgente> <pathname destinazione>
*/

#include </usr/include/fcntl.h>
#include <libgen.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

static const int BUFFERSIZE = 1024;

int main(int argc, char *argv[]) {
  struct stat file_info;
  struct stat dest_info;
  char *filename = basename(argv[1]);
  char dest[BUFFERSIZE];

  if (argc != 3) {
    fprintf(stderr, "Usage: %s <file> <destination_path>\n", argv[0]);
    exit(1);
  }

  if ((lstat(argv[1], &file_info)) == -1) {
    perror(argv[1]);
    exit(2);
  }
  if ((stat(argv[2], &dest_info)) == -1) {
    perror(argv[2]);
    exit(3);
  }

  if (!S_ISDIR(dest_info.st_mode)) {
    fprintf(stderr, "%s is not a directory!", argv[2]);
    exit(4);
  }

  // constructing pathname
  strcpy(dest, argv[2]);
  int length = strlen(dest);
  strcpy(dest + length, filename);

  if (S_ISREG(file_info.st_mode)) {
    if (file_info.st_dev == dest_info.st_dev) {
      if ((link(argv[1], dest)) == -1) {
        perror(dest);
        exit(5);
      }

      if ((unlink(argv[1])) == -1) {
        perror(argv[1]);
        exit(6);
      }
      printf("%s moved\n", filename);
    } else {
      printf("Cross-filesystem operation! \"Copy and delete\" mode...\n");

      int source_fd, dest_fd;
      char buffer[BUFFERSIZE];
      int bytes_read;

      if ((source_fd = open(argv[1], 0400)) == -1) {
        perror(argv[1]);
        exit(5);
      }

      if ((dest_fd = open(dest, O_RDWR | O_TRUNC | O_CREAT,
                          file_info.st_mode & 0777)) == -1) {
        perror(dest);
        exit(5);
      }

      do {
        if ((bytes_read = read(source_fd, buffer, BUFFERSIZE)) == -1) {
          perror(argv[1]);
          exit(6);
        }

        if ((write(dest_fd, buffer, bytes_read)) == -1) {
          perror(dest);
          exit(7);
        }
      } while (bytes_read == BUFFERSIZE);

      if (unlink(argv[1])) {
        perror(argv[1]);
        exit(8);
      }

      printf("%s copied\n", argv[1]);
    }
  } else if (S_ISLNK(file_info.st_mode)) {
    printf("%s is a link!\n", argv[1]);
    char buffer[BUFFERSIZE];
    int size;

    if ((size = readlink(argv[1], buffer, BUFFERSIZE)) == -1) {
      perror(argv[1]);
      exit(5);
    }
    buffer[size] = '\0';

    if ((symlink(buffer, dest)) == -1) {
      perror(argv[2]);
      exit(6);
    }

    unlink(argv[1]);
    printf("%s recreated\n", argv[1]);
  }
}