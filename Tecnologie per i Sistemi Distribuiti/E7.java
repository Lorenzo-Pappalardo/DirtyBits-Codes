/*
  Scrivere in C o Java un programma che:
  - Apra una connessione verso l'IP 90.147.166.230, port 8080
  - Invii la stringa "GET /prova/07.aux\r\n"
  - Nello stream di byte ricevuti, cerchi il primo contenente il carattere '-'
  - Memorizzi quindi i successivi 31 byte
  - Scriva questi 31 byte sulla standard output
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class E7 {
  public static void main(String[] args) {
    final String address = "90.147.166.230";
    final int port = 8080;
    final String command = "GET /prova/07.aux\r\n";

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
      socketOutput.println(command);

      String res;
      Boolean save = false;
      int count = 31;
      String message = "";
      while ((res = socketInput.readLine()) != null) {
        for (int i = 0; i < res.length(); i++) {
          if (count > 0 && save) {
            message += res.charAt(i);
            count--;
          }
          if (res.charAt(i) == '-') {
            save = !save;
          }
          if (count == 0)
            break;
        }
        if (count == 0)
          break;
      }

      System.out.println(message);
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }
}