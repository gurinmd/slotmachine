package com.github.gurinmd.assessment.game.config.data.combination;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CombinationDefinition {
  
  private String name;
  
  @JsonProperty("reward_multiplier")
  private Double rewardMultiplier;
  
  @JsonProperty("when")
  private WhenConditionType whenConditionType;
  
  @JsonProperty("count")
  private Integer count;

  @JsonProperty("group")
  private String group;
  
  @JsonProperty("covered_areas")
  private List<List<String>> coveredArea;

  public Double getRewardMultiplier() {
    return rewardMultiplier;
  }

  public WhenConditionType getWhenCondition() {
    return whenConditionType;
  }

  public Integer getCount() {
    return count;
  }

  public String getGroup() {
    return group;
  }

  public List<List<String>> getCoveredArea() {
    return coveredArea;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
