package it.isd;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

public class OutputWriter {
  private FileChannel outputFile = null;

  OutputWriter(Path outputFilePath) {
    try {
      this.outputFile = FileChannel.open(outputFilePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    } catch (IOException e) {
      System.err.println("Unable to open output file");
    }
  }

  public void writeMapKeysToFile(List<Map<String, String>> maps) {
    for (Map<String, String> map : maps) {
      for (String key : map.keySet()) {
        try {
          outputFile.write(ByteBuffer.wrap((key + "\n").getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
          System.err.println("Error writing in output file");
        }
      }
    }

    try {
      outputFile.close();
    } catch (IOException e) {
      System.err.println("Error closing the output file");
    }
  }

  public void writeKeyValuePairToFile(String key, String value) {
    try {
      outputFile.write(ByteBuffer.wrap((key + " -> " + value + "\n").getBytes(StandardCharsets.UTF_8)));
    } catch (IOException e) {
      System.err.println("Error writing in output file");
    }
  }
}
