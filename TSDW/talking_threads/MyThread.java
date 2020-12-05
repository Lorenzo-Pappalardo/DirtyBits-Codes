package talking_threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MyThread extends Thread {
  private final String address;
  private final int port;
  private final String command;

  MyThread(String name, String address, int port, String command) {
    super(name);
    this.address = address;
    this.port = port;
    this.command = command;
  }

  @Override
  public void run() {
    if (!(command.equals("exit"))) {
      try (Socket socket = new Socket(InetAddress.getByName(address), port);
          BufferedReader receiveBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintWriter sendBuffer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {
        receiveBuffer.readLine(); // This is needed just to procees further

        sendBuffer.println(command);

        System.out.println(getName() + ") Response: " + receiveBuffer.readLine());
      } catch (Exception e) {
        System.err.println(getName() + " error");
      }
    }
  }
}
