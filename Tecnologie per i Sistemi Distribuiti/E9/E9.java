/*
  Scrivere in C o Java un programma cliente che:
  - Apra una connessione verso l'IP 90.147.166.230 con porta 8080
  - Invii a tale server la stringa "GET /prova/09.aux\r\n"
  - Nello stream testo ricevuto, legga il numero che rappresenta il (solo) contenuto della riga 12
  - Moltiplichi tale numero per il valore restituito dalla funzione remota hash(), eseguita su localhost e applicata al proprio cognome; sia n il risultato,
  - Invii a 90.147.166.230 con porta 8080 la stringa "GET /prova/09b.aux\r\n",
  - Dallo stream testo ricevuto, individui la riga n e ne scriva il contenuto sulla standard output.
*/

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
    final String command = "GET /prova/09.aux\r\n";
    String nextCommand = "GET /prova/09b.aux\r\n";

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
