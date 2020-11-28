package up_or_down;

public class Main {
  public static void main(String[] args) {
    Thread t1 = new Thread(new MyThreadT1("T1"));
    Thread t2 = new Thread(new MyThreadT2("T2"));

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }

    System.out.println("\nThreads finished");
    System.out.println("N's final value: " + N.getInstance().get());
  }
}