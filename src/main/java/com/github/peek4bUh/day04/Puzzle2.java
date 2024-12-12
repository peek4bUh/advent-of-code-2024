package com.github.peek4bUh.day04;

import com.github.peek4bUh.aoc2024.BasePuzzle;
import static com.github.peek4bUh.day04.Puzzle1.UP_LEFT;
import static com.github.peek4bUh.day04.Puzzle1.UP_RIGHT;
import com.github.peek4bUh.utils.ReadFile;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --- Part Two ---
 *
 * The Elf looks quizzically at you. Did you misunderstand the assignment?
 *
 * Looking for the instructions, you flip over the word search to find that this
 * isn't actually an XMAS puzzle; it's an X-MAS puzzle in which you're supposed
 * to find two MAS in the shape of an X. One way to achieve that is like this:
 *
 * M.S .A. M.S
 *
 * Irrelevant characters have again been replaced with . in the above diagram.
 * Within the X, each MAS can be written forwards or backwards.
 *
 * Here's the same example from before, but this time all of the X-MASes have
 * been kept instead:
 *
 * .M.S...... ..A..MSMS. .M.S.MAA.. ..A.ASMSM. .M.S.M.... .......... S.S.S.S.S.
 * .A.A.A.A.. M.M.M.M.M. ..........
 *
 * In this example, an X-MAS appears 9 times.
 *
 * Flip the word search from the instructions back over to the word search side
 * and try again. How many times does an X-MAS appear?
 *
 * @author peek4bUh
 */
public class Puzzle2 implements BasePuzzle {

    @Override
    public void play() {
        List<String> horizontalWords = ReadFile.getResourceFileAsList("input-day04.txt");
        List<List<Character>> grid = horizontalWords.stream()
                .map(str -> str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList()))
                .collect(Collectors.toList());
        List<List<Integer>> coordinates = getCoordinatesLetterA(grid);
        int total = 0;

        for (int i = 0; i < coordinates.size(); i++) {
            int row = coordinates.get(i).get(0);
            int column = coordinates.get(i).get(1);

            // Top-Bottom
            if (grid.get(row - 1).get(column - 1) == 'M'
                    && grid.get(row - 1).get(column + 1) == 'M'
                    && grid.get(row + 1).get(column + 1) == 'S'
                    && grid.get(row + 1).get(column - 1) == 'S') {
                total += 1;
            } // Right-Left
            else if (grid.get(row - 1).get(column + 1) == 'M'
                    && grid.get(row + 1).get(column + 1) == 'M'
                    && grid.get(row + 1).get(column - 1) == 'S'
                    && grid.get(row - 1).get(column - 1) == 'S') {
                total += 1;
            } // Bottom-Top
            else if (grid.get(row + 1).get(column + 1) == 'M'
                    && grid.get(row + 1).get(column - 1) == 'M'
                    && grid.get(row - 1).get(column - 1) == 'S'
                    && grid.get(row - 1).get(column + 1) == 'S') {
                total += 1;
            } // Left-Right
            else if (grid.get(row + 1).get(column - 1) == 'M'
                    && grid.get(row - 1).get(column - 1) == 'M'
                    && grid.get(row - 1).get(column + 1) == 'S'
                    && grid.get(row + 1).get(column + 1) == 'S') {
                total += 1;
            }
        }

        System.out.println("Solution 4b: " + total);
    }

    private List<List<Integer>> getCoordinatesLetterA(List<List<Character>> charList) {
        List<List<Integer>> coordinatesLetterA = new ArrayList<>();

        for (int i = 0; i < charList.size(); i++) {
            for (int j = 0; j < charList.get(0).size(); j++) {
                if (charList.get(i).get(j) == 'A') {
                    if (i != 0 && i != charList.size() - 1
                            && j != 0 && j != charList.size() - 1) {
                        coordinatesLetterA.add(Arrays.asList(i, j));
                    }
                }
            }
        }

        return coordinatesLetterA;
    }
}
