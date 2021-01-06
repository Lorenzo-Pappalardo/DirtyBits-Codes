/*
Realizzare un server (in C o in Java) che si metta in ascolto sul port 3333, sul quale accetta richieste di connessione da un client. 
Sulla connessione stabilita, il server riceve una stringa, terminata dal carattere '\n' (si supponga che basti una sola operazione di lettura in ricezione da parte del server, per ricevere ciascuna stringa inviata dal client). 
Il server quindi scrive la stringa ricevuta sullo standard output e, chiusa la connessione, si rimette in attesa di eventuali richieste di connessione. 
Testare il server server_A usando telnet. 
Conservando la versione (A) del server, realizzarne un’ulteriore versione (B), tale che ciascuna stringa ricevuta dal server, oltre ad essere scritta dal server sulla propria standard output, sia inviata nuovamente al client come risposta (senza alcuna modifica). 
Testare il server server_B usando telnet.
Conservando la versione (B) del server, realizzarne un’ulteriore versione tale che il server implementi un metodo/funzione sommacifre(s) 
che accetta per argomento una stringa s e restituisce un intero;  per il momento, ai fini di questo quesito (C), si supponga che, per qualunque stringa argomento s, venga restituito l’intero 0. In questa versione, il server, ricevuta una stringa s la scrive sulla standard output, calcola x = sommacifre(s)
e invia al client un messaggio in forma di stringa  del tipo n,x
dove n è un id progressivo della richiesta fatta dal client, mentre x è l’output del metodo sommacifre(s). L’id, che fa parte dello stato del server, parte da 0 e viene incrementato ad ogni nuova richiesta da parte di un client fino a quando il server non viene riavviato.
Testare il server server_C usando telnet. 
Modificare la funzione sommacifre(s) in modo che, riconosciute le cifre numeriche al suo interno, restituisca la somma delle singole cifre o 0 se non ve ne fosse alcuna. 
Esempio: sommacifre("c0a89s2a3hk") restituirà 22 (dato che 0+8+9+2+3 = 22).
*/

package summing_characters;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  private static final int PORT = 3333;
  private static int handledRequests = 0;

  public static void main(String[] args) {
    System.out.println("Server started");

    while (true) {
      try (ServerSocket socket = new ServerSocket(PORT);
          Socket clientSocket = socket.accept();
          BufferedReader socketInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);) {
        System.out.println("Connection accepted from " + clientSocket);
        socketOutput.println("Insert a line:");

        String line = socketInput.readLine();
        if (line.equals("exit")) {
          socketOutput.println("Bye xD");
          break;
        }

        System.out.println("Received line: " + line); // A
        // socketOutput.println(line); // B
        socketOutput.println("Request n." + ++handledRequests + "°: Sum of all character as digits = " + sumAll(line)); // C
        socketOutput.println("Request n." + ++handledRequests + "°: Sum of only digits = " + sumOnlyNumbers(line)); // D
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private static int sumAll(String s) {
    int sum = 0;
    for (int i = 0; i < s.length(); i++) {
      sum += Character.valueOf(s.charAt(i));
    }
    return sum;
  }

  private static int sumOnlyNumbers(String s) {
    int sum = 0;
    for (int i = 0; i < s.length(); i++) {
      if (Character.isDigit(s.charAt(i)))
        sum += Character.getNumericValue(s.charAt(i));
    }
    return sum;
  }
}
