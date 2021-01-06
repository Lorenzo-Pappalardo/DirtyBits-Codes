// Simple client to interact with Bank server

package E10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
  public static void main(String[] args) {
    String serverAddress;
    if (args.length == 0)
      serverAddress = "localhost";
    else
      serverAddress = args[0];
    final int serverPort = Bank.PORT;

    try (Socket socket = new Socket(InetAddress.getByName(serverAddress), serverPort);
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));) {
      while (true) {
        System.out.print("Insert command: ");
        String command = userInput.readLine();
        socketOutput.println(command);
        if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("end"))
          break;
        String res = socketInput.readLine();
        System.out.println(res + '\n');
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }
}
