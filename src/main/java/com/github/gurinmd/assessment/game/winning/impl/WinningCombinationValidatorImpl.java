package com.github.gurinmd.assessment.game.winning.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.config.data.combination.CombinationDefinition;
import com.github.gurinmd.assessment.game.config.data.combination.WhenConditionType;
import com.github.gurinmd.assessment.game.winning.WhenConditionValidator;
import com.github.gurinmd.assessment.game.winning.WinningCombinationValidator;

public class WinningCombinationValidatorImpl implements WinningCombinationValidator {
  
  private Map<WhenConditionType, WhenConditionValidator> whenConditionValidators;

  public WinningCombinationValidatorImpl() {
    this.whenConditionValidators = new HashMap<>();
    whenConditionValidators.put(WhenConditionType.SAME_SYMBOLS, new SameSymbolsWhenConditionValidator());
    whenConditionValidators.put(WhenConditionType.COVERED_AREA, new CoveredAreaWhenConditionValidator());
  }

  @Override
  public boolean validate(Board board, String symbol, CombinationDefinition combinationDefinition) {
    WhenConditionType whenConditionType = combinationDefinition.getWhenCondition();
    WhenConditionValidator validator = whenConditionValidators.get(whenConditionType);
    return validator.validate(board, symbol, combinationDefinition);
  }
}
