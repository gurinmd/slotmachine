package com.github.gurinmd.assessment.game;

import java.io.IOException;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.board.BoardGenerator;
import com.github.gurinmd.assessment.game.config.ConfigurationReader;
import com.github.gurinmd.assessment.game.config.data.Configuration;
import com.github.gurinmd.assessment.game.outcome.OutcomeBuilder;
import com.github.gurinmd.assessment.game.winning.WinningCombinationDetector;
import com.github.gurinmd.assessment.game.winning.WinningSummary;
import com.github.gurinmd.assessment.input.Input;
import com.github.gurinmd.assessment.output.Outcome;

/**
 * Main logic class. Receives a configuration as input, emulates the game and returns result 
 * @param gameConfiguration game configuration read from an input file
 * @return {@link Outcome} representing a game outcome
 */
public class Emulator {
  
  private BoardGenerator boardGenerator;
  private WinningCombinationDetector winningCombinationDetector;
  private OutcomeBuilder outcomeBuilder;
  private ConfigurationReader configurationReader;
  
  public Emulator(BoardGenerator boardGenerator, WinningCombinationDetector winningCombinationDetector,
                  OutcomeBuilder outcomeBuilder, ConfigurationReader configurationReader) {
    this.configurationReader = configurationReader;
    this.boardGenerator = boardGenerator;
    this.winningCombinationDetector = winningCombinationDetector;
    this.outcomeBuilder = outcomeBuilder;
  }

  /**
   * Method, simulating a single round. Builds a board, detects for winning combinations and 
   * summarizing winning outcome 
   * @param gameConfiguration 
   * @return outcome to print
   */
  public Outcome emulate(Input input) throws IOException {
    Configuration configuration = configurationReader.readGameConfiguration(input.getFilePath());
    Board board = boardGenerator.generate(configuration);
    WinningSummary winningSummary = winningCombinationDetector.detectWinningCombinations(board, configuration, input.getBettingAmount());
    return outcomeBuilder.buildGameOutcome(winningSummary, board);
  }
}
