import java.io.*;
import java.net.*;

//package Base;

public class BaseClient {
    Socket mioSocket = null;
    int porta = 6789; //porta server
    DataInputStream in;
    DataOutputStream out;

    public Socket connetti(){
        try {
            System.out.println("[0] - Provo a connettermi al server");
            Socket mioSocket = new Socket (InetAddress.getLocalHost(), porta);

            System.out.println("[1] - Connesso");
            in = new DataInputStream(mioSocket.getInputStream());
            out = new DataOutputStream(mioSocket.getOutputStream());
            
        } catch (UnknownHostException e){
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("impossibile stabilire la connessione");
        }
        return mioSocket;
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.connetti();
    }
}
