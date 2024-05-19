package com.github.malahor.differ.lcs;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LcsMatrixVisitor {

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
  }

  public boolean isRowValueHigherThanColumn(int[][] matrix) {
    return matrix[row - 1][column] > matrix[row][column - 1];
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
}
