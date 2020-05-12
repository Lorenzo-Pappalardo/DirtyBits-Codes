/*
    1) Scrivere un metodo che data una lista di double ed una lista di interi
    riempa la seconda lista con gli elementi della prima lista,
    facendo gli opportuni cast
*/

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class Ex1 {
    private static List<Integer> ilist;
    private static List<Double> dlist;

    public static void main(String args[]) {
        ilist = new LinkedList<>();
        dlist = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            ilist.add(random.nextInt(100));
            dlist.add(random.nextDouble() * random.nextInt(100));
        }

        System.out.println("Before...");
        System.out.println("ilist: " + ilist);
        System.out.println("dlist: " + dlist);

        toIlist();

        System.out.println('\n');
        System.out.println("After...");
        System.out.println("ilist: " + ilist);
        System.out.println("dlist: " + dlist);
    }

    private static void toIlist() {
        for (Double item : dlist) {
            ilist.add(item.intValue());
        }
    }
}