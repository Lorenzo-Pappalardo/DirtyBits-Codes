package CubeOfNumber;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) {
    System.out.println("Server started...");
    final int port = 3333;

    try (ServerSocket socket = new ServerSocket(port);) {
      Socket clientSocket = socket.accept();
      System.out.println("Connection from " + clientSocket + " accepted");
      BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

      String str;
      while (!((str = receiveBuffer.readLine()).equals("quit"))) {
        try {
          int number = Integer.parseInt(str);
          sendBuffer.println(getCube(number));
        } catch (Exception e) {
          sendBuffer.println("NaN");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static int getCube(int number) {
    return (int) Math.pow(number, 3);
  }
}