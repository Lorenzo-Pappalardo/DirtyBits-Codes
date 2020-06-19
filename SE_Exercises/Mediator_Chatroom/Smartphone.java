package Mediator_Chatroom;

public class Smartphone extends Colleague {
    Smartphone(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void receive(String message) {
        System.out.println("Smartphone has received: " + message);
    }
}