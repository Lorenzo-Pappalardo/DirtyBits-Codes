package it.isd;

import it.isd.threads.MapperThread;
import it.isd.threads.SimilarityEvaluatorThread;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;

public class Main {
  private static void stopWordsReader(Path stopWordsPath, List<String> stopWords) {
    try (LineNumberReader reader = new LineNumberReader(new FileReader(stopWordsPath.toString()))) {
      while (reader.ready()) {
        stopWords.add(reader.readLine());
      }
    } catch (FileNotFoundException e) {
      System.err.println("File not found");
      System.exit(3);
    } catch (IOException e) {
      System.err.println("Error encountered while reading stop words: " + stopWordsPath);
    }
  }

  public static void main(String[] args) {
    Path baseFilePath = null;
    Path dictionaryFilepath = null;
    Path stopWordsPath = null;

    if (args.length >= 3) {
      baseFilePath = Path.of(args[0]);
      dictionaryFilepath = Path.of(args[1]);
      stopWordsPath = Path.of(args[2]);
    }

    if (baseFilePath == null || dictionaryFilepath == null || stopWordsPath == null) {
      try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
        if (baseFilePath == null) {
          System.out.println("Insert path to the file which holds the base words:");
          baseFilePath = Path.of(input.readLine());
        }
        if (dictionaryFilepath == null) {
          System.out.println("Insert path to the dictionary file:");
          dictionaryFilepath = Path.of(input.readLine());
        }
        if (stopWordsPath == null) {
          System.out.println("Insert path to the stop words file:");
          stopWordsPath = Path.of(input.readLine());
        }
      } catch (IOException e) {
        System.err.println("Input error");
        System.exit(1);
      }
    }

    System.out.println("Started");

    // Preparation
    Splitter splitter = new Splitter(dictionaryFilepath, ";", 1000);
    List<Path> newFilesPaths = splitter.splitFile();

    List<String> stopWords = new ArrayList<>();
    stopWordsReader(stopWordsPath, stopWords);

    Map<String, String> baseMap = new MapperThread("base_pairs", baseFilePath, ",", new int[]{1, 5}, stopWords).call();

    List<Future<Map<String, String>>> futures = new ArrayList<>();
    ExecutorService executor = Executors.newCachedThreadPool();

    for (Path filePath : newFilesPaths) {
      futures.add(executor.submit(new MapperThread("dictionary", filePath, ";", new int[]{0, 3}, stopWords)));
    }

    List<Map<String, String>> dictionaryMaps = new ArrayList<>();

    futures.forEach(future -> {
      try {
        dictionaryMaps.add(future.get());
      } catch (InterruptedException | ExecutionException e) {
        System.err.println("Error getting dictionary map from Future");
      }
    });

    Map<String, String> jaccardMap = Collections.synchronizedMap(new HashMap<>());

    List<SimilarityEvaluatorThread> callables = dictionaryMaps.stream().map(map -> new SimilarityEvaluatorThread(baseMap, map, jaccardMap)).toList();

    List<Future<Boolean>> jaccardFutures;

    try {
      jaccardFutures = executor.invokeAll(callables);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    executor.shutdown();

    OutputWriter outputWriter = new OutputWriter(Path.of(dictionaryFilepath.resolveSibling("").toAbsolutePath() + "\\output.txt"));
    outputWriter.writeMapToFile(jaccardMap);
  }
}
