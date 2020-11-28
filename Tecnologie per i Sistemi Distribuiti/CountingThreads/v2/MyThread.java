package CountingThreads.v2;

import java.util.Random;

public class MyThread extends Thread {
  private int count;

  MyThread(String name) {
    super(name);
    count = 0;
  }

  @Override
  public void run() {
    while (true) {
      try {
        sleep(new Random().nextInt(50));
      } catch (Exception e) {
        e.printStackTrace();
      }

      int before = CountingThreads.getX();
      if (before >= 300) {
        System.out.println("X reached maximum value. Exiting...");
        break;
      }
      CountingThreads.incrementX();
      count++;
      System.out.println(getName() + ":\tX: " + before + " --> " + CountingThreads.getX());
    }

    System.out.println(getName() + ": Count: " + count);
  }
}