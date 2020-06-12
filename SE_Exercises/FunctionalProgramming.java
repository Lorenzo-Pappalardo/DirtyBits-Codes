import java.util.Scanner;

interface Message {
    public String getMessage(String name);
}

class ActualMessage {
    private String name;

    public ActualMessage(String name) {
        this.name = name;
    }

    public String getActualMessage(Message message) {
        return message.getMessage(name);
    }
}

public class FunctionalProgramming {
    public static void main(String[] args) {
        System.out.println("Enter name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        scanner.close();
        // Anonymous class
        System.out.println(new Message() {
            public String getMessage(String name) {
                return "Hello " + name + '!';
            }
        }.getMessage(name));
        // Oppure
        System.out.println(new ActualMessage(name).getActualMessage(message -> "Hello " + message + '!'));
    }
}