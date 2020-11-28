package Functional_Programming_Exercises;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Ex2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.println("Insert words, write stop to stop inserting words xD");
        while (true) {
            String word = in.nextLine();
            if (word.equalsIgnoreCase("stop"))
                break;
            list.add(word);
        }
        in.close();

        System.out.println(getInitialsString(list));
    }

    public static String getInitialsString(List<String> list) {
        return list.stream().reduce("", (accum, w) -> accum += w.substring(0, 1));
    }
}