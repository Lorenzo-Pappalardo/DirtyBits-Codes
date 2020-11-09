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
