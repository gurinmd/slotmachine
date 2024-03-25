package com.github.gurinmd.assessment.game.config.data.probability;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Impact {
  MULTIPLY("multiply_reward"),
  EXTRA("extra_bonus"),
  MISS("miss");


  private String name;

  Impact(String name) {
    this.name = name;
  }

  @JsonCreator
  public static Impact forValue(String value) {
    return Arrays.stream(Impact.values()).filter(i -> Objects.equals(value, i.name))
               .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("Unknown impact type " + value));
  }
}
