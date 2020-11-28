package State_Book_Reader;

import java.util.List;

/** Concrete State */
public class SingleWord implements Display {
    public void write(List<String> text) {
        text.stream().forEach(w -> printWord(w));
    }

    private void printWord(String word) {
        System.out.print(word);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            System.err.println(ie);
        }
        for (int i = 0; i < word.length(); i++)
            System.out.print('\b');
    }
}