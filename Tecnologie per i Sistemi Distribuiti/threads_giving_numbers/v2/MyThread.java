package threads_giving_numbers.v2;

import java.util.Random;

public class MyThread extends Thread {
  private Random random;

  MyThread(String name) {
    super(name);
    random = new Random();
  }

  private synchronized void doStuff() {
    while (true) {
      int newValue = random.nextInt(20) + 1;
      int before = Sample.set(newValue);
      if (before == newValue) {
        System.out.println(getName() + ": reads " + before + ", but new value is the same");
        System.out.println(getName() + " terminates");
        break;
      }
      System.out.println(getName() + ": reads " + before + ", writes " + newValue);
    }
  }

  @Override
  public void run() {
    try {
      sleep(random.nextInt(20));
    } catch (InterruptedException e) {
      e.printStackTrace();
      interrupt();
    }
    doStuff();
  }
}
