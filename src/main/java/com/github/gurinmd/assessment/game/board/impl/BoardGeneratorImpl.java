package com.github.gurinmd.assessment.game.board.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.board.BoardGenerator;
import com.github.gurinmd.assessment.game.board.BoardSymbol;
import com.github.gurinmd.assessment.game.config.data.Configuration;
import com.github.gurinmd.assessment.game.config.data.probability.BonusSymbolsProbConfig;
import com.github.gurinmd.assessment.game.config.data.probability.ProbabilitesConfiguration;
import com.github.gurinmd.assessment.game.config.data.probability.StandardSymbolProbConfiguration;
import com.github.gurinmd.assessment.game.config.data.probability.SymbolType;
import com.github.gurinmd.assessment.random.ProbabilisticDataStorage;
import com.github.gurinmd.assessment.random.impl.TreeMapStorageImpl;

public class BoardGeneratorImpl implements BoardGenerator {

  private Random random = new Random();
  
  @Override
  public Board generate(Configuration configuration) {
    BoardImpl board = new BoardImpl(configuration.getRows(), configuration.getColumns());
    
    Integer bonusRow = random.nextInt(0, board.getRows());
    Integer bonusCol = random.nextInt(0, board.getCols());
    
    // add standard symbols
    addStandardSymbols(configuration, board, bonusRow, bonusCol);
    
    //add AT MOST ONE bonus symbol. See assumptions.md
    addBonusSymbols(configuration, board, bonusRow, bonusCol);
    
    return board;
  }
  
  private void addBonusSymbols(Configuration configuration, BoardImpl board, int row, int col) {
    Map<String, Integer> bonusProbabilities = 
        Optional.ofNullable(configuration.getProbabilitesConfiguration())
            .map(ProbabilitesConfiguration::getBonusSymbolsProbConfig)
            .map(BonusSymbolsProbConfig::getProbabilities)
            .orElse(Collections.emptyMap());
    String symbol = peekSymbol(bonusProbabilities);
    BoardSymbol boardSymbol = createBoardSymbol(symbol, row, col, SymbolType.BONUS);
    board.set(boardSymbol);
  }
  
  private void addStandardSymbols(Configuration configuration, BoardImpl board, int bonusRow, int bonusCol) {
    List<StandardSymbolProbConfiguration> configurations =
        Optional.ofNullable(configuration.getProbabilitesConfiguration())
            .map(ProbabilitesConfiguration::getStandardSymbolsConfiguration)
            .orElse(Collections.emptyList());
    for (StandardSymbolProbConfiguration config : configurations){
      Integer row = config.getRow();
      Integer column = config.getColumn();
      if (row == bonusRow && column == bonusCol) {
        continue; // skip setting bonus cell
      }
      String symbol = peekSymbol(config.getSymbols());
      BoardSymbol boardSymbol = createBoardSymbol(symbol, row, column, SymbolType.STANDARD);
      board.set(boardSymbol);
    }
  }
  
  private BoardSymbol createBoardSymbol(String label, int row, int col, SymbolType symbolType) {
    BoardSymbol boardSymbol = new BoardSymbol(label, symbolType);
    boardSymbol.setRow(row);
    boardSymbol.setCol(col);
    return boardSymbol;
  }
  
  private String peekSymbol(Map<String, Integer> symbolsProbability) {
    ProbabilisticDataStorage<String> storage = new TreeMapStorageImpl<>();
    for (Map.Entry<String, Integer> entry : symbolsProbability.entrySet()) {
      storage.add(entry.getValue(), entry.getKey());
    }
    return storage.get();
  }
}
