package com.github.gurinmd.assessment.game.outcome.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.outcome.OutcomeBuilder;
import com.github.gurinmd.assessment.game.winning.WinningSummary;
import com.github.gurinmd.assessment.output.Outcome;

public class OutcomeBuilderImpl implements OutcomeBuilder {
  @Override
  public Outcome buildGameOutcome(WinningSummary winningSummary, Board board) {
    Outcome outcome = new Outcome();
    outcome.setMatrix(buildMatrix(board));
    outcome.setReward(Boolean.TRUE.equals(winningSummary.getWin()) ? winningSummary.getWinAmount() : 0);
    if (winningSummary.getAppliedBonus() != null && !winningSummary.getAppliedBonus().isEmpty()) {
      outcome.setAppliedSymbolBonus(winningSummary.getAppliedBonus());
    }
    outcome.setAppliedWinCombinations(winningSummary.getAppliedCombinations());
    return outcome;
  }
  
  private List<List<String>> buildMatrix(Board board) {
    List<List<String>> res = new ArrayList<>();
    for (int row = 0; row < board.getRows(); row++) {
      List<String> rowData = new ArrayList<>();
      for (int col = 0; col < board.getCols(); col++) {
        rowData.add(board.get(row, col).getLabel());
      }
      res.add(rowData);
    }
    return res;
  }
}
