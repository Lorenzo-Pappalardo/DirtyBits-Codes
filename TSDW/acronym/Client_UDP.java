package acronym;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client_UDP {
  private static final int BUFFERSIZE = 8080;

  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("You must pass server's ip adress and port");
      System.exit(1);
    }

    try (DatagramSocket socket = new DatagramSocket(); Scanner keyboardInput = new Scanner(System.in);) {
      System.out.print("Insert your name: ");
      String name = keyboardInput.nextLine();
      name += "\r\n";

      byte[] buffer = name.getBytes();
      socket.send(new DatagramPacket(buffer, buffer.length, InetAddress.getByName(args[0]), Integer.parseInt(args[1])));

      DatagramPacket incomingPacket = new DatagramPacket(new byte[BUFFERSIZE], BUFFERSIZE);
      socket.receive(incomingPacket);

      try (BufferedReader result = new BufferedReader(
          new InputStreamReader((new ByteArrayInputStream(incomingPacket.getData()))));) {
        String res;
        while ((res = result.readLine()) != null) {
          System.out.println(res);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
