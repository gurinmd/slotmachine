package com.github.gurinmd.assessment.game.config.data.probability;

import java.util.Map;

public class StandardSymbolProbConfiguration {
  private Integer column;
  private Integer row;
  private Map<String, Integer> symbols;

  public Integer getColumn() {
    return column;
  }

  public Integer getRow() {
    return row;
  }

  public Map<String, Integer> getSymbols() {
    return symbols;
  }
}
