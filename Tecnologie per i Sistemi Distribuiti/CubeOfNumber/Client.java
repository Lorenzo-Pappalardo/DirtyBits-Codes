package CubeOfNumber;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    final String address = "localhost";
    final int port = 3333;

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {
      System.out.println("Insert commands:");

      Scanner keyboardInput = new Scanner(System.in);
      String input;
      while (!((input = keyboardInput.nextLine()).equals("quit"))) {
        sendBuffer.println(input);
        String res = receiveBuffer.readLine();
        System.out.println("Response: " + res);
        System.out.println("\nInsert commands:");
      }
      sendBuffer.println("quit");
      keyboardInput.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
