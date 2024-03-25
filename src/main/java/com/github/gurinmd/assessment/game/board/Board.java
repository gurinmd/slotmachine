package com.github.gurinmd.assessment.game.board;

import java.util.Map;

/**
 * Represents a board
 */
public interface Board {
  int getRows();
  int getCols();

  /**
   * Return a symbol from row and column given 
   * @param row row
   * @param col column
   * @return symbol
   */
  BoardSymbol get(int row, int col);

  /**
   * Return bonus symbols
   * @return return bonus symbol. 
   */
  BoardSymbol getBonusSymbol();

  /**
   * Return count of each symbol.
   * @return Map where key - symbol label ("A", "B",...) 
   * and value - how many symbols are there on the board
   */
  Map<String, Integer> getSymbolsCount();
}
