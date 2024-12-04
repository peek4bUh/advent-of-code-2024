package com.github.peek4bUh.day03;

import com.github.peek4bUh.aoc2024.BasePuzzle;
import com.github.peek4bUh.utils.ReadFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Part Two ---
 *
 * As you scan through the corrupted memory, you notice that some of the
 * conditional statements are also still intact. If you handle some of the
 * uncorrupted conditional statements in the program, you might be able to get
 * an even more accurate result.
 *
 * There are two new instructions you'll need to handle:
 *
 * The do() instruction enables future mul instructions. The don't() instruction
 * disables future mul instructions.
 *
 * Only the most recent do() or don't() instruction applies. At the beginning of
 * the program, mul instructions are enabled.
 *
 * For example:
 *
 * xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
 *
 * This corrupted memory is similar to the example from before, but this time
 * the mul(5,5) and mul(11,8) instructions are disabled because there is a
 * don't() instruction before them. The other mul instructions function
 * normally, including the one at the end that gets re-enabled by a do()
 * instruction.
 *
 * This time, the sum of the results is 48 (2*4 + 8*5).
 *
 * Handle the new instructions; what do you get if you add up all of the results
 * of just the enabled multiplications?
 *
 * @author peek4bUh
 */
public class Puzzle2 implements BasePuzzle {

    @Override
    public void play() {
        String input = ReadFile.getResourceFileAsString("input-day03.txt");
        String regex = "do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        boolean isEnabled = true;
        int total = 0;

        while (matcher.find()) {
            String match = matcher.group();

            if (match.equals("do()")) {
                isEnabled = true;
            } else if (match.equals("don't()")) {
                isEnabled = false;
            } else if (match.startsWith("mul")) {
                if (isEnabled) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    total += x * y;
                }
            }
        }

        System.out.println("Solution 3b: " + total);
    }
}
