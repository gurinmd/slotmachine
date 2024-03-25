package com.github.gurinmd.assessment.game.config.data.probability;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SymbolConfiguration {
  @JsonProperty("reward_multiplier")
  private Double rewardMultiplier;
  @JsonProperty("extra")
  private Integer extra;
  @JsonProperty("type")
  private SymbolType symbolType;
  @JsonProperty("impact")
  private Impact impact;

  public Double getRewardMultiplier() {
    return rewardMultiplier;
  }

  public Integer getExtra() {
    return extra;
  }

  public SymbolType getSymbolType() {
    return symbolType;
  }

  public Impact getImpact() {
    return impact;
  }
}
