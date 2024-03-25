package com.github.gurinmd.assessment;

import java.io.IOException;

import com.github.gurinmd.assessment.game.Emulator;
import com.github.gurinmd.assessment.game.board.impl.BoardGeneratorImpl;
import com.github.gurinmd.assessment.game.config.impl.ConfigurationReaderImpl;
import com.github.gurinmd.assessment.game.outcome.impl.OutcomeBuilderImpl;
import com.github.gurinmd.assessment.game.winning.impl.WinningCombinationDetectorImpl;
import com.github.gurinmd.assessment.input.Input;
import com.github.gurinmd.assessment.jakson.JacksonObjectMapperProvider;
import com.github.gurinmd.assessment.output.Outcome;

/**
 * Main class. Does 3 steps: read input, emulate a game, print result
 */
public class SlotMachineApplication {
  public static void main(String[] args) throws Exception {
    Input input = readInput(args);
    Outcome outcome = process(input);
    printOutcome(outcome);
  }

  /**
   * Reads input params
   *
   * @param args
   *     param args
   * @return {@link Input} object to be used in emulation process
   */
  private static Input readInput(String[] args) {
    String file = null;
    Integer amount = null;
    for (int i = 0; i < args.length; i++) {
      if ("--config".equalsIgnoreCase(args[i]) && i != args.length - 1) {
        file = args[i+1];
      } 
      if ("--betting-amount".equalsIgnoreCase(args[i]) && i != args.length - 1) {
        amount = Integer.parseInt(args[i+1]);
      }
    }
    if (amount == null || file == null) {
      throw new IllegalArgumentException("--config or --betting-amount are not set!");
    }
    Input input = new Input();
    input.setFilePath(file);
    input.setBettingAmount(amount);
    
    return input;
  }

  private static Outcome process(Input input) throws Exception {
    Emulator emulator = new Emulator(new BoardGeneratorImpl(),
        new WinningCombinationDetectorImpl(),
        new OutcomeBuilderImpl(),
        new ConfigurationReaderImpl());
    return emulator.emulate(input);
  }

  private static void printOutcome(Outcome outcome) throws IOException {
    System.out.println(JacksonObjectMapperProvider.getInstance().writerWithDefaultPrettyPrinter().writeValueAsString(outcome));
  }
}
