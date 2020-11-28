import java.util.Random;

public class ServerPort {
    private int n_port;
    private boolean running;
    private Random random;

    ServerPort() {
        this.random = new Random();
        this.n_port = random.nextInt(101) + 5000;
        this.running = false;
    }

    public int getPort() {
        return this.n_port;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void setRunning(boolean status){
        this.running=status;
    }

    @Override
    public String toString(){
        return this.n_port+"\t"+this.running;
    }
}