package com.github.malahor.differ;

import com.github.malahor.differ.lcs.LcsMatrix;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

@Command
public class DifferCommand {

  @Command(command = "differ", description = "Show differences between two files")
  public String[] differ(
      @Option(
              required = true,
              longNames = "file1",
              shortNames = '1',
              description = "First file's path",
              label = "PATH",
              arity = CommandRegistration.OptionArity.EXACTLY_ONE)
          String file1,
      @Option(
              required = true,
              longNames = "file2",
              shortNames = '2',
              description = "Second file's path",
              label = "PATH",
              arity = CommandRegistration.OptionArity.EXACTLY_ONE)
      String file2) {
    return new LcsMatrix(new String[]{file1}, new String[]{file2}).lcs();
  }
}
