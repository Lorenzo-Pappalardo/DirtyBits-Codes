package talking_threads;

import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("You must pass server's address and port");
      System.exit(1);
    }

    Scanner keyboardInput = new Scanner(System.in);

    System.out.print("Insert first command:");
    String command1 = keyboardInput.nextLine();

    System.out.print("Insert second command:");
    String command2 = keyboardInput.nextLine();

    System.out.print("Insert third command:");
    String command3 = keyboardInput.nextLine();

    MyThread t1 = new MyThread("T1", args[0], Integer.parseInt(args[1]), command1);
    MyThread t2 = new MyThread("T2", args[0], Integer.parseInt(args[1]), command2);
    MyThread t3 = new MyThread("T3", args[0], Integer.parseInt(args[1]), command3);

    t1.start();
    t2.start();
    t3.start();

    try {
      t1.join();
      t2.join();
      t3.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }
}
