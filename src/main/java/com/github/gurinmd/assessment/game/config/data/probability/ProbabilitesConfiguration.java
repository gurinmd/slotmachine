package com.github.gurinmd.assessment.game.config.data.probability;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProbabilitesConfiguration {
  
  @JsonProperty("standard_symbols")
  private List<StandardSymbolProbConfiguration> standardSymbolsConfiguration;
  
  @JsonProperty("bonus_symbols")
  private BonusSymbolsProbConfig bonusSymbolsProbConfig;

  public List<StandardSymbolProbConfiguration> getStandardSymbolsConfiguration() {
    return standardSymbolsConfiguration;
  }

  public BonusSymbolsProbConfig getBonusSymbolsProbConfig() {
    return bonusSymbolsProbConfig;
  }
}
