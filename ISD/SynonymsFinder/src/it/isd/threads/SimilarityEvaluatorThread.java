package it.isd.threads;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SimilarityEvaluatorThread implements Runnable {
  final Map<String, String> base;
  final Map<String, String> dictionary;
  final Map<String, String> outputMap;

  public SimilarityEvaluatorThread(Map<String, String> base, Map<String, String> dictionary, Map<String, String> outputMap) {
    this.base = base;
    this.dictionary = dictionary;
    this.outputMap = outputMap;
  }

  private float getJaccardIndex(String[] s1, String[] s2) {
    List<String> l2 = Arrays.stream(s2).toList();
    float commonWords = 0;

    for (String s1Word : s1) {
      if (l2.contains(s1Word)) {
        commonWords++;
      }
    }

    return (commonWords / (s1.length + s2.length - commonWords));
  }

  @Override
  public void run() {
    for (Map.Entry<String, String> baseEntry : base.entrySet()) {
      for (Map.Entry<String, String> dictionaryEntry : dictionary.entrySet()) {
        String resultKey = '(' + baseEntry.getKey() + ", " + dictionaryEntry.getKey() + ')';
        float resultValue = getJaccardIndex(baseEntry.getValue().split(" "), dictionaryEntry.getValue().split(" "));

        if (resultValue >= 0) {
          synchronized (outputMap) {
            outputMap.put(resultKey, String.valueOf(resultValue));
          }
        }
      }
    }
  }
}
