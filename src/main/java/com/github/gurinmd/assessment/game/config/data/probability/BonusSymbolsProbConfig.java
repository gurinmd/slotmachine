package com.github.gurinmd.assessment.game.config.data.probability;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BonusSymbolsProbConfig {
  
  public static final String MISS_SYMBOL_NAME = "MISS";
  @JsonProperty("symbols")
  private Map<String, Integer> probabilities;

  public Map<String, Integer> getProbabilities() {
    return probabilities;
  }
}
