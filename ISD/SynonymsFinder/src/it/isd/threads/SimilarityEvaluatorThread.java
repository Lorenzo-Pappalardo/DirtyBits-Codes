package it.isd.threads;

import it.isd.OutputWriter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SimilarityEvaluatorThread implements Runnable {
  Map<String, String> base;
  Map<String, String> dictionary;
  OutputWriter output;

  public SimilarityEvaluatorThread(Map<String, String> base, Map<String, String> dictionary, OutputWriter output) {
    this.base = base;
    this.dictionary = dictionary;
    this.output = output;
  }

  private String getJaccardIndex(String[] s1, String[] s2) {
    List<String> l2 = Arrays.stream(s2).toList();
    int commonWords = 0;

    for (String s1Word : s1) {
      if (l2.contains(s1Word)) {
        commonWords++;
      }
    }

    return String.valueOf(commonWords / (s1.length + s2.length - commonWords));
  }

  @Override
  public void run() {
    for (Map.Entry<String, String> baseEntry : base.entrySet()) {
      for (Map.Entry<String, String> dictionaryEntry : dictionary.entrySet()) {
        String resultKey = '(' + baseEntry.getKey() + ", " + dictionaryEntry.getKey() + ')';
        String resultValue = getJaccardIndex(baseEntry.getKey().split(" "), dictionaryEntry.getKey().split(" "));

        output.writeKeyValuePairToFile(resultKey, resultValue);
      }
    }
  }
}
