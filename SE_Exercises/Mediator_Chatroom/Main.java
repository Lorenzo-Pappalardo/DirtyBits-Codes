package Mediator_Chatroom;

public class Main {
    public static void main(String[] args) {
        ConcreteMediator concreteMediator = new ConcreteMediator();

        Colleague desktop = new Desktop(concreteMediator);
        Colleague smartphone = new Smartphone(concreteMediator);

        concreteMediator.addColleague(desktop);
        concreteMediator.addColleague(smartphone);

        desktop.send("Hello from Desktop");
        smartphone.send("Hello from Smartphone");
    }
}