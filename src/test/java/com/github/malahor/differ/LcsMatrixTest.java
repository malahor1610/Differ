package com.github.malahor.differ;

import com.github.malahor.differ.lcs.LcsMatrix;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class LcsMatrixTest {

  @Test
  void happyPath() {
    var result =
        new LcsMatrix(
                Arrays.asList(
                    "Coding Challenges helps you become a better software engineer through that build real applications.",
                    "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                    "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology.",
                    "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities."),
                Arrays.asList(
                    "Helping you become a better software engineer through coding challenges that build real applications.",
                    "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                    "These are challenges that I’ve used or am using as exercises to learn a new programming language or technology.",
                    "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities."))
            .diff();
    Assertions.assertArrayEquals(
        new String[] {
          "1d - Coding Challenges helps you become a better software engineer through that build real applications.",
          "1a - Helping you become a better software engineer through coding challenges that build real applications.",
          "3d - I’ve used or am using these coding challenges as exercise to learn a new programming language or technology.",
          "3a - These are challenges that I’ve used or am using as exercises to learn a new programming language or technology."
        },
        result.toArray(String[]::new));
  }

  @Test
  void differentLengths() {
    var result =
        new LcsMatrix(
                Arrays.asList(
                    "Coding Challenges helps you become a better software engineer through that build real applications.",
                    "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                    "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology.",
                    "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities."),
                Arrays.asList(
                    "Coding Challenges helps you become a better software engineer through that build real applications.",
                    "Helping you become a better software engineer through coding challenges that build real applications.",
                    "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                    "These are challenges that I’ve used or am using as exercises to learn a new programming language or technology.",
                    "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.",
                    "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology."))
            .diff();
    Assertions.assertArrayEquals(
        new String[] {
          "2a - Helping you become a better software engineer through coding challenges that build real applications.",
          "3d - I’ve used or am using these coding challenges as exercise to learn a new programming language or technology.",
          "4a - These are challenges that I’ve used or am using as exercises to learn a new programming language or technology.",
          "6a - I’ve used or am using these coding challenges as exercise to learn a new programming language or technology."
        },
        result.toArray(String[]::new));
  }

  @Test
  void mixedUpLines() {
    var result =
        new LcsMatrix(
                Arrays.asList(
                    "Coding Challenges helps you become a better software engineer through that build real applications.",
                    "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.",
                    "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                    "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                    "Coding Challenges helps you become a better software engineer through that build real applications.",
                    "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology.",
                    "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.",
                    "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology."),
                Arrays.asList(
                    "Coding Challenges helps you become a better software engineer through that build real applications.",
                    "Helping you become a better software engineer through coding challenges that build real applications.",
                    "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                    "These are challenges that I’ve used or am using as exercises to learn a new programming language or technology.",
                    "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.",
                    "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology."))
            .diff();
    Assertions.assertArrayEquals(
        new String[] {
          "2d - Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.",
          "3d - I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
          "2a - Helping you become a better software engineer through coding challenges that build real applications.",
          "5d - Coding Challenges helps you become a better software engineer through that build real applications.",
          "6d - I’ve used or am using these coding challenges as exercise to learn a new programming language or technology.",
          "4a - These are challenges that I’ve used or am using as exercises to learn a new programming language or technology."
        },
        result.toArray(String[]::new));
  }
}
