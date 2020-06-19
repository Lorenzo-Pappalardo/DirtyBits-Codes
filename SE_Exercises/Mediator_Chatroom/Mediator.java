package Mediator_Chatroom;

public interface Mediator {
    public void send(String message, Colleague destination);
}