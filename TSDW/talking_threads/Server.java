package talking_threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  private static final int PORT = 8080;

  public static void main(String[] args) {
    System.out.println("System started");

    while (true) {
      try (ServerSocket socket = new ServerSocket(PORT);
          Socket clientSocket = socket.accept();
          BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);) {
        System.out.println("Connection from " + clientSocket + " accepted");

        sendBuffer.println("Usage: <command> <text>");

        String request = receiveBuffer.readLine();

        System.out.println("Received request: " + request);

        sendBuffer.println(handleRequest(request));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private static String handleRequest(String request) {
    if (request.substring(0, 3).equalsIgnoreCase("sum")) {
      String line = request.substring(4);

      int sum = 0;
      for (int i = 0; i < line.length(); i++)
        sum += Character.valueOf(line.charAt(i));

      return String.valueOf(sum);
    }

    if (request.substring(0, 10).equalsIgnoreCase("capitalize")) {
      String line = request.substring(11);
      boolean capitalize = false;

      String capitalizedLine = "";
      capitalizedLine += line.substring(0, 1).toUpperCase();

      for (int i = 1; i < line.length(); i++) {
        if (line.charAt(i) == ' ') {
          capitalizedLine += line.charAt(i);
          capitalize = true;
          continue;
        }

        if (capitalize) {
          capitalizedLine += line.substring(i, i + 1).toUpperCase();
          capitalize = false;
        } else {
          capitalizedLine += line.charAt(i);
        }
      }
      return capitalizedLine;
    }

    return "ERROR";
  }
}