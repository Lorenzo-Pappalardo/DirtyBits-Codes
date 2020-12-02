package talking_threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class MyThread extends Thread {
  private final String address;
  private final int port;

  MyThread(String name, String address, int port) {
    super(name);
    this.address = address;
    this.port = port;
  }

  @Override
  public void run() {
    System.out.println(getName() + ") Enter command: ");

    Scanner keyboardInput = new Scanner(System.in);
    String command = keyboardInput.nextLine();
    keyboardInput.close();

    if (!(command.equals("exit"))) {
      try (Socket socket = new Socket(InetAddress.getByName(address), port);
          BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));) {
        sendBuffer.println(command);

        System.out.println(receiveBuffer.readLine());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
