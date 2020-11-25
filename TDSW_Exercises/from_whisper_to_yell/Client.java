package from_whisper_to_yell;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("You must pass server's ip address and port");
      System.exit(1);
    }

    System.out.println("Enter words with lowercase characters");

    Scanner scanner = new Scanner(System.in);

    while (true) {
      String word = scanner.nextLine();
      if (word.equalsIgnoreCase("quit")) {
        System.out.println("Quitting...");
        break;
      }

      try (DatagramSocket socket = new DatagramSocket();
        ByteArrayOutputStream bytesBuffer = new ByteArrayOutputStream();
          DataOutputStream stringBuffer = new DataOutputStream(bytesBuffer);) {
        stringBuffer.writeUTF(scanner.nextLine());
        byte[] packetData = bytesBuffer.toByteArray();

        DatagramPacket packet = new DatagramPacket(packetData, packetData.length, InetAddress.getByName(args[0]),
            Integer.parseInt(args[1]));
            
            socket.send(packet);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
