/*
  Scrivere in C o Java un programma che:
  - Apra una connessione verso l'IP 90.147.166.230, port 8080
  - Invii la stringa "GET /prova/08.aux\r\n" nello stream testo ricevuto
  - Salti le prime 22015 righe e scriva sulla standard output le righe dalla 22016 alla 22023
  - Salti altre 3828 righe in avanti
  - Legga il numero intero all'inizio della nuova riga corrente: si tratta di un ulteriore numero di riga, che diremo n
  - Scriva le righe dalla n alla n+8
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class E8 {
  public static void main(String[] args) {
    final String address = "90.147.166.230";
    final int port = 8080;
    final String command = "GET /prova/08.aux\r\n";
    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
      socketOutput.println(command);

      String res;
      int linesRead = 0;
      int count = 3828;
      int n = 0;
      int offset = 8;
      while ((res = socketInput.readLine()) != null) {
        linesRead++;
        if (linesRead >= 22016 && linesRead <= 22023) {
          System.out.println(res);
        }
        if (count == 0 && n == 0) {
          n = Integer.parseInt(res);
        }
        if (linesRead > 22023 && count > 0) {
          count--;
        }
        if (n != 0 && offset > 0 && linesRead >= n) {
          System.out.println(res);
          offset--;
        }
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }
}
