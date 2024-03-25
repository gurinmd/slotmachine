package com.github.gurinmd.assessment.random;

/**
 * As mentioned: "To calculate to probability percentage just sum all symbols probability numbers and divide individual 
 * symbol's probability number to total probability numbers"
 * 
 *
 * A data storage, to help peek a random element assuming probabilities given. 
 * For example, we can register elements, like 
 * <pre>
 * ProbabilisticDataStorage storage = ...;
 * 
 * storage.add("One", 1);
 * storage.add("Two", 4);
 * storage.add("Three", 5);
 * </pre>
 * and retrieve elements using
 * <pre>
 *   storage.get(); // "One" will be returned with 10% probability, "Two" - 40% probability, "Three" - 50%
 * </pre>
 * @param <T> object type to store
 */
public interface ProbabilisticDataStorage<T>  {
  void add(Integer probability, T object);
  T get();
}
