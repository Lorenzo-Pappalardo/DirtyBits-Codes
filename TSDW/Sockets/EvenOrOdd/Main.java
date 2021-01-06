package EvenOrOdd;

public class Main {
  private static int n = 0;

  public static synchronized int getN() {
    return n;
  }

  public static synchronized void updateN(int increment) {
    n += increment;
  }

  public static void main(String[] args) {
    MyThread tE = new MyThread("tE", 0);
    MyThread tO = new MyThread("tO", 1);

    tE.start();
    tO.start();

    try {
      tE.join();
      tO.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }

    System.out.println("n final value: " + n);
  }
}