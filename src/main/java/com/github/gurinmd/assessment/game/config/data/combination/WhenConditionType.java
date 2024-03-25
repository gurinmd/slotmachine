package com.github.gurinmd.assessment.game.config.data.combination;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum WhenConditionType {
  SAME_SYMBOLS("same_symbols"), COVERED_AREA("linear_symbols")
  ;
  private String name;

  WhenConditionType(String name) {
    this.name = name;
  }

  @JsonCreator
  public static WhenConditionType forValue(String value) {
    return Arrays.stream(WhenConditionType.values()).filter(w -> Objects.equals(value, w.name))
               .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("Unknown when type " + value));
  }
}
