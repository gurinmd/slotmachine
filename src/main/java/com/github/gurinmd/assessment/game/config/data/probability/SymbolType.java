package com.github.gurinmd.assessment.game.config.data.probability;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SymbolType {
  STANDARD("standard"), BONUS("bonus");

  private String name;

  SymbolType(String name) {
    this.name = name;
  }

  @JsonCreator
  public static SymbolType forValue(String value) {
    return Arrays.stream(SymbolType.values()).filter(symbolType -> Objects.equals(value, symbolType.name))
               .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("Unknown symbol type " + value));
  }
}
