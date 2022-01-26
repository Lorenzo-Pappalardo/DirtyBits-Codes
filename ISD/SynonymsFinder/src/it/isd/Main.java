package it.isd;

import it.isd.threads.ReaderThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) {
    Path baseWordsFilePath = null;
    Path dictionaryFilepath = null;

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.println("Insert path to the file which holds the base words:");
      baseWordsFilePath = Path.of(input.readLine());
      System.out.println("Insert path to the dictionary file:");
      dictionaryFilepath = Path.of(input.readLine());
    } catch (IOException e) {
      System.err.println("Input error");
      System.exit(1);
    }

    Splitter splitter = new Splitter(dictionaryFilepath, "}", 1000);
    List<Path> newFilesNames = splitter.splitFile();

    /*ExecutorService executor = Executors.newFixedThreadPool(8);

    for (int i = 0; i < 8; i++) {
      executor.execute(new ReaderThread(Integer.toString(i + 1), newFilesNames.get(i)));
    }

    executor.shutdown();*/

    KeyValueExtractor kve = new KeyValueExtractor(baseWordsFilePath, ",");

    kve.getKeyValue().forEach((key, value) -> System.out.println(key + ": " + value));
  }
}
