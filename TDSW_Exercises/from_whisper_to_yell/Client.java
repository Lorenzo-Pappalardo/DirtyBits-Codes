package from_whisper_to_yell;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
  private static final int BUFFERSIZE = 1000;

  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("You must pass server's ip address and port");
      System.exit(1);
    }

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Enter words with lowercase characters");
      String line = scanner.nextLine();

      try (DatagramSocket socket = new DatagramSocket();
          ByteArrayOutputStream bytesBuffer = new ByteArrayOutputStream();
          DataOutputStream stringBuffer = new DataOutputStream(bytesBuffer);) {
        stringBuffer.writeUTF(line);
        byte[] packetData = bytesBuffer.toByteArray();

        DatagramPacket packet = new DatagramPacket(packetData, packetData.length, InetAddress.getByName(args[0]),
            Integer.parseInt(args[1]));

        socket.send(packet);

        if (line.equalsIgnoreCase("quit")) {
          System.out.println("Quitting...");
          break;
        }

        byte[] receiveBuffer = new byte[BUFFERSIZE];
        DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        socket.receive(receivedPacket);

        String receivedData = new DataInputStream(new ByteArrayInputStream(receivedPacket.getData())).readUTF();

        System.out.println("Server: " + receivedData);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    scanner.close();
  }
}
