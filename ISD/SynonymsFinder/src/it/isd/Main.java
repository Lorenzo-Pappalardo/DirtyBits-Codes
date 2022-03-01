package it.isd;

import it.isd.test.SimilarityTest;
import it.isd.threads.MapperThread;
import it.isd.threads.SimilarityEvaluatorThread;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
  static String[] variablesNames = {"baseFilePath", "baseDelimiter", "baseColumns", "dictionaryFilepath", "dictionaryDelimiter", "dictionaryColumns", "stopWordsPath"};

  /**
   * Reads inputs from standard input and organizes them in a Map
   *
   * @param args Program arguments
   * @return {Map<String, String>} Map of inputs needed for correct program execution
   */
  public static Map<String, String> getInput(String[] args) {
    Map<String, String> inputMap = new HashMap<>();

    for (int i = 0; i < args.length; i++) {
      inputMap.put(variablesNames[i], args[i]);
    }

    final String propertiesFilepath = Paths.get(".").toAbsolutePath().normalize() + "\\src\\it\\isd\\resources\\launchArguments.properties";
    try (FileReader propertiesFile = new FileReader(propertiesFilepath)) {
      Properties properties = new Properties();
      properties.load(propertiesFile);

      for (String variableName : variablesNames) {
        final String value = properties.getProperty(variableName);
        if (value != null && !value.equals("")) {
          inputMap.put(variableName, value);
        }
      }
    } catch (IOException ignored) {
    }

    Scanner input = new Scanner(System.in);
    for (String variableName : variablesNames) {
      if (!inputMap.containsKey(variableName)) {
        try {
          System.out.println("Insert " + variableName + ':');
          inputMap.put(variableName, input.nextLine());
        } catch (NoSuchElementException e) {
          System.err.println("Input error");
          System.exit(1);
        }
      }
    }
    input.close();

    return inputMap;
  }

  /**
   * Reads stop words file and return a List of stop words, used to filter out meaningless words
   *
   * @param stopWordsPath {Path} Path to the stop words file
   * @return {List<String>} List of stop words
   */
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

  /**
   * Extracts maps of each dictionary partials files and puts them all in a List
   *
   * @param newFilesPaths       {List<String>} List of all dictionary partials files
   * @param dictionaryDelimiter {String} Symbol delimiting each dictionary record
   * @param dictionaryColumns   {int[]} Array of exactly 2 int, the first representing the column containing the key and the second the value
   * @param stopWords           {List<String>} List of stop words
   * @return {List<Map<String, String>>} List of Map containing the records taken from each dictionary partial file
   */
  private static List<Map<String, String>> getDictionaryMaps(List<Path> newFilesPaths, String dictionaryDelimiter, int[] dictionaryColumns, List<String> stopWords) {
    final List<Future<Map<String, String>>> futures = new ArrayList<>();

    final ExecutorService executor = Executors.newCachedThreadPool();
    for (Path filePath : newFilesPaths) {
      futures.add(executor.submit(new MapperThread("dictionary", filePath, dictionaryDelimiter, dictionaryColumns, stopWords)));
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

  /**
   * Evaluates Jaccard index from entries taken from the base map and the dictionary maps, then saves the result in a map
   *
   * @param baseMap        {Map<String, String>} Map containing records taken from the base file
   * @param dictionaryMaps {List<Map<String, String>>} List of Map containing the records taken from each dictionary partial file
   * @param jaccardMap     {Map<String, String>} Output Map in which the results are saved
   */
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
    final int[] baseColumns = Arrays.stream(inputMap.get(variablesNames[2]).split(",")).mapToInt(Integer::valueOf).toArray();
    final Path dictionaryFilepath = Path.of(inputMap.get(variablesNames[3]));
    final String dictionaryDelimiter = inputMap.get(variablesNames[4]);
    final int[] dictionaryColumns = Arrays.stream(inputMap.get(variablesNames[5]).split(",")).mapToInt(Integer::valueOf).toArray();
    final Path stopWordsPath = Path.of(inputMap.get(variablesNames[6]));

    final Date startDate = new Date();
    System.out.println("Started");

    // Preparation
    final List<Path> newFilesPaths = new Splitter(dictionaryFilepath, dictionaryDelimiter, 1000).splitFile();
    final List<String> stopWords = readStopWords(stopWordsPath);
    final Map<String, String> baseMap = new MapperThread("base_pairs", baseFilePath, baseDelimiter, baseColumns, stopWords).call();
    final List<Map<String, String>> dictionaryMaps = getDictionaryMaps(newFilesPaths, dictionaryDelimiter, dictionaryColumns, stopWords);

    // Tests
    new SimilarityTest(stopWords).test();

    // Results
    final Map<String, String> jaccardMap = Collections.synchronizedMap(new HashMap<>());
    getJaccardMap(baseMap, dictionaryMaps, jaccardMap);

    // Writing results
    new OutputWriter(Path.of(dictionaryFilepath.getParent() + "\\output\\output.csv")).writeMapToFile(jaccardMap);

    System.out.println("Finished in " + (new Date().getTime() - startDate.getTime()) + "ms");
  }
}
