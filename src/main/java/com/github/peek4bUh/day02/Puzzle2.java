package com.github.peek4bUh.day02;

import com.github.peek4bUh.aoc2024.BasePuzzle;
import com.github.peek4bUh.utils.ReadFile;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --- Part Two ---
 *
 * The engineers are surprised by the low number of safe reports until they
 * realize they forgot to tell you about the Problem Dampener.
 *
 * The Problem Dampener is a reactor-mounted module that lets the reactor safety
 * systems tolerate a single bad level in what would otherwise be a safe report.
 * It's like the bad level never happened!
 *
 * Now, the same rules apply as before, except if removing a single level from
 * an unsafe report would make it safe, the report instead counts as safe.
 *
 * More of the above example's reports are now safe:
 *
 * 7 6 4 2 1: Safe without removing any level. 1 2 7 8 9: Unsafe regardless of
 * which level is removed. 9 7 6 2 1: Unsafe regardless of which level is
 * removed. 1 3 2 4 5: Safe by removing the second level, 3. 8 6 4 4 1: Safe by
 * removing the third level, 4. 1 3 6 7 9: Safe without removing any level.
 *
 * Thanks to the Problem Dampener, 4 reports are actually safe!
 *
 * Update your analysis by handling situations where the Problem Dampener can
 * remove a single level from unsafe reports. How many reports are now safe?
 *
 * @author peek4bUh
 */
public class Puzzle2 implements BasePuzzle {

    @Override
    public void play() {
        List<String> rawData = ReadFile.getResourceFileAsList("input-day02.txt");
        List<List<Integer>> reports = rawData.stream()
                .map(str -> Arrays.stream(str.split("\\s"))
                .map(Integer::valueOf)
                .collect(Collectors.toList()))
                .collect(Collectors.toList());

        int safeReports = 0;

        for (List<Integer> report : reports) {
            if (isSafe(report)) {
                safeReports++;
                continue;
            }

            for (int i = 0; i < report.size(); i++) {
                int n = report.get(i);
                report.remove(i);

                if (isSafe(report)) {
                    safeReports++;
                    break;
                }

                report.add(i, n);
            }
        }

        System.out.println("Solution 2b: " + safeReports);
    }

    public static boolean isSafe(List<Integer> report) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < report.size() - 1; i++) {
            int diff = report.get(i + 1) - report.get(i);

            if (diff < 1 || diff > 3) {
                increasing = false;
            }
            if (diff > -1 || diff < -3) {
                decreasing = false;
            }
            if (!increasing && !decreasing) {
                return false;
            }
        }

        return increasing || decreasing;
    }
}
