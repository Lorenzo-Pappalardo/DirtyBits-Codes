package State_Music_Player;

public class Stop implements State {
    @Override
    public void switchState() {
        System.out.println("Music player stopped!");
    }

    @Override
    public String toString() {
        return "Stopped!";
    }
}