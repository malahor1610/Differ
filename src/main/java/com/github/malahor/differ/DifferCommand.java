package com.github.malahor.differ;

import com.github.malahor.differ.lcs.LcsMatrix;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

@Command
public class DifferCommand {

  @Command(command = "differ", description = "Show differences between two files")
  public String differ(
      @Option(
              required = true,
              longNames = "file1",
              shortNames = '1',
              description = "First file's path",
              label = "FILE_PATH_1",
              arity = CommandRegistration.OptionArity.EXACTLY_ONE)
          String file1,
      @Option(
              required = true,
              longNames = "file2",
              shortNames = '2',
              description = "Second file's path",
              label = "FILE_PATH_2",
              arity = CommandRegistration.OptionArity.EXACTLY_ONE)
          String file2)
      throws IOException {
    return produceResult(file1, file2);
  }

  private String produceResult(String file1, String file2) throws IOException {
    var fileLines1 = Files.readAllLines(Path.of(file1));
    var fileLines2 = Files.readAllLines(Path.of(file2));
    var result = new LcsMatrix(fileLines1, fileLines2).diff();
    var joiner = new StringJoiner("\n");
    result.forEach(joiner::add);
    return joiner.toString();
  }
}
