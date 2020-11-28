import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;

public class ServerParallelo extends Thread {
    private int PORT;
    private String s_name;
    private ServerPort sp;
    public ServerParallelo(String s_name, ServerPort port) {
        this.PORT = port.getPort();
        this.s_name = s_name;
        this.sp=port;

    }

    @Override
    public String toString() {
        return this.s_name + " " + this.PORT;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.PORT);
            BufferedReader client_in;
            PrintWriter client_out;
            String msg;
            System.out.println(this.s_name + " connesso su port " + this.PORT + ".");
            sp.setRunning(true);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Server connesso con " + clientSocket + ".");
            client_in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            client_out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            while (true) {
                msg = client_in.readLine();
                if (msg.equals("END"))
                    break;
                System.out.println(msg);
                client_out.println("Server Echo>> messaggio inviato: "+msg);
            }
            client_in.close();
            client_out.close();
            clientSocket.close();
            Server.deleteServer(this);
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPort(){
        return this.PORT;
    }


}
