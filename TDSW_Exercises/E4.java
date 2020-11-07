/*
  Scrivere in C o Java un programma che:
  - Apra una connessione verso l'IP 90.147.166.230 con porta 8080
  - Invii la stringa "GET /pappalardo/prova/04.aux\n"
  - Dallo stream di byte ricevuti memorizzi quelli relativi alle righe in posizione 5503-5537
  - Scriva questi byte sulla standard output
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class E4 {
  public static void main(String[] args) {
    final String address = "90.147.166.230";
    final int port = 8080;
    final String command = "GET /pappalardo/prova/04.aux\r\n";

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {
      socketOutput.println(command);

      ArrayList<String> finalText = new ArrayList<>();
      int linesRead = 0;
      String res;

      while ((res = socketInput.readLine()) != null) {
        linesRead++;
        if (linesRead >= 5503 && linesRead <= 5537) {
          finalText.add(res);
        }
        if (linesRead > 5537)
          break;
      }

      for (String line : finalText) {
        System.out.println(line);
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }
}