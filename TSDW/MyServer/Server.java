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
import java.util.Scanner;

public class Server {
  private static int visitors;

  Server() {
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
        visitors++;

        sendBuffer.println("Hi " + clientSocket.getInetAddress() + " xD\t\t\t You're visitor n." + visitors);
        sendCommands(sendBuffer);

        handleRequest(clientSocket, receiveBuffer, sendBuffer);
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("Previous connection closed\n");
    }
  }

  private static void sendCommands(PrintWriter sendBuffer) {

    sendBuffer.println("\nHow can I help you?");
    sendBuffer.println("[d] Show current server's date   [i] Inspirational quote   [c] Chat   [q] Quit");
  }

  private static void handleRequest(Socket clientSocket, BufferedReader receiveBuffer, PrintWriter sendBuffer)
      throws IOException {
    String input;
    while ((input = receiveBuffer.readLine()) != null) {
      sendBuffer.println();

      if (input.length() > 1)
        sendBuffer.println("Unknown command! Please try again.");

      else if (input.equalsIgnoreCase("d")) {
        Date date = new Date(System.currentTimeMillis());
        sendBuffer.println("Server's date: " + date);
      }

      else if (input.equalsIgnoreCase("q")) {
        sendBuffer.println("Have a great day!");
        break;
      }

      else if (input.equalsIgnoreCase("e")) {
        sendBuffer.println("You've found an Easter Egg!");
        sendBuffer.println("\t\tCongratulations user " + clientSocket.getInetAddress());
        sendBuffer.println("\t\tYou are the " + visitors + " visitor, today is your lucky day!");
      }

      else if (input.equalsIgnoreCase("i")) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("../MyServer/quotes.txt")));) {
          int i = new Random().nextInt(102);
          for (int row = 0; row < i; row++)
            reader.readLine();
          sendBuffer.println(reader.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      else if (input.equalsIgnoreCase("c")) {
        Scanner keyboardInput = new Scanner(System.in);
        System.out.println("\n\n----------Chat started----------\n");
        sendBuffer.println("\n\n----------Chat started----------\n");
        while (true) {
          String line = receiveBuffer.readLine();
          if (line.equalsIgnoreCase("exit")) {
            System.out.println("\n\n----------Chat stopped----------\n");
            sendBuffer.println("\n\n----------Chat stopped----------\n");
            break;
          }
          System.out.println("Received: " + line);
          System.out.print("Enter reply: ");
          sendBuffer.println("Server's reply: " + keyboardInput.nextLine());
        }
        keyboardInput.close();
      }

      else
        sendBuffer.println("Unknown command! Please try again.");

      sendCommands(sendBuffer);
    }
  }
}