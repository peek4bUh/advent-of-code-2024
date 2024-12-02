package com.github.peek4bUh.day01;

import com.github.peek4bUh.aoc2024.BasePuzzle;
import com.github.peek4bUh.utils.ReadFile;

/**
 * --- Part Two ---
 *
 * Now, given the same instructions, find the position of the first character
 * that causes him to enter the basement (floor -1). The first character in the
 * instructions has position 1, the second character has position 2, and so on.
 *
 * For example:
 *
 * ) causes him to enter the basement at character position 1. ()()) causes him
 * to enter the basement at character position 5.
 *
 * What is the position of the character that causes Santa to first enter the
 * basement?
 *
 * @author peek4bUh
 */
public class Puzzle2 implements BasePuzzle {

    @Override
    public void play() {
        String input = ReadFile.getResourceFileAsString("input-day01.txt");
        char[] inputList = input.toCharArray();
        int answerIndex = 0;
        int n = 0;

        for (int i = 0; i < inputList.length; i++) {
            if (answerIndex != -1) {
                if (inputList[i] == '(') {
                    answerIndex++;
                    n++;
                } else if (inputList[i] == ')') {
                    answerIndex--;
                    n++;
                }
            }
        }

        System.out.println("Solution 1b: " + n);
    }

}
