package acronym;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_TCP {
  private static final int PORT = 8080;

  public static void main(String[] args) {
    System.out.println("Server started");

    try (ServerSocket socket = new ServerSocket(PORT);
        Socket clientSocket = socket.accept();
        BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);) {
      System.out.println("Connection from " + clientSocket + " accepted");

      sendBuffer.println("Insert your name: ");
      String name = receiveBuffer.readLine();

      sendBuffer.println(Server_UDP.explodeAcronym(name));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}