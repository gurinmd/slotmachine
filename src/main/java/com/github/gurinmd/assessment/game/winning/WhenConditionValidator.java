package com.github.gurinmd.assessment.game.winning;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.config.data.combination.CombinationDefinition;

/**
 * Validate different 'when' types. Now - 'linear_symbols' and 'same_symbols'
 */
public interface WhenConditionValidator {
  boolean validate(Board board, String symbol, CombinationDefinition combinationDefinition);
}
