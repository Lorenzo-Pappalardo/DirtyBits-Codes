import java.io.*;
import java.net.*;

public class Server{

    ServerSocket server = null;
    Socket socketClient = null;

    int porta = 6789; //porta server

    DataOutputStream out;
    DataInputStream in;

    public Socket attendi(){
        try {
            System.out.println("[0] - Apertura Server");
            //inizializziamo il servizio
            server = new ServerSocket(porta);

            System.out.println("[1] - Server pronto, in ascolto sulla porta:" + porta);
            // attende la connessione del client
            socketClient = server.accept()

            System.out.println("[2] - Connessione completa")
            //evitiamo connessioni multiple
            server.close();

            in = new DataInputStream(socketClient.getInputStream());
            out = new DataOutputStream(socketClient.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Server s = new Server();
        s.attendi();
    }
}