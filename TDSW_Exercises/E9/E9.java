package E9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class E9 {
  public static void main(String[] args) {
    final String address = "90.147.166.230";
    final int port = 8080;
    final String command = "GET /pappalardo/prova/09.aux\r\n";
    String nextCommand = "GET /pappalardo/prova/09b.aux\r\n";

    int n = 0;

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOutput = new PrintWriter(new PrintWriter(socket.getOutputStream()), true)) {
      socketOutput.println(command);

      String res;
      int linesRead = 0;
      while ((res = socketInput.readLine()) != null) {
        linesRead++;
        if (linesRead == 12) {
          int tmpN = Integer.parseInt(res);
          n = tmpN * Hash.getHash("Pappalardo");
          break;
        }
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOutput = new PrintWriter(new PrintWriter(socket.getOutputStream()), true)) {
      socketOutput.println(nextCommand);

      String res;
      int linesRead = 0;
      while ((res = socketInput.readLine()) != null) {
        linesRead++;
        if (linesRead == n) {
          System.out.println(res);
          break;
        }
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }
}
