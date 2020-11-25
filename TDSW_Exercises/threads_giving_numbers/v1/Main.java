package threads_giving_numbers.v1;

/*
  Scrivere un programma in cui tre thread aggiornano la stessa variabile globale condivisa sample, inizialmente posta a 50.
  Ogni thread ciclicamente genera un numero casuale compreso tra 10 e 90 e prova a sovrascrivere il valore corrente di sample. 
  Il thread mostra in output un messaggio che segue il modello “Sono il thread X: sample valeva Y, adesso vale Z”, con opportuni valori al posto di X, Y e Z.
  Se il valore casuale generato è uguale al valore attuale di sample il thread termina il suo ciclo, comunicando in output la propria terminazione.
  Quando tutti i thread sono terminati, il programma principale termina e mostra in output il valore finale di sample.
*/

public class Main {
  private static Sample sample = new Sample();

  public static void main(String[] args) {
    MyThread t1 = new MyThread("T1", sample);
    MyThread t2 = new MyThread("T2", sample);
    MyThread t3 = new MyThread("T3", sample);

    t1.start();
    t2.start();
    t3.start();

    try {
      t1.join();
      t2.join();
      t3.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }

    System.out.println("\nAll threads have terminated!");
    System.out.println("Sample: " + sample.get());
  }
}