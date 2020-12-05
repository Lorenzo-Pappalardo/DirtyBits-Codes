package prova_in_itinere_sockets_threads.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  private static final int PORT = 8080;

  private static int maxLength = 0;

  public static void main(String[] args) {
    System.out.println("Server started");

    try (ServerSocket socket = new ServerSocket(PORT);
        Socket clientSocket = socket.accept();
        BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);) {
      System.out.println("Connection from " + clientSocket + " accepted");

      // sendBuffer.println("Insert word: "); // This is needed to use telnet

      while (true) {
        String word = receiveBuffer.readLine();
        if (word.equals("exit"))
          break;

        System.out.println("Received: " + word);

        if (word.length() > maxLength) {
          maxLength = word.length();
        }
      }

      sendBuffer.println("Max Length: " + maxLength);
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Server stopped");
  }
}