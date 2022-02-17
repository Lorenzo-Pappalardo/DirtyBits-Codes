package it.isd;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class OutputWriter {
  private FileChannel outputFile = null;

  OutputWriter(Path outputFilePath) {
    try {
      if (!Files.exists(outputFilePath.getParent())) {
        Files.createDirectory(outputFilePath.getParent());
      }

      this.outputFile = FileChannel.open(outputFilePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    } catch (IOException e) {
      System.err.println("Unable to open output file");
    }
  }

  public void writeMapsToFile(List<Map<String, String>> maps) {
    for (Map<String, String> map : maps) {
      for (Map.Entry<String, String> entry : map.entrySet()) {
        try {
          Optional<String> values = Arrays.stream((entry.getValue().split(" "))).reduce((res, value) -> res += " " + value);
          outputFile.write(ByteBuffer.wrap((entry.getKey() + ", " + (values.orElse("")) + "\n").getBytes(StandardCharsets.UTF_8)));
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

  public void writeMapToFile(Map<String, String> map) {
    List<Map.Entry<String, String>> ordered = map.entrySet().stream().sorted(Map.Entry.<String, String>comparingByValue().reversed()).toList();

    ordered.forEach(entry -> {
      try {
        Optional<String> values = Arrays.stream((entry.getValue().split(" "))).reduce((res, value) -> res += ", " + value);
        outputFile.write(ByteBuffer.wrap((entry.getKey() + ", " + (values.orElse("")) + "\n").getBytes(StandardCharsets.UTF_8)));
      } catch (IOException e) {
        System.err.println("Error writing in output file");
      }
    });

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
