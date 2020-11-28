/*
    Homework n.1

    Scrivere un programma in linguaggio C che permetta di copiare un numero
    arbitrario di link regolari su una directory di destinazione preesistente.
     
    Homework n.4

    Estendere l'esercizio 'homework n.1' affinche' operi correttamente
    anche nel caso in cui tra le sorgenti e' indicata una directory, copiandone
    il contenuto ricorsivamente. Eventuali link simbolici incontrati dovranno
    essere replicati come tali (dovrà essere creato un link e si dovranno
    preservare tutti permessi di accesso originali dei link e directory).

    Una ipotetica invocazione potrebbe essere la seguente:
    $ homework-4 directory-di-esempio link-semplice.txt path/altra-dir/ "nome con spazi.pdf" directory-destinazione
*/

#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <unistd.h>
#include <libgen.h>
#include <string.h>
#include <dirent.h>

int BUFFERSIZE = 1024;

void checkFile(char *file, char *destination);
void createHardLink(char *source, char *destination);
void createSymLink(char *source, char *destination);
void copyRecursively(char *source, char *destination);

int main(int args, char *argv[]) {
    if (args < 3) {
        fprintf(stderr, "Usage: %s <file1 file2 ... file3> <destination_directory>\n", argv[0]);
        exit(1);
    }

    char* destination = argv[args - 1];
    struct stat destination_stats;
    if ((stat(destination, &destination_stats)) == -1) {
        perror("stat()");
        exit(1);
    }

    if (!S_ISDIR(destination_stats.st_mode)) {
        fprintf(stderr, "%s is not a directory!\n", destination);
        exit(1);
    }

    for (int i=1; i<args-1; i++) {
        char *file = argv[i];

        checkFile(file, destination);
    }
}

void checkFile(char *file, char *destination) {
    struct stat file_stats;
    if ((lstat(file, &file_stats)) == -1) {
        perror("lstat()");
        exit(1);
    }

    if (S_ISREG(file_stats.st_mode)) {
        createHardLink(file, destination);
    } else if (S_ISLNK(file_stats.st_mode)) {
        createSymLink(file, destination);
    } else if (S_ISDIR(file_stats.st_mode)) {
        copyRecursively(file, destination);
    }
}

void createHardLink(char *source, char *destination) {
    char copied_link_path[BUFFERSIZE];
    strcpy(copied_link_path, destination);
    int len = strlen(copied_link_path);
    if (strcmp(copied_link_path + len - 1, "/") != 0) {
        strcat(copied_link_path + len, "/");
        len++;
    }
    strcpy(copied_link_path + len, basename(source));

    if ((link(source, copied_link_path)) == -1) {
        perror("link()");
        exit(1);
    }

    printf("%s copied\n", basename(source));
}

void createSymLink(char *source, char *destination) {
    int BUFFERSIZE = 1024;
    char copied_link_path[BUFFERSIZE];
    strcpy(copied_link_path, destination);
    int len = strlen(copied_link_path);
    if (strcmp(copied_link_path + len - 1, "/") != 0) {
        strcat(copied_link_path + len, "/");
        len++;
    }
    strcpy(copied_link_path + len, basename(source));

    // Path pointed by the link
    char link_path[BUFFERSIZE];
    int size;
    if ((size = readlink(source, link_path, BUFFERSIZE)) == -1) {
        perror("readlink()");
        exit(1);
    }
    link_path[size] = '\0';

    if ((symlink(link_path, copied_link_path)) == -1) {
        perror("symlink()");
        exit(1);
    }

    printf("%s recreated\n", basename(source));
}

void copyRecursively(char *source, char *destination) {
    DIR *directory;
    struct dirent *entry;

    struct stat destination_stats;
    if ((stat(destination, &destination_stats)) == -1) {
        perror("stat()");
        exit(1);
    }

    char newDirectory[BUFFERSIZE];
    strcpy(newDirectory, destination);
    int len = strlen(newDirectory);
    if (strcmp(newDirectory + len - 1, "/") != 0) {
        strcat(newDirectory + len, "/");
        len++;
    }
    strcat(newDirectory + len, basename(source));

    if ((mkdir(newDirectory, destination_stats.st_mode & 0777)) == -1) {
        perror("mkdir()");
        exit(1);
    }

    if ((directory = opendir(source)) == NULL) {
        perror("opendir()");
        exit(1);
    }

    while ((entry = readdir(directory)) != NULL) {
        if (strcmp(entry->d_name,".") == 0  || strcmp(entry->d_name,"..") == 0) continue;

        char file_source_path[BUFFERSIZE];
        strcpy(file_source_path, source);
        int len = strlen(file_source_path);
        if (strcmp(file_source_path + len - 1, "/") != 0) {
            strcat(file_source_path + len, "/");
            len++;
        }
        strcat(file_source_path, entry->d_name);

        printf(file_source_path);
        printf("\n");

        checkFile(file_source_path, newDirectory);
    }
}