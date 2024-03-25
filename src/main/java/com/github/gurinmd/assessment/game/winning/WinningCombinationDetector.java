package com.github.gurinmd.assessment.game.winning;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.config.data.Configuration;

/**
 * Detects if any winning combinations exist on the board. If yes - they and reward are writter to 
 * {@link WinningSummary} object
 */
public interface WinningCombinationDetector {
  WinningSummary detectWinningCombinations(Board board, Configuration configuration, Integer bet);
}
