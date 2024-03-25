package com.github.gurinmd.assessment.game.winning;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.config.data.combination.CombinationDefinition;

/**
 * Used to check in a particular combination occured for a given symbol
 */
public interface WinningCombinationValidator {
  boolean validate(Board board, String symbol, CombinationDefinition combinationDefinition);
}
