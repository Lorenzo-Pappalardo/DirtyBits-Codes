package CountingThreads;

public class MyThread extends Thread {
  private int cnt;

  MyThread() {
    super();
    cnt = 0;
  }

  @Override
  public void run() {
    while (CountingThreads.getX() < 300) {
      System.out.println(this.getName() + ": incrementing, X: " + CountingThreads.getX());
      CountingThreads.incrementX();
      cnt++;
    }
    System.out.println(this.getName() + " cnt value: " + cnt);
  }
}