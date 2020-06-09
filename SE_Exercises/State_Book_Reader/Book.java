package State_Book_Reader;

import java.util.Arrays;
import java.util.List;

/** Context */
public class Book {
    private final String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam magna leo, hendrerit in justo sed, commodo porta erat. Phasellus a lectus imperdiet, lacinia odio vitae, mollis tellus. Duis vitae sodales enim, nec rutrum libero.\n";
    private Display displayMode = new SingleWord();

    /** Prints book's contents */
    public void printContents() {
        final List<String> textList = Arrays.asList(text.split(" "));
        displayMode.write(textList);
    }

    /**
     * Switches between display modes
     * 
     * @param mode : Display
     */
    public void switchMode(String mode) {
        if (mode.equalsIgnoreCase("SingleWord"))
            displayMode = new SingleWord();
        else if (mode.equalsIgnoreCase("SingleLine"))
            displayMode = new SingleLine();
        else if (mode.equalsIgnoreCase("Paragraph"))
            displayMode = new Paragraph();
    }
}