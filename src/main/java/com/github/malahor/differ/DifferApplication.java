package com.github.malahor.differ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.EnableCommand;

@SpringBootApplication
@EnableCommand(DifferCommand.class)
public class DifferApplication {

  public static void main(String[] args) {
    SpringApplication.run(DifferApplication.class, args);
  }
}
