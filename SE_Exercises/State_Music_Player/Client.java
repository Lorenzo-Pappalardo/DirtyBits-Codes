package State_Music_Player;

public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setState("start");
        System.out.println(context.getState());
        context.printState();
        context.setState("stop");
        System.out.println(context.getState());
        context.printState();
    }
}