package it.isd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Splitter {
  final Path pathToOriginalFile;
  final Path pathToOriginalFileDirectory;
  final int maxBytesPerFile;

  public Splitter(Path pathToOriginalFile, int maxBytesPerFile) {
    this.pathToOriginalFile = pathToOriginalFile;
    this.pathToOriginalFileDirectory = pathToOriginalFile.resolveSibling("").toAbsolutePath();
    this.maxBytesPerFile = maxBytesPerFile;
  }

  private Path createNewPartialFile(int id) {
    Path partialsDirectory = Path.of(pathToOriginalFileDirectory.toString() + "\\Partials");
    if (!Files.exists(partialsDirectory)) {
      try {
        Files.createDirectory(partialsDirectory);
      } catch (IOException e) {
        System.err.println("Error encountered while creating the partials directory");
        System.exit(1);
      }
    }

    Path tmpPath = Path.of(partialsDirectory.toString() + "\\" + pathToOriginalFile.getFileName().toString() + '_' + id + ".txt");

    try {
      Files.createFile(tmpPath);
    } catch (FileAlreadyExistsException ignored) {
    } catch (IOException e) {
      System.err.println("Error encountered while creating the partial file " + tmpPath);
      System.exit(2);
    }

    return tmpPath;
  }

  public List<Path> splitFile() {
    List<Path> newFilesNames = new ArrayList<>();
    int newFilesIndex = 0;

    try (RandomAccessFile inputFile = new RandomAccessFile(pathToOriginalFile.toString(), "rw"); FileChannel inputChannel = inputFile.getChannel()) {
      int bufferSize = maxBytesPerFile > inputChannel.size() ? (int)inputChannel.size() : maxBytesPerFile;
      ByteBuffer buffer = ByteBuffer.allocate(bufferSize);

      while (inputChannel.read(buffer) > 0) {
        newFilesNames.add(createNewPartialFile(newFilesIndex + 1));
        RandomAccessFile outputFile = new RandomAccessFile(newFilesNames.get(newFilesIndex).toFile(), "rw");
        outputFile.write(buffer.array());
        buffer.clear();
        newFilesIndex++;
      }
    } catch (FileNotFoundException fnf) {
      System.err.println("File " + pathToOriginalFile + " not found");
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

    return newFilesNames;
  }
}
