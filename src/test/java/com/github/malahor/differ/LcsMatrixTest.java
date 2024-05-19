package com.github.malahor.differ;

import com.github.malahor.differ.lcs.LcsMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class LcsMatrixTest {

  @Test
  void test1() {
    var result =
        new LcsMatrix(
                new String[] {
                  "Coding Challenges helps you become a better software engineer through that build real applications.",
                  "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                  "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology.",
                  "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities."
                },
                new String[] {
                  "Helping you become a better software engineer through coding challenges that build real applications.",
                  "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                  "These are challenges that I’ve used or am using as exercises to learn a new programming language or technology.",
                  "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities."
                })
            .lcs();
    Assertions.assertArrayEquals(
        new String[] {
          "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
          "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities."
        },
        result);
  }

  @Test
  void test2() {
    var result =
        new LcsMatrix(
                new String[] {
                  "Coding Challenges helps you become a better software engineer through that build real applications.",
                  "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                  "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology.",
                  "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities."
                },
                new String[] {
                  "Coding Challenges helps you become a better software engineer through that build real applications.",
                  "Helping you become a better software engineer through coding challenges that build real applications.",
                  "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                  "These are challenges that I’ve used or am using as exercises to learn a new programming language or technology.",
                  "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.",
                  "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology."
                })
            .lcs();
    Assertions.assertArrayEquals(
        new String[] {
          "Coding Challenges helps you become a better software engineer through that build real applications.",
          "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
          "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities."
        },
        result);
  }

  @Test
  void test3() {
    var result =
        new LcsMatrix(
                new String[] {
                  "Coding Challenges helps you become a better software engineer through that build real applications.",
                  "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.",
                  "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                  "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                  "Coding Challenges helps you become a better software engineer through that build real applications.",
                  "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology.",
                  "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.",
                  "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology."
                },
                new String[] {
                  "Coding Challenges helps you become a better software engineer through that build real applications.",
                  "Helping you become a better software engineer through coding challenges that build real applications.",
                  "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                  "These are challenges that I’ve used or am using as exercises to learn a new programming language or technology.",
                  "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.",
                  "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology."
                })
            .lcs();
    Assertions.assertArrayEquals(
        new String[] {
          "Coding Challenges helps you become a better software engineer through that build real applications.",
          "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
          "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.",
          "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology."
        },
        result);
  }
}
