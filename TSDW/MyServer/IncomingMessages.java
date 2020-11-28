package MyServer;

import java.io.BufferedReader;
import java.io.IOException;

public class IncomingMessages extends Thread {
  private BufferedReader receiveBuffer;
  boolean stop;

  IncomingMessages(BufferedReader receiveBuffer) {
    super();
    this.receiveBuffer = receiveBuffer;
    stop = false;
  }

  public void terminate() {
    stop = true;
  }

  @Override
  public void run() {
    while (!stop) {
      try {
        String res = receiveBuffer.readLine();
        System.out.println("Server: " + res);
      } catch (IOException e) {
        System.err.println("Connection closed\n");
      }
    }
  }
}