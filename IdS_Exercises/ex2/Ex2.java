/*
    2) Scrivere un metodo che data una lista di double ed una lista di String
    riempa la seconda lista con gli elementi della prima lista trasformandoli,
    appunto, in stringhe.
*/

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class Ex2 {
    private static List<Double> dlist;
    private static List<String> slist;

    public static void main(String args[]) {
        dlist = new LinkedList<>();
        slist = new LinkedList<>();

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            dlist.add(random.nextDouble() * random.nextInt(100));
        }
        slist.add("Ciao, ");
        slist.add("questo ");
        slist.add("è ");
        slist.add("un ");
        slist.add("test ");
        slist.add("--> ");

        System.out.println("Before...");
        System.out.println("dlist: " + dlist);
        System.out.println("slist: " + slist);

        toSlist();

        System.out.println('\n');
        System.out.println("After...");
        System.out.println("dlist: " + dlist);
        System.out.println("slist: " + slist);
    }

    private static void toSlist() {
        for (Double item : dlist) {
            slist.add(item.toString());
        }
    }
}