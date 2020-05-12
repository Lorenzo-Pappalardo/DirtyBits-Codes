/*
    Homework n.1

    Scrivere un programma in linguaggio C che permetta di copiare un numero
    arbitrario di file regolari su una directory di destinazione preesistente.

    Il programma dovra' accettare una sintassi del tipo:
     $ homework-1 file1.txt path/file2.txt "nome con spazi.pdf"
   directory-destinazione
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFFERSIZE 1024

int main(int argc, char **argv) {
  if (argc < 3) {
    fprintf(stderr, "Usage: %s (<file1> <file2>...) <destination_directory>",
            argv[0]);
    exit(1);
  }

  char destination_pathname[BUFFERSIZE];
  strcpy(destination_pathname, argv[argc - 1]);
  int size = strlen(destination_pathname);

  for (int i = 1; i < argc - 1; i++) {
    int fd;
    if ((fd = open(argv[i], 0400)) == -1) {
      perror(argv[i]);
      exit(2);
    }

    char *name = basename(argv[i]);
    strncpy(destination_pathname + size, name, BUFFERSIZE - size);

    int newfd;
    if ((newfd = creat(destination_pathname, 0660)) == -1) {
      perror(destination_pathname);
      exit(3);
    }

    char buffer[BUFFERSIZE];
    int characters_read;
    while((characters_read = read(fd, buffer, BUFFERSIZE)) != 0) {
      write(newfd, buffer, characters_read);
    }
  }
}