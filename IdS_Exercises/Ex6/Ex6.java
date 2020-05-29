/*
    6) Isolare l'esplorazione del file system in una classe apposita FileExplorer,
    avente come attributo d'istanza proprio il percorso della directory da esplorare.
    Delegare a questo oggetto tutto ciò che adesso è eseguito dal main tramite metodi statici
    (caricamento file, esplorazione cartelle, stampa del contenuto ecc...).
    Fare in modo che l'unica responsabilità del main sia di creare un FileExplorer e di farne uso.
*/

package Ex6;

public class Ex6 {
    public static void main(String args[]) {
        if (args.length == 0) {
            System.err.println("You have to pass a path");
            System.exit(1);
        }

        if (args.length > 1) {
            System.err.println("Passed too many argumets, only one path can be processed");
            System.exit(2);
        }

        new FileExplorer(args[0]);
    }
}