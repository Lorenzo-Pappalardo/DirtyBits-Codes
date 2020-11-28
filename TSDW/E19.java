import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class E19 {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("You must pass a number");
      System.exit(1);
    }

    int nthWord = Integer.parseInt(args[0]);

    final String address = "90.147.166.230";
    final int port = 8080;
    final String command = "GET /prova/dizionario\r\n";
    String chosenWord = "";

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
      sendBuffer.println(command);

      String res;
      int i = 0;
      while ((res = receiveBuffer.readLine()) != null) {
        i++;
        if (i == nthWord)
          chosenWord = res;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Chosen word: " + chosenWord);

    final String nextCommand = "GET /prova/19.aux\r\n";

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
      sendBuffer.println(nextCommand);

      String res;
      int occurrencies = 0;
      while ((res = receiveBuffer.readLine()) != null) {
        String[] words = res.split(" ");
        for (String word : words)
          if (word.equals(chosenWord))
            occurrencies++;
      }

      System.out.println("Occurrencies: " + occurrencies);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
