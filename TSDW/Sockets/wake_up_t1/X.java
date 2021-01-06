package wake_up_t1;

import java.util.Random;

public class X {
  private static X instance = null;
  private static final int VALUE = new Random().nextInt(11);
  public static final Object lock = new Object();

  private X() {
  }

  public static synchronized int get() {
    return VALUE;
  }

  public static X getInstance() {
    if (instance == null)
      instance = new X();
    return instance;
  }
}
