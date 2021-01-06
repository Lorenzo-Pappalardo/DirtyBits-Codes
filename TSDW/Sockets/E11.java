/*
  Scrivere in C o Java un programma che:
  - Apra una connessione verso l'IP 90.147.166.230 con porta 8080
  - Invii la stringa "GET /prova/11.aux\r\n"
  - Nello stream di byte ricevuti individui la prima parentesi quadra aperta
  - Memorizzi tutti i successivi byte fino alla prima parentesi quadra chiusa, che il server invierà, in una variabile stringa denominata "s"
  - Riaperta la connessione, invii la stringa s (attenzione: dovrebbe terminare per "\n")
  - Scriva i byte ricevuti in risposta sulla standard output
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class E11 {
  public static void main(String[] args) {
    final String address = "90.147.166.230";
    final int port = 8080;
    final String command = "GET /prova/11.aux\r\n";
    String nextCommand = "";

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {
      sendBuffer.println(command);

      String res;
      Boolean save = false;
      while ((res = receiveBuffer.readLine()) != null) {
        for (int i = 0; i < res.length(); i++) {
          if (res.charAt(i) == ']') {
            save = false;
            break;
          }
          if (save) {
            nextCommand += res.charAt(i);
          }
          if (res.charAt(i) == '[')
            save = true;
        }
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }

    try (Socket socket = new Socket(InetAddress.getByName(address), port);
        BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {
      sendBuffer.println(nextCommand);

      String res;
      while ((res = receiveBuffer.readLine()) != null) {
        System.out.println(res);
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }
}