
/**Funzionamento:
 * -Lanciare Server.java, verranno mostrate tre porte molto alte generate random (range da 5000 e 5100) 
 *  (sono 3 di default, modificabili dalla variabile "number_of_parallel_servers")
 * -Connettersi con telnet a localhost porta 9091 da un altro terminale
 * -Sul terminale del server sarà notificata una porta in stato di running
 * -Al client chiamante con telnet sara' invece inviata la porta alla quale connettersi
 * -Effettuare la nuova connessione a localhost con la nuova porta indicata
 * -il client puo' adesso inviare al server dei messaggi che verranno stampati nello standard out
 * -quando ha finito trasmette END per liberare la porta e chiudere la trasmissione
 * -procedimento eseguibile in parallello con n client fino ad esaurimento porte
 * -??? SERVER CHAT >> PROFIT.
 * 
 * -TODO:
 * -meccanismo che faccia autoterminare i thread che restano in ascolto per troppo tempo senza ricevere connessione
 *  rendendo la porta nuovamente disponibile
 */

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {
    private static List<ServerParallelo> servers = new ArrayList<>();
    private static List<ServerPort> serverPorts = new ArrayList<>();
    private static Random random = new Random();
    private static int number_of_parallel_servers = 3;
    public static final int PORT = 9091;

    public static void main(String[] args) {
        initialize();
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            PrintWriter client_out;
            while (true) {
                System.out.println("Server principale connesso su port " + PORT + ".");
                while (true) {
                    printServerPorts();
                    System.out.println("In attesa di richieste in entrata...");
                    Socket clientSocket = serverSocket.accept();
                    client_out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
                    if (!Server.isFull()) {
                        ServerParallelo sp = new ServerParallelo("S" + random.nextInt(101), getFreePort());
                        servers.add(sp);
                        sp.start();
                        System.out.println("New server >> " + sp);
                        client_out.println("Connettersi su port " + sp.getPort());
                    } else {
                        client_out.println("I nostri server sono momentaneamente occupati.");
                    }
                    client_out.close();
                    clientSocket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initialize() {
        servers.clear();
        serverPorts.clear();
        loadPorts();
    }

    private static ServerPort getFreePort() {
        for (ServerPort sp : serverPorts)
            if (sp.isRunning() == false) {
                return sp;
            }
        return null;
    }

    private static void loadPorts() {
        int valid_servers = 0;
        while (valid_servers < number_of_parallel_servers) {
            ServerPort tmp_sp = new ServerPort();
            boolean check = true;
            for (ServerPort sp : serverPorts)
                if (tmp_sp.getPort() == sp.getPort()) {
                    check = false;
                }
            if (check) {
                serverPorts.add(tmp_sp);
                valid_servers++;
            }
        }
    }

    public static void deleteServer(ServerParallelo parserv) {
        if (servers.contains(parserv)) {
            for (ServerPort sp : serverPorts)
                if (parserv.getPort() == sp.getPort()) {
                    sp.setRunning(false);
                }
            servers.remove(parserv);
        }
    }

    private static boolean isFull() {
        if (servers.size() == number_of_parallel_servers)
            return true;
        else
            return false;
    }

    private synchronized static void printServerPorts() {
        System.out.println("PORT\tRUNNING");
        boolean check = false;
        for (ServerPort sp : serverPorts) {
            System.out.println(sp);
            if (!sp.isRunning())
                check = true;
        }
        if (!check) {
            System.out.println("ALERT: all servers are running!");
        }

    }

}