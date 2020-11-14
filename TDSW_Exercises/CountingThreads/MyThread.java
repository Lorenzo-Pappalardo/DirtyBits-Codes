package CountingThreads;

public class MyThread extends Thread {
  private int count;

  MyThread(String name) {
    super(name);
    count = 0;
  }

  @Override
  public void run() {
    while (CountingThreads.getX() < 300) {
      /* if (getName().equals("T1"))
        System.out.println(getName() + "| X = " + CountingThreads.getX());
      else
        System.out.println("\t\t\t" + getName() + "| X = " + CountingThreads.getX()); */

      CountingThreads.incrementX();
      count++;

      try {
        sleep((long) Math.random() * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
    }
    System.out.println(this.getName() + "'s count value: " + count);
  }
}