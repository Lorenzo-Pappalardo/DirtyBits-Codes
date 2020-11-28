package CountingThreads.v1;

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

      int before = CountingThreads.incrementCount();
      count++;

      if (before >= 299) {
        System.out.println("X reached maximum value. Exiting...");
        break;
      }
      System.out.println(getName() + ":\tX: " + before + " --> " + (before + 1));
    }

    System.out.println(getName() + ": Count: " + count);
  }
}