# Assumptions and implementation notes

## Requirements
* Java 17
* Maven

## How to build
1. Run 
```bash 
mvn clean package
```
2. `slotmachine.jar` is created in `target` directory

## How to run
Run 

```bash
java -jar target/slotmachine.jar --betting-amout <amount> --config <configfile>
```

For example

```bash
java -jar target/slotmachine.jar --betting-amout 100 --config testcase.json
```

## Implementation notes
### Used libraries
* Jackson - to ease the parsing process of JSON file
* JUnit 5, Mockito - for unit tests

### Design notes
Application is created as a command line application. 
As soon as parameters are passed, JSON file is deserialized to a configuration object, then application builds a board (or field) with symbols, and then start validating all the winning combinations agains data on board.
In the end - calculate reward and print result to output. Total time spent on the task ~6 hours. 
Just one unit test is implemented, just to demonstrate, that I know how to write them and what should be tested :)

### Missed Unit Tests
JUnit 5 and Mockito should be used to generate unit tests. At first, I didn't start working in TDD way, and then it was too late to implement tests.
Later I understood, I was not able to reach my desired coverage (>80%), because, I can't afford spending too much time on this task.

### Jackson Serializing Issue
During the source code inspection, you might face `com.github.gurinmd.assessment.jakson.MatrixSerializer` class. This was the only way to make Jackson print matrix in the following formatting style
```json
{
    "matrix": [
        ["A", "A", "B"],
        ["A", "+1000", "B"],
        ["A", "A", "B"]
    ],
    ...
}
```

## Assumptions

### Problem: Wrong or inconsistent configuration
How should I handle a situation, when configuration is wrong? For example, `rows * columns !=  probabilities.standard_symbols.size()`. Or `probabilities.standard_symbols.symbols` refer to symbols, not listed in `symbols`?
#### Assumption: 
Regard configuration file as correct, without performing any validations

### Problem: Number of bonus symbols
How many bonus symbols should be generated on a field? As described, there is a probability distribution for each bonus symbol. But what is the probability, that any cell can become a bonus one? 
#### Assumption: 
Exactly 1 bonus symbol is generated for a field

### Problem: Controversial location of `win_combinations`
Where "win_combinations" is located? According to the example, it's on the same nesting level as "rows", "columns", "symbols" and "probabilites". 
But the documentation refers to "probabilities.win_combination", as if "win_combination" is nested to "probabilities" object. 
#### Assumption: 
`win_combination` is NOT nested to `probabilties` (like `probabilities.win_combinations`), it's on the same level with `probabilities`
### Problem: Weird probability configuration
Am I correct, that symbol probabilities are set for each cell (0:0, 0:1, 0:2...)? For me, it does not make sense, as in this case it will be impossible to define field size in config file. Because in every case of changing "rows" and "cols" values - new probabilties should be set to cover all the range (line 128 in `readme.md`). 
#### Assumption:
Probabilities are set for each position in configuration file 

### Problem: Winning combination apply order
It's not clear from the description, in which order winning combinations should be applied. 
#### Assumption: 
Combinations (in each group) are sorted by `reward_multiplier` in descending order, and are applied one by one, starting from biggest `reward_multiplier` to the smallest one. Until any combinations is found.

### Problem: Group exclusive combinations
As far as I understand, taking into consideration 
formula `SYMBOL_1 * WIN_COMBINATION_1_FOR_SYMBOL_1 * WIN_COMBINATION_2_FOR_SYMBOL_1`, 
and a fact that "max 1 winning combination should be applied for each win combination group" - it makes me thing that `WIN_COMBINATION_2_FOR_SYMBOL_1` and `WIN_COMBINATION_1_FOR_SYMBOL_1` SHOULD belong to different groups. 
In other words, if an y symbol is a part of two different winning combination - these combinations belongs to different groups. And here is a question. One symbol can belong to MULTIPLE combinations in SAME group. In this case - which combination to choose? 
#### Assumption: 
If a symbol is a part of multiple winning combinations within the same group - group with HIGHEST multiplier should be picked

### Problem: bonus apply orderd
How to apply 2 different types of bonuses: "multiply_reward" and "extra_bonus"? What should I apply first? 
#### Assumption: 
There is only 1 bonus symbol, so the problem is not applicable

## Possible Improvements
* Add more rules to generate Board
* Introduce Board with flexible number of rows and columns without necessity of setting probability for each cell
* Unit test coverage >80%
* ... open to discuss