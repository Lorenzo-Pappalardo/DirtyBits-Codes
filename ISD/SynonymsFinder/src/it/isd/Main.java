package it.isd;

import it.isd.threads.MapperThread;
import it.isd.threads.SimilarityEvaluatorThread;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;

public class Main {
  static String[] variablesNames = {"baseFilePath", "baseDelimiter", "dictionaryFilepath", "dictionaryDelimiter", "stopWordsPath"};

  public static Map<String, String> getInput(String[] args) {
    Map<String, String> inputMap = new HashMap<>();

    for (int i = 0; i < args.length; i++) {
      inputMap.put(variablesNames[i], args[i]);
    }

    for (String variablesName : variablesNames) {
      if (!inputMap.containsKey(variablesName)) {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
          System.out.println("Insert " + variablesName + ':');
          inputMap.put(variablesName, input.readLine());
        } catch (IOException e) {
          System.err.println("Input error");
          System.exit(1);
        }
      }
    }

    return inputMap;
  }

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
    Map<String, String> inputMap = getInput(args);

    Path baseFilePath = Path.of(inputMap.get(variablesNames[0]));
    String baseDelimiter = inputMap.get(variablesNames[1]);
    Path dictionaryFilepath = Path.of(inputMap.get(variablesNames[2]));
    String dictionaryDelimiter = inputMap.get(variablesNames[3]);
    Path stopWordsPath = Path.of(inputMap.get(variablesNames[4]));

    System.out.println("Started");

    // Preparation
    Splitter splitter = new Splitter(dictionaryFilepath, dictionaryDelimiter, 1000);
    List<Path> newFilesPaths = splitter.splitFile();

    List<String> stopWords = new ArrayList<>();
    stopWordsReader(stopWordsPath, stopWords);

    Map<String, String> baseMap = new MapperThread("base_pairs", baseFilePath, baseDelimiter, new int[]{1, 5}, stopWords).call();

    List<Future<Map<String, String>>> futures = new ArrayList<>();
    ExecutorService executor = Executors.newCachedThreadPool();

    for (Path filePath : newFilesPaths) {
      futures.add(executor.submit(new MapperThread("dictionary", filePath, dictionaryDelimiter, new int[]{0, 3}, stopWords)));
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

    int threadID= 0;
    for (Map<String, String> map : dictionaryMaps) {
      executor.submit(new SimilarityEvaluatorThread("SE" + ++threadID, baseMap, map, jaccardMap));
    }

    executor.shutdown();
    try {
      executor.awaitTermination(5, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      System.err.println("Timeout expired");
    }

    OutputWriter outputWriter = new OutputWriter(Path.of(dictionaryFilepath.getParent() + "\\output.txt"));
    outputWriter.writeMapToFile(jaccardMap);
  }
}
