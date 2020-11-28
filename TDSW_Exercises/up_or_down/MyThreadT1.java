package up_or_down;

import java.util.Random;

public class MyThreadT1 extends MyThread implements Runnable {
  MyThreadT1(String name) {
    super(name);
  }

  @Override
  public void run() {
    while (true) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }

      synchronized (n) {
        System.out.print(getName() + " | Before: " + n.get());
        n.increase(new Random().nextInt(10));
        System.out.print("\tAfter: " + n.get());

        iterations++;
        System.out.println("\tIterations: " + iterations);

        if (n.get() > 150 && iterations < 1000) {
          System.out.println(getName() + " reached its goal and stopped");
          break;
        }
      }
    }
  }
}