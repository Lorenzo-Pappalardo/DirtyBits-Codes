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

    base.put("aplastic anemia", "An anemia that is characterized by a deficiency of red blood cells, white blood cells and platelets produced by bone marrow");
    dictionary.put("Babesia canis", "Babesia canis is a parasite that infects red blood cells and can lead to anemia");
    removePunctuation();
    removeStopsWords();
  }

  private void removePunctuation() {
    base.entrySet().forEach(entry -> entry.setValue(entry.getValue().replaceAll("[^\\w\\s]", "")));

    dictionary.entrySet().forEach(entry -> entry.setValue(entry.getValue().replaceAll("[^\\w\\s]", "")));
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
      if (value.equals(String.valueOf(4 / 18f))) {
        System.out.println("Passed");
      } else {
        System.out.println("Failed");
      }
    });
  }
}
