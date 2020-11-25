package from_whisper_to_yell;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
  public static final int PORT = 8080;
  private static final int BUFFERSIZE = 1000;

  public static void main(String[] args) {
    try (DatagramSocket socket = new DatagramSocket(PORT);
        ByteArrayOutputStream bytesBuffer = new ByteArrayOutputStream();
        DataOutputStream stringBuffer = new DataOutputStream(bytesBuffer);) {
      System.out.println("Socket created");

      byte[] buffer = new byte[BUFFERSIZE];

      while (true) {
        DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);

        System.out.println("Waiting for packets...");
        socket.receive(receivedPacket);

        System.out.println("Received packet");
        System.out.println("from:" + receivedPacket.getAddress() + "\tport : " + receivedPacket.getPort());
        System.out.println();

        String packetData = new DataInputStream(new ByteArrayInputStream(receivedPacket.getData())).readUTF();

        if (packetData.equals("quit"))
          break;

        System.out.println("Received data: " + packetData);

        stringBuffer.writeUTF(packetData.toUpperCase() + '!');
        byte[] dataToSend = bytesBuffer.toByteArray();

        DatagramPacket packetToSend = new DatagramPacket(dataToSend, dataToSend.length, receivedPacket.getAddress(),
            receivedPacket.getPort());

        socket.send(packetToSend);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}