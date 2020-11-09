/*
Implementare in C o Java, su localhost, un programma server banca, connection-oriented, che:
  - Mantenga un array di 10 interi conto che rappresentano il saldo dei conti da 0 a 9;
  - Risponda, su localhost, port 7777 ai seguenti messaggi:
    [Un] dove n, da 0 a 9, è il numero del conto;
      l'effetto sarà di rendere il conto n quello attuale, cioè su cui operano implicitamente gli altri comandi;
    [Vxyzw] dove xyzw sono 4 cifre intere;
      l'effetto sarà di versare la somma xyzw sul conto attuale (cioè l'ultimo selezionato con il comando [Un])
    [Pxyzw] dove xyzw sono 4 cifre intere;
      l'effetto sarà di prelevare la somma xyzw dal conto attuale (cioè l'ultimo selezionato con il comando [Un])
    [S]
      l'effetto sarà di inviare al cliente il saldo depositato sul conto attuale (cioè l'ultimo selezionato con il comando [Un])
  - Risponda ERROR a ogni altro dato ricevuto dal cliente

  P.S. io ho cambiato lettere in conformità alle parole inglesi corrispondenti alle azioni intraprese
*/

package E10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Bank {
  public static final int PORT = 7777;
  private static final int[] accounts = new int[10];

  private static void initAccounts() {
    for (int i = 0; i < 10; i++) {
      accounts[i] = 0;
    }
  }

  private static void handleError(PrintWriter sendBuffer) {
    System.out.println("Unknown command");
    sendBuffer.println("ERROR");
  }

  public static void main(String[] args) {
    initAccounts();

    try (ServerSocket serverSocket = new ServerSocket(PORT);) {
      System.out.println("Server started: " + serverSocket);
      System.out.println("Waiting for connections...\n");
      Socket clientSocket = serverSocket.accept();
      System.out.println("Connection accepted: " + clientSocket);

      BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

      int selectedAccount = -1;

      while (true) {
        String request = receiveBuffer.readLine();
        if (request.equalsIgnoreCase("quit") || request.equalsIgnoreCase("exit") || request.equalsIgnoreCase("end")) {
          break;
        }
        System.out.println("Received request: " + request);
        request = request.toUpperCase();

        switch (request.charAt(0)) {
          case 'S':
            if (request.length() < 2) {
              handleError(sendBuffer);
              break;
            }
            selectedAccount = Character.getNumericValue(request.charAt(1));
            System.out.println("Selected account: " + selectedAccount);
            sendBuffer.println("Selected account: " + selectedAccount);
            break;
          case 'D':
            if (request.length() < 2) {
              handleError(sendBuffer);
              break;
            }
            if (selectedAccount != -1) {
              int amount = Integer.parseInt(request.substring(1));
              if (amount > 0) {
                accounts[selectedAccount] += amount;
                System.out.println("Deposited " + amount + " on account " + selectedAccount);
                sendBuffer.println(amount + " deposited successfully on account " + selectedAccount);
              } else
                sendBuffer.println("Enter a positive amount");
            } else
              sendBuffer.println("First select the account!");
            break;
          case 'W':
            if (request.length() < 2) {
              handleError(sendBuffer);
              break;
            }
            if (selectedAccount != -1) {
              int amount = Integer.min(accounts[selectedAccount], Integer.parseInt(request.substring(1)));
              if (amount > 0) {
                accounts[selectedAccount] -= Integer.min(accounts[selectedAccount], amount);
                System.out.println("Withdrew " + amount + " from account " + selectedAccount);
                sendBuffer.println(amount + " withdrawn successfully from account " + selectedAccount);
              } else
                sendBuffer.println("Enter a positive amount");
            } else
              sendBuffer.println("First select the account!");
            break;
          case 'V':
            if (selectedAccount == -1) {
              handleError(sendBuffer);
              sendBuffer.println("First select the account!");
              break;
            }
            sendBuffer.println("Money: " + accounts[selectedAccount]);
            System.out.println("Sent money availability to us");
            break;
          default:
            handleError(sendBuffer);
        }
      }

      receiveBuffer.close();
      sendBuffer.close();
      clientSocket.close();
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }
}
