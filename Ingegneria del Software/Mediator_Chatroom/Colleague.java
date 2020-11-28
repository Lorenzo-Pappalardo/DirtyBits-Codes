package Mediator_Chatroom;

public abstract class Colleague {
    private Mediator mediator;

    protected Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    public abstract void receive(String message);
}