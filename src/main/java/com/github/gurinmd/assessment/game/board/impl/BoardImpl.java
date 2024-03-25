package com.github.gurinmd.assessment.game.board.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.board.BoardSymbol;
import com.github.gurinmd.assessment.game.config.data.probability.SymbolType;

public class BoardImpl implements Board {
  private int rows;
  private int cols;
  private BoardSymbol[][] boardSymbols;
  private BoardSymbol bonusSymbol;
  private Map<String, Integer> symbolsCount = new HashMap<>();

  public BoardImpl(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.boardSymbols = new BoardSymbol[rows][cols];
  }

  @Override
  public int getRows() {
    return rows;
  }

  @Override
  public int getCols() {
    return cols;
  }

  @Override
  public BoardSymbol get(int row, int col) {
    return boardSymbols[row][col];
  }
  
  public void set(BoardSymbol boardSymbol) {
    int row = boardSymbol.getRow();
    int col = boardSymbol.getCol();
    if (row == -1 || col == -1) {
      throw new IllegalArgumentException("Row and/or col is not set!!");
    }
    if (boardSymbols[row][col] != null) {
      throw new IllegalArgumentException("Row " + row + " and col " + col + " are already set!");
    }
    boardSymbols[row][col] = boardSymbol;
    if (boardSymbol.getSymbolType() == SymbolType.BONUS) {
      bonusSymbol = boardSymbol;
    }
    symbolsCount.put(boardSymbol.getLabel(), symbolsCount.getOrDefault(boardSymbol.getLabel(), 0) +  1);
  }

  @Override
  public BoardSymbol getBonusSymbol() {
    return bonusSymbol;
  }

  @Override
  public Map<String, Integer> getSymbolsCount() {
    return symbolsCount;
  }
}
