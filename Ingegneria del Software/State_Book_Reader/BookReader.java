package State_Book_Reader;

public class BookReader {
    public static void main(String[] args) throws Exception {
        Book book = new Book();
        book.printContents();
        System.out.println();
        book.switchMode("SingleLine");
        book.printContents();
        System.out.println();
        book.switchMode("Paragraph");
        book.printContents();
    }
}