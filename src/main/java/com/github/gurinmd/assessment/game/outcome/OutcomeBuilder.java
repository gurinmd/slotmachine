package com.github.gurinmd.assessment.game.outcome;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.winning.WinningSummary;
import com.github.gurinmd.assessment.output.Outcome;

/**
 * Builds {@link Outcome} which is a game output
 */
public interface OutcomeBuilder {
  Outcome buildGameOutcome(WinningSummary winningSummary, Board board);

}
