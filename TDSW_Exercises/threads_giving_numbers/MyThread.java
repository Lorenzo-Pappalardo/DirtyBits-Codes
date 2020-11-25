package threads_giving_numbers;

import java.util.Random;

public class MyThread extends Thread {
  private Random random;
  private Sample sample;

  MyThread(String name, Sample sample) {
    super(name);
    random = new Random(System.currentTimeMillis());
    this.sample = sample;
  }

  @Override
  public void run() {
    while (true) {
      int newValue = random.nextInt(20) + 1;
      synchronized (sample) {
        if (sample.get() == newValue)
          break;
        System.out.println(getName() + ": reads " + sample.get() + ", writes " + newValue);
        sample.set(newValue);
      }
    }
    System.out.println(getName() + " terminated");
  }
}
