/*
    4) Scrivere un metodo che inizializzi e ritorni una lista di liste di String.
*/

import java.util.List;
import java.util.ArrayList;

public class Ex4 {
    private static List<List<String>> listOfListsOfStrings;

    public static void main(String args[]) {
        listOfListsOfStrings = getList();

        for (List<String> item : listOfListsOfStrings) {
            System.out.println(item);
        }
    }

    private static List<List<String>> getList() {
        List<String> slist1 = new ArrayList<>(4);
        slist1.add("Una mattina mi son' svegliato");
        slist1.add("O bella ciao, bella ciao, bella ciao ciao ciao");
        slist1.add("Una mattina mi son' svegliato");
        slist1.add("E ho trovato l'invasor");

        List<String> slist2 = new ArrayList<>(4);
        slist2.add("O partigiano portami via");
        slist2.add("O bella ciao, bella ciao, bella ciao ciao ciao");
        slist2.add("O partigiano portami via");
        slist2.add("Ché mi sento di morir");

        List<String> slist3 = new ArrayList<>(4);
        slist3.add("E se io muoio da partigiano");
        slist3.add("O bella ciao, bella ciao, bella ciao ciao ciao");
        slist3.add("E se io muoio da partigiano");
        slist3.add("Tu mi devi seppellir");

        List<List<String>> listOfListsOfStrings = new ArrayList<>();

        listOfListsOfStrings.add(slist1);
        listOfListsOfStrings.add(slist2);
        listOfListsOfStrings.add(slist3);

        return listOfListsOfStrings;
    }
}