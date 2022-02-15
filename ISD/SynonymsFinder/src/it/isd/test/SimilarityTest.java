package it.isd.test;

import it.isd.threads.SimilarityEvaluatorThread;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimilarityTest {
  Map<String, String> base;
  Map<String, String> dictionary;
  Map<String, String> outputMap;
  List<String> stopWords;

  public SimilarityTest(List<String> stopWords) {
    base = new HashMap<>();
    dictionary = new HashMap<>();
    outputMap = new HashMap<>();
    this.stopWords = stopWords;

    base.put("Parkinsonism", "A Parkinsonism that is characterized by postural instability, a broad-based gait with the absence of tremors of vascular origin.");
    dictionary.put("muscular disease", "A neuromuscular disease that is characterized by an abnormal reduction in the muscle volume and atrophy.");
    removeStopsWords();
  }

  private void removeStopsWords() {
    base.entrySet().forEach(entry -> {
      String[] wordsArray = entry.getValue().split(" ");
      String newValue = Arrays.stream(wordsArray).filter(word -> !stopWords.contains(word.toLowerCase())).collect(Collectors.joining(" "));
      entry.setValue(newValue);
    });

    dictionary.entrySet().forEach(entry -> {
      String[] wordsArray = entry.getValue().split(" ");
      String newValue = Arrays.stream(wordsArray).filter(word -> !stopWords.contains(word.toLowerCase())).collect(Collectors.joining(" "));
      entry.setValue(newValue);
    });
  }

  public void test() {
    SimilarityEvaluatorThread similarityEvaluatorThread = new SimilarityEvaluatorThread("1", base, dictionary, outputMap);
    similarityEvaluatorThread.call();

    outputMap.forEach((key, value) -> {
      if (value.equals(String.valueOf(1/17f))) {
        System.out.println("Passed");
      } else {
        System.out.println("Failed");
      }
    });
  }
}
