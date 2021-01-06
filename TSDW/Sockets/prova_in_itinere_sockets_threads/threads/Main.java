package prova_in_itinere_sockets_threads.threads;

public class Main {
  public static void main(String[] args) {
    Thread1 t1 = new Thread1("t1");
    Thread2 t2 = new Thread2("t2");

    t1.start();
    t2.start();
  }
}
