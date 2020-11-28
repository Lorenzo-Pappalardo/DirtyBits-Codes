package threads_giving_numbers.v1;

import java.util.Random;

public class MyThread extends Thread {
  private Random random;
  private Sample sample;

  MyThread(String name, Sample sample) {
    super(name);
    random = new Random();
    this.sample = sample;
  }

  @Override
  public void run() {
    while (true) {
      int newValue = random.nextInt(20) + 1;
      synchronized (sample) {
        if (sample.get() == newValue) {
          System.out.println(getName() + ": reads " + sample.get() + ", but new value is the same");
          System.out.println(getName() + " terminates");
          break;
        }
        System.out.println(getName() + ": reads " + sample.get() + ", writes " + newValue);
        sample.set(newValue);
      }
    }
  }
}
