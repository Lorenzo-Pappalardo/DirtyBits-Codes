package State_Book_Reader;

import java.util.List;

/** Concrete State */
public class Paragraph implements Display {
    private final int wordsPerLine = 15;
    private int wordsCounter = 0;

    public void write(List<String> text) {
        text.stream().forEach(w -> printParagraph(w));
    }

    private void printParagraph(String word) {
        System.out.print(word + " ");
        wordsCounter++;
        if (wordsCounter == wordsPerLine) {
            System.out.println();
            wordsCounter = 0;
        }
    }
}