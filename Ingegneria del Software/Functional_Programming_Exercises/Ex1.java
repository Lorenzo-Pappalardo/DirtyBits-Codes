package Functional_Programming_Exercises;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Ex1 {
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

        System.out.println("Insert the beginning substring");
        String substringToLookFor = in.nextLine();
        in.close();

        System.out.println(contains(list, substringToLookFor));
    }

    public static List<String> contains(List<String> original, String substring) {
        return original.stream().filter(w -> w.startsWith(substring)).collect(Collectors.toList());
    }
}