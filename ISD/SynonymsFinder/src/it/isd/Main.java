package it.isd;

import it.isd.threads.MapperThread;
import it.isd.threads.SimilarityEvaluatorThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
  public static void main(String[] args) {
    Path baseFilePath = null;
    Path dictionaryFilepath = null;

    if (args.length >= 2) {
      baseFilePath = Path.of(args[0]);
      dictionaryFilepath = Path.of(args[1]);
    }

    if (baseFilePath == null || dictionaryFilepath == null) {
      try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
        if (baseFilePath == null) {
          System.out.println("Insert path to the file which holds the base words:");
          baseFilePath = Path.of(input.readLine());
        }
        if (dictionaryFilepath == null) {
          System.out.println("Insert path to the dictionary file:");
          dictionaryFilepath = Path.of(input.readLine());
        }
      } catch (IOException e) {
        System.err.println("Input error");
        System.exit(1);
      }
    }

    Splitter splitter = new Splitter(dictionaryFilepath, "}", 1000);
    List<Path> newFilesPaths = splitter.splitFile();

    Map<String, String> baseMap = new MapperThread("base_pairs", baseFilePath, ",").call();

    List<Future<Map<String, String>>> futures = new ArrayList<>();
    ExecutorService executor = Executors.newCachedThreadPool();

    newFilesPaths.forEach(filePath -> futures.add(executor.submit(new MapperThread("dictionary", filePath, "}"))));

    List<Map<String, String>> dictionaryMaps = new ArrayList<>();

    futures.forEach(future -> {
      try {
        dictionaryMaps.add(future.get());
      } catch (InterruptedException | ExecutionException e) {
        System.err.println("Error getting Map from Future");
      }
    });

    OutputWriter outputWriter = new OutputWriter(Path.of(dictionaryFilepath.resolveSibling("").toAbsolutePath() + "\\output.txt"));

    dictionaryMaps.forEach(map -> executor.submit(new SimilarityEvaluatorThread(baseMap, map, outputWriter)));
  }
}
