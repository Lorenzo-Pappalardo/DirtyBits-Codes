package State_Music_Player;

/** Context */
public class Context {
    private State state;

    public Context() {
        state = null;
    }

    public void setState(String action) {
        if (action.equalsIgnoreCase("start")) {
            this.state = new Start();
        } else if (action.equalsIgnoreCase("stop")) {
            this.state = new Stop();
        } else {
        }
    }

    public State getState() {
        return state;
    }

    public void printState() {
        state.printState();
    }
}