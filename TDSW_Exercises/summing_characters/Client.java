package summing_characters;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("You must pass server's address and port");
      System.exit(1);
    }

    try (Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        Scanner scanner = new Scanner(System.in);) {
      System.out.println("Client started");
      System.out.print("-> ");

      String line = scanner.nextLine();
      socketOutput.println(line);

      if (!(line.equals("exit"))) {
        String res;
        while ((res = socketInput.readLine()) != null)
          System.out.println(res);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
