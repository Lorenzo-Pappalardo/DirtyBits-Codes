package acronym;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class Server_UDP {
  private static final int PORT = 8080;
  private static final int BUFFERSIZE = 8080;

  public static void main(String[] args) {
    System.out.println("Server started");

    try (DatagramSocket socket = new DatagramSocket(PORT);) {
      DatagramPacket incomingPacket = new DatagramPacket(new byte[BUFFERSIZE], BUFFERSIZE);
      socket.receive(incomingPacket);

      String name = extractName(incomingPacket);
      String explodedAcronym = explodeAcronym(name);

      byte[] buffer = explodedAcronym.getBytes();
      socket.send(new DatagramPacket(buffer, buffer.length, incomingPacket.getAddress(), incomingPacket.getPort()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String extractName(DatagramPacket packet) {
    String name = "ERROR";

    try (BufferedReader data = new BufferedReader(
        new InputStreamReader((new ByteArrayInputStream(packet.getData()))));) {
      name = data.readLine();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Received name: " + name);
    return name;
  }

  public static String explodeAcronym(String name) {
    Random random = new Random(System.currentTimeMillis());

    String res = "\r\n";

    for (int i = 0; i < name.length(); i++) {
      while (true) {
        int wordIndex = random.nextInt(Words.words.length);
        String selectedWord = Words.words[wordIndex];
        if (selectedWord.toLowerCase().charAt(0) == name.toLowerCase().charAt(i)) {
          res += "[" + name.charAt(i) + "]" + selectedWord.substring(1) + "\r\n";
          break;
        }
      }
    }

    return res;
  }
}