package it.isd;

import it.isd.threads.MapperThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    List<Path> newFilesPaths = splitter.splitFile();

    MapperThread mapper = new MapperThread(baseWordsFilePath, ",");

    Date start = new Date();
    MapperThread dictionaryExtractor = new MapperThread(dictionaryFilepath, "}");
    dictionaryExtractor.call();
    System.out.println("Single thread: " + (new Date().getTime() - start.getTime()) + " ms");

    List<Future<Map<String, String>>> futures = new ArrayList<>();
    ExecutorService executor = Executors.newCachedThreadPool();

    start = new Date();
    newFilesPaths.forEach(filePath -> {
      futures.add(executor.submit(new MapperThread(filePath, "}")));
    });

    executor.shutdown();

    futures.forEach(future -> {
      try {
        future.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    });
    System.out.println("Multiple threads: " + (new Date().getTime() - start.getTime()) + " ms");
  }
}
