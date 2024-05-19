package com.github.malahor.differ.lcs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LcsMatrix {

  private int[][] matrix;
  private int rowsSize;
  private int columnsSize;
  private String[] rowsHeaders;
  private String[] columnsHeaders;

  public LcsMatrix(List<String> rowTexts, List<String> columnTexts) {
    rowsHeaders = rowTexts.toArray(String[]::new);
    columnsHeaders = columnTexts.toArray(String[]::new);
    rowsSize = rowTexts.size();
    columnsSize = columnTexts.size();
    matrix = new int[rowsSize + 1][columnsSize + 1];
    fillCells();
  }

  public List<String> diff() {
    List<String> differences = new ArrayList<>();
    var visitor = new LcsMatrixVisitor(rowsSize, columnsSize);
    while (visitor.matrixHasPrevious()) {
      if (textsMatch(visitor)) {
        visitor.moveBack();
      } else {
        if (visitor.isRowValueHigherThanColumn(matrix)) {
          differences.add(deletedLine(visitor));
          visitor.moveToPreviousRow();
        } else {
          differences.add(addedLine(visitor));
          visitor.moveToPreviousColumn();
        }
      }
    }
    moveBackWhereStillPossible(visitor, differences);
    Collections.reverse(differences);
    return differences;
  }

  private void moveBackWhereStillPossible(LcsMatrixVisitor visitor, List<String> differences) {
    if (visitor.row > 0)
      while (visitor.row > 0) {
        differences.add(deletedLine(visitor));
        visitor.moveToPreviousRow();
      }
    if (visitor.column > 0)
      while (visitor.column > 0) {
        differences.add(addedLine(visitor));
        visitor.moveToPreviousColumn();
      }
  }

  private String addedLine(LcsMatrixVisitor visitor) {
    return (visitor.column) + "a - " + columnsHeaders[visitor.column - 1];
  }

  private String deletedLine(LcsMatrixVisitor visitor) {
    return (visitor.row) + "d - " + rowsHeaders[visitor.row - 1];
  }

  private void fillCells() {
    var visitor = new LcsMatrixVisitor(1, 1);
    for (visitor.row = 1; visitor.row <= rowsSize; visitor.moveToNextRow())
      for (visitor.column = 1; visitor.column <= columnsSize; visitor.moveToNextColumn())
        setCell(visitor);
  }

  private void setCell(LcsMatrixVisitor visitor) {
    if (textsMatch(visitor)) visitor.increaseCurrent(matrix);
    else visitor.copyPreviousValueToCurrent(matrix);
  }

  private boolean textsMatch(LcsMatrixVisitor visitor) {
    return rowsHeaders[visitor.row - 1].equals(columnsHeaders[visitor.column - 1]);
  }
}
