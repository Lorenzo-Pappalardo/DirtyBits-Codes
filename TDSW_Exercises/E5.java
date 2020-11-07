/*
  Scrivere in C o Java un programma che:
  - Apra una connessione verso l'IP 90.147.166.230 con porta 8080
  - Invii la stringa "GET /pappalardo/prova/05.aux\n"
  - Dallo stream di byte ricevuti individui i primi (e unici) compresi tra i caratteri [ e ]
  - Memorizzi questi byte in una variabile stringa denominata "command2"
  - Invii, su una nuova connessione verso lo stesso server, la stringa command2, terminata da "\r\n"
  - Scriva i byte ricevuti in risposta sullo standard output
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class E5 {
  public static void main(String[] args) {
    final String address = "90.147.166.230";
    final int port = 8080;
    final String command1 = "GET /pappalardo/prova/05.aux\r\n";
    String command2 = "";

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {
      socketOutput.println(command1);

      String res;
      Boolean save = false;
      while ((res = socketInput.readLine()) != null) {
        int i = 0;
        while (i < res.length()) {
          if (res.charAt(i) == ']') {
            save = !save;
          }
          if (save) {
            command2 += res.charAt(i);
          }
          if (res.charAt(i) == '[') {
            save = !save;
          }
          i++;
        }
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }

    command2 += "\r\n";

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {
      socketOutput.println(command2);

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