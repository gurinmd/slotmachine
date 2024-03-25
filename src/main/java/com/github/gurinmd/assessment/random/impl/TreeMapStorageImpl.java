package com.github.gurinmd.assessment.random.impl;

import java.util.Random;
import java.util.TreeMap;

import com.github.gurinmd.assessment.random.ProbabilisticDataStorage;

public class TreeMapStorageImpl<T> implements ProbabilisticDataStorage<T> {
  
  private Integer currentOffset = 0;
  private TreeMap<Integer, T> storage = new TreeMap<>();
  private Random random = new Random();
  
  @Override
  public void add(Integer probability, T object) {
    currentOffset = currentOffset + probability;
    storage.put(currentOffset, object);
  }

  @Override
  public T get() {
    Integer index = random.nextInt(0, currentOffset + 1); // +1 because upperBound is exclusive
    return storage.ceilingEntry(index).getValue();
  }
}
