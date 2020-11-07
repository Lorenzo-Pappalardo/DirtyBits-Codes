import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/*
  Scrivere in C o Java un programma che:
  - Apra una connessione verso l'IP 90.147.166.230 con porta 8080
  - Invii la stringa "GET /pappalardo/prova/06.aux\r\n"
  - Nello stream di byte ricevuti individui il primo carattere diverso da 'x', spazio o fine riga
  - Memorizzi questo byte e tutti quelli che il server invierà ancora in una variabile stringa denominata "s"
  - Riaperta la connessione, invii la stringa s, seguita da "\n"
  - Scriva i byte ricevuti in risposta sulla standard output
*/

public class E6 {
  public static void main(String[] args) {
    final String address = "90.147.166.230";
    final int port = 8080;
    final String command = "GET /pappalardo/prova/06.aux\r\n";
    String nextCommand = "";

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
      socketOutput.println(command);

      String res;
      Boolean save = false;
      while ((res = socketInput.readLine()) != null) {
        for (int i = 0; i < res.length(); i++) {
          char tmpChar = res.charAt(i);
          if (tmpChar != 'x' && tmpChar != ' ' && tmpChar != '\n' && !save) {
            save = !save;
          }
          if (save) {
            nextCommand += tmpChar;
          }
        }
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
      socketOutput.println(nextCommand);

      String res;
      while ((res = socketInput.readLine()) != null) {
        System.out.println(res);
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }
}
