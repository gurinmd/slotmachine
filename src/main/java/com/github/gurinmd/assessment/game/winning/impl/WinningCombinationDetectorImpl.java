package com.github.gurinmd.assessment.game.winning.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.gurinmd.assessment.game.board.Board;
import com.github.gurinmd.assessment.game.board.BoardSymbol;
import com.github.gurinmd.assessment.game.config.data.Configuration;
import com.github.gurinmd.assessment.game.config.data.combination.CombinationDefinition;
import com.github.gurinmd.assessment.game.config.data.probability.SymbolConfiguration;
import com.github.gurinmd.assessment.game.config.data.probability.SymbolType;
import com.github.gurinmd.assessment.game.winning.WinningCombinationDetector;
import com.github.gurinmd.assessment.game.winning.WinningCombinationValidator;
import com.github.gurinmd.assessment.game.winning.WinningSummary;

public class WinningCombinationDetectorImpl implements WinningCombinationDetector {

  private final WinningCombinationValidator winningCombinationValidator = new WinningCombinationValidatorImpl();

  @Override
  public WinningSummary detectWinningCombinations(Board board, Configuration configuration, Integer bet) {
    WinningSummary winningSummary = new WinningSummary();
    Map<String, List<String>> applicableCombinations = calculateCombinationsForEachSymbol(board, configuration);

    if (applicableCombinations.isEmpty()) {
      winningSummary.setWin(false);
    } else {
      winningSummary.setWin(true);
      Integer reward = calculateSymbolsReward(bet, applicableCombinations, configuration);
      winningSummary.setWinAmount(reward);
      applyBonus(board, winningSummary, configuration);
      winningSummary.setAppliedCombinations(applicableCombinations);
    }
    
    return winningSummary;
  }

  private Integer calculateSymbolsReward(Integer bet, Map<String, List<String>> symbolCombinations,
                                         Configuration configuration) {
    int total = 0;
    for (String symbol : symbolCombinations.keySet()) {
      Double symbolMultiplier = configuration.getSymbolsConfiguration().get(symbol).getRewardMultiplier();
      for (String combination : symbolCombinations.get(symbol)) {
        symbolMultiplier *= configuration.getWinCombinations().get(combination).getRewardMultiplier();
      }
      total += (int) (bet * symbolMultiplier);
    }
    return total;
  }

  private void applyBonus(Board board, WinningSummary winningSummary, Configuration configuration) {
    BoardSymbol bonusSymbols = board.getBonusSymbol();
    Integer currentReward = winningSummary.getWinAmount();
    if (bonusSymbols != null) {
      SymbolConfiguration bonusSymbolConfig = configuration.getSymbolsConfiguration().get(bonusSymbols.getLabel());
      winningSummary.setAppliedBonus(bonusSymbols.getLabel());
      Integer updatedReward;
      switch (bonusSymbolConfig.getImpact()) {
        case MULTIPLY -> updatedReward = (int) (currentReward * bonusSymbolConfig.getRewardMultiplier());
        case EXTRA -> updatedReward = currentReward + bonusSymbolConfig.getExtra();
        default -> updatedReward = currentReward;
      }
      winningSummary.setWinAmount(updatedReward);
    }
  }

  // key - symbol. value - list of combinations matching for each symbol
  private Map<String, List<String>> calculateCombinationsForEachSymbol(Board board, Configuration configuration) {
    Map<String, List<String>> res = new HashMap<>();
    Map<String, List<CombinationDefinition>> splitByGoups = splitByGroupAndSortByMultiplierDesc(configuration);

    // iterate by symbol.
    // For each symbol - iterate for each group
    // in each group - try each condition

    // 1. Take all the symbols
    Set<String> standardSymbols = getStandardSymbols(board, configuration);

    // 2. Iterate by each symbol
    for (String symbolLabel : standardSymbols) {

      // 3. For each symbol - iterate for each group
      for (Map.Entry<String, List<CombinationDefinition>> entry : splitByGoups.entrySet()) {
        // 4. In each group - try to apply each condition
        for (CombinationDefinition combinationDefinition : entry.getValue()) {
          if (winningCombinationValidator.validate(board, symbolLabel, combinationDefinition)) {
            res.computeIfAbsent(symbolLabel, s -> new ArrayList<>()).add(combinationDefinition.getName());
            break;
          }
        }
      }

    }
    return res;
  }

  // define set of symbols to check. All standard symbols
  private Set<String> getStandardSymbols(Board board, Configuration configuration) {
    Map<String, Integer> symbolCount = board.getSymbolsCount();
    return symbolCount.keySet().stream()
            .filter(s -> configuration.getSymbolsConfiguration().get(s) != null
                             && configuration.getSymbolsConfiguration().get(s).getSymbolType() == SymbolType.STANDARD)
            .collect(Collectors.toSet());
  }

  private Map<String, List<CombinationDefinition>> splitByGroupAndSortByMultiplierDesc(Configuration configuration) {
    Map<String, CombinationDefinition> definitions =
        Optional.ofNullable(configuration.getWinCombinations()).orElse(Collections.emptyMap());

    // create a list of all definitions
    List<CombinationDefinition> defList = definitions.entrySet().stream().map(e -> {
      e.getValue().setName(e.getKey());
      return e.getValue();
    }).toList();

    // split by group
    Map<String, List<CombinationDefinition>> byGroups = defList.stream().collect(Collectors.toMap(
        CombinationDefinition::getGroup,
        combinationDefinition -> {
          List<CombinationDefinition> l = new ArrayList<>();
          l.add(combinationDefinition);
          return l;
        },
        (l1, l2) -> {
          l1.addAll(l2);
          return l1;
        }
    ));

    //sort in each group
    byGroups.forEach((s, combinationDefinitions) -> 
      Collections.sort(combinationDefinitions,
          Comparator.comparing(CombinationDefinition::getRewardMultiplier).reversed()));

    return byGroups;
  }
}
