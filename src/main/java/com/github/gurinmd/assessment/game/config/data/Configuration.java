package com.github.gurinmd.assessment.game.config.data;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.gurinmd.assessment.game.config.data.combination.CombinationDefinition;
import com.github.gurinmd.assessment.game.config.data.probability.ProbabilitesConfiguration;
import com.github.gurinmd.assessment.game.config.data.probability.SymbolConfiguration;

/**
 * Object representation of JSON file, passed to input.
 * Used to build a board, validate winning combinations and calculate a reward
 */
public class Configuration {

  @JsonProperty("columns")
  private Integer columns;

  @JsonProperty("rows")
  private Integer rows;
  
  @JsonProperty("symbols")
  private Map<String, SymbolConfiguration> symbolsConfiguration;
  
  @JsonProperty("probabilities")
  private ProbabilitesConfiguration probabilitesConfiguration;
  
  @JsonProperty("win_combinations")
  private Map<String, CombinationDefinition> winCombinations;

  public Integer getColumns() {
    return columns;
  }

  public Integer getRows() {
    return rows;
  }

  public Map<String, SymbolConfiguration> getSymbolsConfiguration() {
    return symbolsConfiguration;
  }

  public ProbabilitesConfiguration getProbabilitesConfiguration() {
    return probabilitesConfiguration;
  }

  public Map<String, CombinationDefinition> getWinCombinations() {
    return winCombinations;
  }
}
