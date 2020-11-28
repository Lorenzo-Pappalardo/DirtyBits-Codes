package E14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class E14 {
  public static String[] numbers = new String[9];

  private static void printSudoku() {
    int w = 0;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (w == 3 || w == 7) {
          for (int z = 0; z < 29; z++)
            System.out.print('-');
          System.out.println();
          w++;
        }
        System.out.print(numbers[i].charAt(j));
        if (j == 2 || j == 5)
          System.out.print('|');
        else
          System.out.print("   ");
        if (j == 8) {
          System.out.println();
          w++;
        }
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    final String address = "90.147.166.230";
    final int port = 8080;
    final String command = "GET /prova/14.aux\r\n";
    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {
      sendBuffer.println(command);

      String res;
      int i = 0;
      while ((res = receiveBuffer.readLine()) != null) {
        if (res.charAt(0) >= '1' && res.charAt(0) <= '9') {
          numbers[i++] = res;
        }
      }
      printSudoku(); // Graphical visualization of the data acquired

      int wrongRows = 0;
      for (String row : numbers) {
        if (!(Validate.validate(row)))
          wrongRows++;
      }

      System.out.println("Wrong rows(not valid permutations): " + wrongRows);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}