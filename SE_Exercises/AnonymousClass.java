interface Message {
    public String getMessage();
}

public class AnonymousClass {
    public static void main(String[] args) {
        System.out.println(new Message() {
            public String getMessage() {
                return "Hello World!\n";
            }
        }.getMessage());
    }
}