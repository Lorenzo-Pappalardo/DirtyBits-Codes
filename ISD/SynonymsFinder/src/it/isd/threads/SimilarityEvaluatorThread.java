package it.isd.threads;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class SimilarityEvaluatorThread extends WorkerThread implements Callable<Boolean> {
  final Map<String, String> base;
  final Map<String, String> dictionary;
  final Map<String, String> outputMap;

  public SimilarityEvaluatorThread(String threadID, Map<String, String> base, Map<String, String> dictionary, Map<String, String> outputMap) {
    super(threadID);
    this.base = base;
    this.dictionary = dictionary;
    this.outputMap = outputMap;
  }

  /**
   * Calculates Jaccard index based on two descriptions
   * @param s1 Array of words that make up the first description
   * @param s2 Array of words that make up the second description
   * @return {float} The calculated Jaccard Index
   */
  private float getJaccardIndex(String[] s1, String[] s2) {
    List<String> l2 = Arrays.stream(s2).toList();
    Set<String> commonWords = new HashSet<>();

    for (String s1Word : s1) {
      if (l2.contains(s1Word)) {
        commonWords.add(s1Word);
      }
    }

    return ((float) commonWords.size() / (s1.length + s2.length - commonWords.size()));
  }

  @Override
  public Boolean call() {
    int iterations = 0;
    int totalEntries = base.size();

    for (Map.Entry<String, String> baseEntry : base.entrySet()) {
      for (Map.Entry<String, String> dictionaryEntry : dictionary.entrySet()) {
        String resultKey = baseEntry.getKey() + ", " + dictionaryEntry.getKey();
        float resultValue = getJaccardIndex(baseEntry.getValue().split(" "), dictionaryEntry.getValue().split(" "));

        if (resultValue > 0) {
          synchronized (outputMap) {
            outputMap.put(resultKey, String.valueOf(resultValue));
          }
        }
      }

      int percentage = (100 * ++iterations) / totalEntries;
      if (percentage == 25 || percentage == 50 || percentage == 75 || percentage == 100) {
        System.out.println(threadID + " progress: " + percentage + '%');
      }
    }

    return true;
  }
}
