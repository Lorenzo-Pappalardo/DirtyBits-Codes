package prova_in_itinere_sockets_threads.threads;

import java.util.Random;

public class Thread1 extends MyThread {
  Thread1(String name) {
    super(name);
  }

  @Override
  public void run() {
    while (true) {
      synchronized (variable) {
        System.out.print(getName() + ") From: " + variable.getValue());
        variable.increment(new Random().nextInt(20));
        System.out.println("\t\t\tTo: " + variable.getValue());
      }

      super.run();
    }
  }
}
