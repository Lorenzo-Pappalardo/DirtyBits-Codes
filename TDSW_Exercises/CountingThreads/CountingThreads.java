package CountingThreads;

public class CountingThreads {
  private static int x = 0;

  public static synchronized int getX() {
    return x;
  }

  public static synchronized void incrementX() {
    x++;
  }

  public static void main(String[] args) {
    MyThread t1 = new MyThread("T1");
    MyThread t2 = new MyThread("T2");

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }

    System.out.println("X value: " + x);
  }
}