package com.github.malahor.differ.lcs;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LcsMatrixVisitor {

  public int lcsIndex;
  public int row;
  public int column;

  public boolean matrixHasPrevious() {
    return row > 0 && column > 0;
  }

  public void increaseCurrent(int[][] matrix) {
    matrix[row][column] = matrix[row - 1][column - 1] + 1;
  }

  public void copyPreviousValueToCurrent(int[][] matrix) {
    matrix[row][column] = Math.max(matrix[row - 1][column], matrix[row][column - 1]);
  }

  public void moveBack() {
    moveToPreviousRow();
    moveToPreviousColumn();
    previousCharacter();
  }

  public void moveBackToHigherValue(int[][] matrix) {
    if (matrix[row - 1][column] > matrix[row][column - 1]) moveToPreviousRow();
    else moveToPreviousColumn();
  }

  public void moveToPreviousRow() {
    row--;
  }

  public void moveToPreviousColumn() {
    column--;
  }

  public void moveToNextRow() {
    row++;
  }

  public void moveToNextColumn() {
    column++;
  }

  public void previousCharacter() {
    lcsIndex--;
  }
}
