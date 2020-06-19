package Mediator_Chatroom;

public class Desktop extends Colleague {
    public Desktop(Mediator m) {
        super(m);
    }

    @Override
    public void receive(String message) {
        System.out.println("Desktop has received: " + message);
    }
}