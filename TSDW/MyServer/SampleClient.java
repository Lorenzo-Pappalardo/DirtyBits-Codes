package MyServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SampleClient {
  public static void main(String[] args) {
    final String address = "localhost";
    final int port = 8080;

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        Scanner scanner = new Scanner(System.in);) {

      IncomingMessages thread = new IncomingMessages(receiveBuffer);
      thread.start();

      String command;
      while (true) {
        command = scanner.nextLine();
        if (command.equalsIgnoreCase("q")) {
          thread.terminate();
          break;
        }
        sendBuffer.println(command);
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }
}