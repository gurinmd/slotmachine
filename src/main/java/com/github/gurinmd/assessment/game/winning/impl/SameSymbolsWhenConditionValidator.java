package com.github.gurinmd.assessment.game.winning.impl;

import java.util.Map;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.config.data.combination.CombinationDefinition;
import com.github.gurinmd.assessment.game.winning.WhenConditionValidator;

/**
 * Validates if one symbol repeated in the matrix probabilities.win_combinations.{X}.count times
 */
public class SameSymbolsWhenConditionValidator implements WhenConditionValidator {
  @Override
  public boolean validate(Board board, String symbol, CombinationDefinition combinationDefinition) {
    Integer countRequired = combinationDefinition.getCount();
    Map<String, Integer> counts = board.getSymbolsCount();
    return counts.containsKey(symbol) && counts.get(symbol) >= countRequired;
  }
}
