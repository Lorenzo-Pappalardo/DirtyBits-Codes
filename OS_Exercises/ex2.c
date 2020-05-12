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

#define BUFFERSIZE 1024