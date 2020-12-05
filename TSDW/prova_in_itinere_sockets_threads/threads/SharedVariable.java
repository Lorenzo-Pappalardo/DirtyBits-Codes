package prova_in_itinere_sockets_threads.threads;

public class SharedVariable {
  private static SharedVariable instance;
  private int variable;

  private SharedVariable() {
    variable = 0;
  }

  public synchronized int getValue() {
    return variable;
  }

  public synchronized void increment(int value) {
    variable += value;

    notifyAll();

    if (variable > 200) {
      System.out.println("\nVariable higher than 200, waiting\n");
      try {
        wait();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public synchronized void decrement(int value) {
    variable -= value;

    notifyAll();

    if (variable < 20) {
      System.out.println("\nVariable less than 20, waiting\n");
      try {
        wait();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static SharedVariable getInstance() {
    if (instance == null)
      instance = new SharedVariable();
    return instance;
  }
}
