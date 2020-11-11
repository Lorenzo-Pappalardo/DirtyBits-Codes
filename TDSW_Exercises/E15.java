/*
  Scrivere in C o Java un programma che:
  1) Apra una connessione verso l'IP 90.147.166.230 con porta 8080
  2) Invii al server la stringa "GET /prova/15.aux\r\n"; Lo stream ricevuto in risposta dal server è organizzato in righe di 9 caratteri;
  3) Legga le righe ricevute, finché ne arrivano;
  4) Per ogni riga ricevuta controlli, come specificato di seguito, se essa sia una permutazione delle cifre arabiche da 1 a 9;
  5) Alla fine, scriva su console quante righe sono risultate permutazioni del tipo detto.
  
  Per la verifica richiesta al punto (4) sopra, occorre implementare una funzione/metodo validate() che abbia:
  - Input: stringa di 9 caratteri
  - Output da restituire:
    - 1, se i caratteri della riga sono una permutazione delle cifre da 1 a 9;
    - 0, se nella stringa in input una cifra è ripetuta o anche uno solo dei caratteri non è una cifra.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class E15 {
  public static void main(String[] args) {
    final String ADDRESS = "90.147.166.230";
    final int PORT = 8080;
    final String command = "GET /prova/15.aux\r\n";
    int correctLines = 0;

    try (Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
        BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
      sendBuffer.println(command);

      String res;
      while ((res = receiveBuffer.readLine()) != null) {
        if (validate(res))
          correctLines++;
      }

      System.out.println("Correct lines: " + correctLines);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static boolean validate(String line) {
    boolean[] numbers = new boolean[10];
    for (int i = 0; i < 10; i++) {
      numbers[i] = false;
    }

    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) < '0' || line.charAt(i) > '9')
        return false;
      if (numbers[Character.getNumericValue(line.charAt(i))])
        return false;
      numbers[Character.getNumericValue(line.charAt(i))] = true;
    }
    return true;
  }
}