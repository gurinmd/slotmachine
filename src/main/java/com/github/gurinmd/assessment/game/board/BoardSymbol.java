package com.github.gurinmd.assessment.game.board;

import com.github.gurinmd.assessment.game.config.data.probability.SymbolType;

/**
 * Class representing symbol on the board.
 * Contains label, position information and symbol type
 */
public class BoardSymbol {
  private String label;
  private Integer row = -1;
  private Integer col = -1;
  private SymbolType symbolType;

  public BoardSymbol(String label, Integer row, Integer col, SymbolType symbolType) {
    this.label = label;
    this.row = row;
    this.col = col;
    this.symbolType = symbolType;
  }

  public BoardSymbol(String label, Integer row, Integer col) {
    this(label, row, col, SymbolType.STANDARD);
  }

  public BoardSymbol(String label, SymbolType symbolType) {
    this(label, -1, -1, symbolType);
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public SymbolType getSymbolType() {
    return symbolType;
  }

  public void setSymbolType(SymbolType symbolType) {
    this.symbolType = symbolType;
  }

  public void setRow(Integer row) {
    this.row = row;
  }

  public void setCol(Integer col) {
    this.col = col;
  }

  public Integer getRow() {
    return row;
  }

  public Integer getCol() {
    return col;
  }
}
