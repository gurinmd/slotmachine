package com.github.gurinmd.assessment.game.winning.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.config.data.combination.CombinationDefinition;
import com.github.gurinmd.assessment.game.winning.WhenConditionValidator;

/**
 * Validates if it matches to probabilities.win_combinations.{X}.covered_areas
 */
public class CoveredAreaWhenConditionValidator implements WhenConditionValidator {
  @Override
  public boolean validate(Board board, String symbol, CombinationDefinition combinationDefinition) {
    List<List<String>> areas =
        Optional.ofNullable(combinationDefinition.getCoveredArea()).orElse(Collections.emptyList());
    // check each area
    for (List<String> area : areas) {
      if (area != null && !area.isEmpty() && (validateArea(area, board, symbol))) {
          return true;
      }
    }
    return false;
  }
  
  private boolean validateArea(List<String> area, Board board, String symbol) {
    List<int[]> coordinates = area.stream().map(s -> s.split(":"))
                                  .map(strs -> new int[]{Integer.parseInt(strs[0]), Integer.parseInt(strs[1])})
                                  .toList();
    for (int[] coord : coordinates) {
      int row = coord[0];
      int col = coord[1];
      if (!Objects.equals(board.get(row, col).getLabel(), symbol)) {
        return false;
      }
    }
    return true;
  }
}
