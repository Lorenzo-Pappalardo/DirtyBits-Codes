/*
    Homework n.3

    Usando la possibilita' di mappare file in memoria, creare un programma che
    possa manipolare un file arbitrariamente grande costituito da una sequenza
    di record lunghi N byte.
    La manipolazione consiste nel riordinare, tramite un algoritmo di
   ordinamento a scelta, il record considerando il contenuto dello stesso come
   chiave: ovvero, supponendo N=5, il record [4a a4 91 f0 01] precede [4a ff 10
   01 a3]. La sintassi da supportare e' la seguente: $ homework-3 <N> <pathname
   del file>

    E' possibile testare il programma sul file 'esempio.txt' prodotto dal
   seguente comando, utilizzando il parametro N=33: $ ( for I in `seq 1000`; do
   echo $I | md5sum | cut -d' ' -f1 ; done ) > esempio.txt

    Su tale file, l'output atteso e' il seguente:
     $ homework-3 33 esempio.txt
     $ head -n5 esempio.txt
        000b64c5d808b7ae98718d6a191325b7
        0116a06b764c420b8464f2068f2441c8
        015b269d0f41db606bd2c724fb66545a
        01b2f7c1a89cfe5fe8c89fa0771f0fde
        01cdb6561bfb2fa34e4f870c90589125
     $ tail -n5 esempio.txt
        ff7345a22bc3605271ba122677d31cae
        ff7f2c85af133d62c53b36a83edf0fd5
        ffbee273c7bb76bb2d279aa9f36a43c5
        ffbfc1313c9c855a32f98d7c4374aabd
        ffd7e3b3836978b43da5378055843c67
*/

#include </usr/include/fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
  if (argc < 3) {
    fprintf(stderr, "Usage: %s <N> <path_to_file>\n", argv[0]);
    exit(1);
  }

  const int N = atoi(argv[1]);
  const char *path_to_file = argv[2];

  int file_descriptor;
  if ((file_descriptor = open(path_to_file, O_RDWR)) == -1) {
    perror(path_to_file);
    exit(2);
  }

  struct stat file_info;
  if ((fstat(file_descriptor, &file_info)) == -1) {
    perror(path_to_file);
    exit(3);
  }

  void *mapped_file;
  if ((mapped_file = mmap(NULL, file_info.st_size, PROT_READ | PROT_WRITE,
                          MAP_SHARED, file_descriptor, 0)) == (void *)-1) {
    perror("mmap()");
    exit(4);
  }

  if ((close(file_descriptor)) == -1) {
    perror("close()");
    exit(5);
  }
}