package com.github.gurinmd.assessment.output;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.gurinmd.assessment.jakson.MatrixSerializer;

/**
 * An object representation of game output. In other word - what should be printed as a result
 */
@JsonPropertyOrder({"matrix", "reward", "applied_winning_combinations", "applied_bonus_symbol"})
public class Outcome {
  @JsonProperty("matrix")
  @JsonSerialize(using = MatrixSerializer.class)
  private List<List<String>> matrix;
  @JsonProperty("reward")
  private Integer reward;
  @JsonProperty("applied_winning_combinations")
  private Map<String, List<String>> appliedWinCombinations;
  @JsonProperty("applied_bonus_symbol")
  private String appliedSymbolBonus;

  public List<List<String>> getMatrix() {
    return matrix;
  }

  public void setMatrix(List<List<String>> matrix) {
    this.matrix = matrix;
  }

  public Integer getReward() {
    return reward;
  }

  public void setReward(Integer reward) {
    this.reward = reward;
  }

  public Map<String, List<String>> getAppliedWinCombinations() {
    return appliedWinCombinations;
  }

  public void setAppliedWinCombinations(Map<String, List<String>> appliedWinCombinations) {
    this.appliedWinCombinations = appliedWinCombinations;
  }

  public String getAppliedSymbolBonus() {
    return appliedSymbolBonus;
  }

  public void setAppliedSymbolBonus(String appliedSymbolBonus) {
    this.appliedSymbolBonus = appliedSymbolBonus;
  }
}
