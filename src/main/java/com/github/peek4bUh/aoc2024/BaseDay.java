package com.github.peek4bUh.aoc2024;

/**
 *
 * @author peek4bUh
 */
public abstract class BaseDay {

  private final BasePuzzle puzzle1;
  private final BasePuzzle puzzle2;

  protected BaseDay(BasePuzzle puzzle1, BasePuzzle puzzle2) {
    this.puzzle1 = puzzle1;
    this.puzzle2 = puzzle2;
  }

  public BasePuzzle getPuzzle1() {
    return puzzle1;
  }

  public BasePuzzle getPuzzle2() {
    return puzzle2;
  }
}
