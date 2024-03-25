package com.github.gurinmd.assessment.board;

import java.util.Map;

import com.github.gurinmd.assessment.game.board.BoardSymbol;
import com.github.gurinmd.assessment.game.board.impl.BoardImpl;
import com.github.gurinmd.assessment.game.config.data.probability.SymbolType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardImplTest {

  private BoardImpl board;

  @BeforeEach
  public void setUp() {
    board = new BoardImpl(3, 3);
  }

  @Test
  void testBoardCreation() {
    assertNotNull(board);
    assertEquals(3, board.getRows());
    assertEquals(3, board.getCols());
  }

  @Test
  void testGetAndSet() {
    assertNull(board.get(0, 0));

    BoardSymbol symbol = new BoardSymbol("X", 0, 0);
    board.set(symbol);

    assertEquals(symbol, board.get(0, 0));
    assertEquals( 0, symbol.getRow());
    assertEquals(0, symbol.getCol());
  }

  @Test
  void testSetAlreadyOccupied() {
    BoardSymbol symbol = new BoardSymbol("X", 1, 1, SymbolType.STANDARD);
    symbol.setLabel("X");
    symbol.setSymbolType(SymbolType.STANDARD);
    board.set(symbol);

    assertThrows(IllegalArgumentException.class, () -> board.set(symbol));
  }

  @Test
  void testGetBonusSymbol() {
    assertNull(board.getBonusSymbol());
    BoardSymbol bonusSymbol = new BoardSymbol("x10", 0,0, SymbolType.BONUS);
    board.set(bonusSymbol);

    assertEquals(bonusSymbol, board.getBonusSymbol());
  }

  @Test
  void testGetSymbolsCount() {
    board.set(new BoardSymbol("A", 0, 0, SymbolType.STANDARD));
    board.set(new BoardSymbol("B", 0, 1, SymbolType.STANDARD));
    board.set(new BoardSymbol("A", 1, 0, SymbolType.STANDARD));
    board.set(new BoardSymbol("B", 1, 1, SymbolType.STANDARD));
    board.set(new BoardSymbol("C", 2, 2, SymbolType.STANDARD));

    Map<String, Integer> symbolsCount = board.getSymbolsCount();

    assertEquals(2, symbolsCount.get("A"));
    assertEquals(2, symbolsCount.get("B"));
    assertEquals(1, symbolsCount.get("C"));
    
  }
}

