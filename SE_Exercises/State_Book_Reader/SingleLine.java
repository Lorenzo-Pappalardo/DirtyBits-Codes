package State_Book_Reader;

import java.util.List;

/** Concrete State */
public class SingleLine implements Display {
    private final int wordsPerLine = 15;
    private int wordsCounter = 0;
    private int lineLength = 0;

    public void write(List<String> text) {
        text.stream().forEach(w -> printLine(w));
    }

    private void printLine(String word) {
        System.out.print(word + ' ');
        wordsCounter++;
        lineLength += word.length() + 1;
        if (wordsCounter == wordsPerLine) {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException ie) {
                System.err.println(ie);
            }
            for (int i = 0; i < lineLength; i++) {
                System.out.print('\b');
            }
            wordsCounter = 0;
            lineLength = 0;
        }
    }
}