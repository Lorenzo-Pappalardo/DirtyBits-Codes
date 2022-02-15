package it.isd;

import it.isd.test.SimilarityTest;
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

  private static List<String> readStopWords(Path stopWordsPath) {
    List<String> toReturn = new ArrayList<>();

    try (LineNumberReader reader = new LineNumberReader(new FileReader(stopWordsPath.toString()))) {
      while (reader.ready()) {
        toReturn.add(reader.readLine());
      }
    } catch (FileNotFoundException e) {
      System.err.println("File not found");
      System.exit(3);
    } catch (IOException e) {
      System.err.println("Error encountered while reading stop words: " + stopWordsPath);
    }

    return toReturn;
  }

  private static List<Map<String, String>> getDictionaryMaps(List<Path> newFilesPaths, String dictionaryDelimiter, List<String> stopWords) {
    final List<Future<Map<String, String>>> futures = new ArrayList<>();

    final ExecutorService executor = Executors.newCachedThreadPool();
    for (Path filePath : newFilesPaths) {
      futures.add(executor.submit(new MapperThread("dictionary", filePath, dictionaryDelimiter, new int[]{0, 3}, stopWords)));
    }

    executor.shutdown();
    try {
      executor.awaitTermination(5, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      System.err.println("Timeout expired");
    }

    final List<Map<String, String>> dictionaryMaps = new ArrayList<>();
    futures.forEach(future -> {
      try {
        dictionaryMaps.add(future.get());
      } catch (InterruptedException | ExecutionException e) {
        System.err.println("Error getting dictionary map from Future");
      }
    });

    return dictionaryMaps;
  }

  private static void getJaccardMap(Map<String, String> baseMap, List<Map<String, String>> dictionaryMaps, Map<String, String> jaccardMap) {
    final ExecutorService executor = Executors.newCachedThreadPool();

    int threadID = 0;
    for (Map<String, String> map : dictionaryMaps) {
      executor.submit(new SimilarityEvaluatorThread("SE" + ++threadID, baseMap, map, jaccardMap));
    }

    executor.shutdown();
    try {
      executor.awaitTermination(5, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      System.err.println("Timeout expired");
    }
  }

  public static void main(String[] args) {
    final Map<String, String> inputMap = getInput(args);

    final Path baseFilePath = Path.of(inputMap.get(variablesNames[0]));
    final String baseDelimiter = inputMap.get(variablesNames[1]);
    final Path dictionaryFilepath = Path.of(inputMap.get(variablesNames[2]));
    final String dictionaryDelimiter = inputMap.get(variablesNames[3]);
    final Path stopWordsPath = Path.of(inputMap.get(variablesNames[4]));

    final Date startDate = new Date();
    System.out.println("Started");

    // Preparation
    final List<Path> newFilesPaths = new Splitter(dictionaryFilepath, dictionaryDelimiter, 1000).splitFile();
    final List<String> stopWords = readStopWords(stopWordsPath);
    final Map<String, String> baseMap = new MapperThread("base_pairs", baseFilePath, baseDelimiter, new int[]{1, 5}, stopWords).call();
    final List<Map<String, String>> dictionaryMaps = getDictionaryMaps(newFilesPaths, dictionaryDelimiter, stopWords);

    // Results
    final Map<String, String> jaccardMap = Collections.synchronizedMap(new HashMap<>());
    getJaccardMap(baseMap, dictionaryMaps, jaccardMap);

    // Writing results
    new OutputWriter(Path.of(dictionaryFilepath.getParent() + "\\output.txt")).writeMapToFile(jaccardMap);

    final Date endDate = new Date();
    System.out.println("Finished in " + (endDate.getTime() - startDate.getTime()) + "ms");

    // Tests
    new SimilarityTest(stopWords).test();
  }
}
