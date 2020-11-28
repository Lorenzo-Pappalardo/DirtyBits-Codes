package Mediator_Chatroom;

import java.util.ArrayList;
import java.util.List;

public class ConcreteMediator implements Mediator {
    private List<Colleague> colleagues;

    public ConcreteMediator() {
        colleagues = new ArrayList<>();
    }

    public void addColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    public void send(String message, Colleague sourceColleague) {
        for (Colleague colleague : colleagues) {
            if (sourceColleague != colleague)
                colleague.receive(message);
        }
    }
}