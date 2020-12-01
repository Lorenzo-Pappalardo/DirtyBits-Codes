package MyServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;

public class Server {
  private static boolean quit;
  private static int visitors;

  Server() {
    quit = false;
    visitors = 0;
  }

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
          handleRequest(clientSocket, sendBuffer, input);
          if (quit)
            break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("Previous connection closed\n");
      visitors++;
    }
  }

  private static void welcome(PrintWriter sendBuffer) {
    sendBuffer.println("Hi, how can I help you?");
    sendBuffer.println("[d] Show current server's date\t[i] Inspirational quote\t[q] Quit");
  }

  private static void handleRequest(Socket clientSocket, PrintWriter sendBuffer, String input) {
    if (input.length() > 1)
      sendBuffer.println("Unknown command! Please try again.");
    else if (input.equalsIgnoreCase("d")) {
      Date date = new Date(System.currentTimeMillis());
      sendBuffer.println("Server's date: " + date);
    } else if (input.equalsIgnoreCase("q")) {
      sendBuffer.println("Have a great day!");
      quit = true;
    } else if (input.equalsIgnoreCase("e")) {
      sendBuffer.println("You've found an Easter Egg!");
      sendBuffer.println("\t\tCongratulations user " + clientSocket.getInetAddress());
      sendBuffer.println("\t\tYou are the " + visitors + " visitor, today is your lucky day!");
    } else if (input.equalsIgnoreCase("i")) {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("../MyServer/quotes.txt")));) {
        Random random = new Random();
        int i = random.nextInt(102);
        for (int row = 0; row < i; row++)
          reader.readLine();
        sendBuffer.println(reader.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else
      sendBuffer.println("Unknown command! Please try again.");
  }
}