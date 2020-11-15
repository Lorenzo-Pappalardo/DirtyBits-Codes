package CountingThreads;

import java.util.Random;

public class MyThread extends Thread {
  private int count;

  MyThread(String name) {
    super(name);
    count = 0;
  }

  @Override
  public void run() {
    while (CountingThreads.getX() < 300) {
      CountingThreads.incrementX();
      count++;

      if (getName().equals("T1"))
        System.out.println(getName() + "| X = " + CountingThreads.getX());
      else
        System.out.println("\t\t\t" + getName() + "| X = " + CountingThreads.getX());

      Random random = new Random();
      try {
        sleep(random.nextInt(50));
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
    }
    System.out.println(this.getName() + "'s count value: " + count);
  }
}