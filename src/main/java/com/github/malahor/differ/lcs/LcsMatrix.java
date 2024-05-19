package com.github.malahor.differ.lcs;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LcsMatrix {

  private int[][] matrix;
  private int rowsSize;
  private int columnsSize;
  private char[] rowsHeaders;
  private char[] columnsHeaders;

  public LcsMatrix(String rowText, String columnText) {
    rowsHeaders = rowText.toCharArray();
    columnsHeaders = columnText.toCharArray();
    rowsSize = rowText.length();
    columnsSize = columnText.length();
    matrix = new int[rowsSize + 1][columnsSize + 1];
    fillCells();
  }

  public String lcs() {
    var lcsLength = lastElement();
    char[] lcs = new char[lcsLength];
    var visitor = new LcsMatrixVisitor(lcsLength - 1, rowsSize, columnsSize);
    while (visitor.matrixHasPrevious()) {
      if (charactersMatch(visitor)) {
        lcs[visitor.lcsIndex] = rowsHeaders[visitor.row - 1];
        visitor.moveBack();
      } else {
        visitor.moveBackToHigherValue(matrix);
      }
    }
    return toString(lcs);
  }

  private void fillCells() {
    var visitor = new LcsMatrixVisitor(0, 1, 1);
    for (visitor.row = 1; visitor.row <= rowsSize; visitor.moveToNextRow())
      for (visitor.column = 1; visitor.column <= columnsSize; visitor.moveToNextColumn()) setCell(visitor);
  }

  private void setCell(LcsMatrixVisitor visitor) {
    if (charactersMatch(visitor)) visitor.increaseCurrent(matrix);
    else visitor.copyPreviousValueToCurrent(matrix);
  }

  private String toString(char[] lcs) {
    var result = new StringBuilder();
    for (var c : lcs) result.append(c);
    return result.toString();
  }

  private int lastElement() {
    return matrix[rowsSize][columnsSize];
  }

  private boolean charactersMatch(LcsMatrixVisitor visitor) {
    return rowsHeaders[visitor.row - 1] == columnsHeaders[visitor.column - 1];
  }
}
