package State_Music_Player;

public class Start implements State {
    @Override
    public void printState() {
        System.out.println("Music player started!");
    }

    @Override
    public String toString() {
        return "Started!";
    }
}