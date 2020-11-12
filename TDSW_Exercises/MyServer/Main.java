package MyServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Main {
  private static boolean quit = false;

  public static void main(String[] args) {
    System.out.println("Server started\n");
    while (true) {
      try (ServerSocket socket = new ServerSocket(8080);
          Socket clientSocket = socket.accept();
          BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true)) {
        System.out.println("Connection accepted: " + clientSocket);
        welcome(sendBuffer);

        String input;
        while ((input = receiveBuffer.readLine()) != null) {
          handleRequest(sendBuffer, input);
          if (quit)
            break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("Previous connection closed\n");
    }
  }

  private static void welcome(PrintWriter sendBuffer) {
    sendBuffer.println("Hi, how can I help you?");
    sendBuffer.println("[d] Show current server's date\t[q] Quit");
  }

  private static void handleRequest(PrintWriter sendBuffer, String input) {
    if (input.length() > 1)
      sendBuffer.println("Unknown command! Please try again.");
    if (input.equalsIgnoreCase("d")) {
      Date date = new Date(System.currentTimeMillis());
      sendBuffer.println("Server's date: " + date);
    } else if (input.equalsIgnoreCase("q")) {
      sendBuffer.println("Have a great day!");
      quit = true;
    } else
      sendBuffer.println("Unknown command! Please try again.");
  }
}