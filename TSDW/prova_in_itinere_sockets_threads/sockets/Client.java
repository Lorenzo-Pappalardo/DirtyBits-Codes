package prova_in_itinere_sockets_threads.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("You must pass Server's ip address and port");
      System.exit(1);
    }

    try (Socket socket = new Socket(InetAddress.getByName(args[0]), Integer.parseInt(args[1]));
        BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {
      System.out.println("Insert words:\t\t\tInsert \"exit\" to stop");
      Scanner scanner = new Scanner(System.in);

      while (true) {
        String line = scanner.nextLine();
        sendBuffer.println(line);

        if (line.equals("exit"))
          break;
      }

      scanner.close();

      System.out.println("Max Length" + receiveBuffer.readLine());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
