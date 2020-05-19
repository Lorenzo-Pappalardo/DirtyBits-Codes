/*
    5) Fare in modo che il metodo che stampa il contenuto di un file non ignori le cartelle.
    In questi casi, va ricorsivamente applicata la stessa procedura usata per la cartella principale,
    stampando i file e a sua volta controllando se ci sono sotto cartelle.
    Aggiungere la gestione esplicita del caso in cui una cartella sia vuota,
    in modo che venga stampato un messaggio d'avvertimento a schermo.
*/

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;

public class Ex5 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("You have to pass a path!");
            System.exit(1);
        }

        File file = new File(args[0]);

        printPathRecursively(file, 0);
    }

    private static void printPathRecursively(File f, int indentation) {
        if (f.isDirectory()) {
            indent(indentation);
            if (f.listFiles().length == 0)
                System.out.println(f.getName() + " is an empty directory");
            else {
                System.out.println(f.getName() + " is a directory, showing contents below...");
                for (File tmp : f.listFiles())
                    printPathRecursively(tmp, indentation + 1);
            }
        } else {
            indent(indentation);
            System.out.println(f.getName() + " is a file, showing content below...");

            printFile(f, indentation + 1);
        }
    }

    private static void printFile(File f, int indentation) {
        try {
            LineNumberReader reader = new LineNumberReader(new FileReader(f));

            indent(indentation);
            System.out.println("********** FILE START **********");

            String lineRead;
            while ((lineRead = reader.readLine()) != null) {
                indent(indentation);
                System.out.println(lineRead);
            }

            indent(indentation);
            System.out.println("********** FILE END **********");

            reader.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void indent(int indentation) {
        for (int i = 0; i < indentation; i++) {
            System.out.print('\t');
        }
    }
}