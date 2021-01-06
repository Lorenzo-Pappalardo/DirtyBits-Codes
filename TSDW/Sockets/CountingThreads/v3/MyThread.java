package CountingThreads.v3;

import java.util.Random;

public class MyThread extends Thread {
  private int count;
  private X x = X.getInstance();

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

      synchronized (x) {
        int before = x.get();
        if (before >= 300) {
          System.out.println("X reached maximum value. Exiting...");
          break;
        }
        x.increment();
        count++;
        System.out.println(getName() + ":\tX: " + before + " --> " + x.get());
      }
    }

    System.out.println(getName() + ": Count: " + count);
  }
}