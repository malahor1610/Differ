# Differ - Build Your Own diff Tool

Differ is a shell application created based on a challenge #13 of [CodingChallenges](https://codingchallenges.fyi/challenges/challenge-diff) by John Crickett.
Application is implemented in Java using Spring Boot with Spring Shell.

## Description

This challenge is to build your own version of a diff command. The diff utility compares the contents of file1 and file2 and writes to the standard output the list of changes necessary to convert one file into the other.

Differ implements only a basic functionality of the original tool:

- takes two arguments - paths of two files
- returns list of lines necessary to edit for files to match:
    - from the first file to delete
    - from the second file to add

## Requirements

- Java (+ GraalVM) (JDK 21)
- Git
- PowerShell (or other shell program - command execution / path resolving might be different)

## Usage

Clone the repository
```bash
git clone https://github.com/malahor1610/Differ.git
```
In the project directory (where the differ.exe file is) open PowerShell (or other shell program) window

Run command:
```bash
.\differ.exe -1 ./src/main/resources/file1.txt -2 ./src/main/resources/file2.txt
```

Result should be (skipping Spring logo):
```bash
2d - Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.
3d - I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.
2a - Helping you become a better software engineer through coding challenges that build real applications.
5d - Coding Challenges helps you become a better software engineer through that build real applications.
6d - Iĺve used or am using these coding challenges as exercise to learn a new programming language or technology.
4a - These are challenges that Iĺve used or am using as exercises to learn a new programming language or technology.
```

Command description:

    NAME differ - Show differences between two files

    SYNOPSIS differ [--file1 FILE_PATH_1] [--file2 FILE_PATH_2] --help

    OPTIONS
    --file1 or -1 FILE_PATH_1
    First file's path
    [Mandatory]

    --file2 or -2 FILE_PATH_2
    Second file's path
    [Mandatory]

    --help or -h
    help for differ
    [Optional]


Result description:

    Nd e.g. 2d - second line in file1 should be deleted to match content of file2
    Na e.g. 4a - fourth line in file2 should be added to file1 so that it match content from file2

## Adjustment

After applying some changes to a project, it can be compiled.
It is also possible to create a new exe file based on a project. To do this:

Install GraalVM and set up JAVA_HOME pointing to it.
In a project directory execute command:

```bash
.\mvnw.cmd native:compile -Pnative -DskipTests
```

After some time, the exe file should be created inside the target directory. 