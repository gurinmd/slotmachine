package com.github.gurinmd.assessment.game.winning;

import java.util.List;
import java.util.Map;

public class WinningSummary {
  private Boolean win;
  private Integer winAmount;
  
  private Map<String, List<String>> appliedCombinations;
  
  private String appliedBonus;

  public Boolean getWin() {
    return win;
  }

  public void setWin(Boolean win) {
    this.win = win;
  }

  public Integer getWinAmount() {
    return winAmount;
  }

  public void setWinAmount(Integer winAmount) {
    this.winAmount = winAmount;
  }

  public Map<String, List<String>> getAppliedCombinations() {
    return appliedCombinations;
  }

  public void setAppliedCombinations(Map<String, List<String>> appliedCombinations) {
    this.appliedCombinations = appliedCombinations;
  }

  public String getAppliedBonus() {
    return appliedBonus;
  }

  public void setAppliedBonus(String appliedBonus) {
    this.appliedBonus = appliedBonus;
  }
}
