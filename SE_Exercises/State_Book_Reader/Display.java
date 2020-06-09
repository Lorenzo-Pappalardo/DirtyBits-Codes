package State_Book_Reader;

import java.util.List;

/** State */
public interface Display {
    /**
     * Writes contents on screen
     * @param text : List<String>
     */
    public void write(List<String> text);
}