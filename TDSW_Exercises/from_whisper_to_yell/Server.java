package from_whisper_to_yell;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
  public static final int PORT = 8080;
  private static final int BUFFERSIZE = 1000;

  public static void main(String[] args) {
    try (DatagramSocket socket = new DatagramSocket(PORT);) {
      System.out.println("Socket created" + socket);

      byte[] buffer = new byte[BUFFERSIZE];

      while (true) {
        DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);

        socket.receive(receivedPacket);

        System.out.println("Received packet" + receivedPacket);

        System.out.println(receivedPacket.getData());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}