package com.github.peek4bUh.day04;

import com.github.peek4bUh.aoc2024.BasePuzzle;
import com.github.peek4bUh.utils.ReadFile;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * --- Day 4: Ceres Search ---
 *
 * "Looks like the Chief's not here. Next!" One of The Historians pulls out a
 * device and pushes the only button on it. After a brief flash, you recognize
 * the interior of the Ceres monitoring station!
 *
 * As the search for the Chief continues, a small Elf who lives on the station
 * tugs on your shirt; she'd like to know if you could help her with her word
 * search (your puzzle input). She only has to find one word: XMAS.
 *
 * This word search allows words to be horizontal, vertical, diagonal, written
 * backwards, or even overlapping other words. It's a little unusual, though, as
 * you don't merely need to find one instance of XMAS - you need to find all of
 * them. Here are a few ways XMAS might appear, where irrelevant characters have
 * been replaced with .:
 *
 * ..X... .SAMX. .A..A. XMAS.S .X....
 *
 * The actual word search will be full of letters instead. For example:
 *
 * MMMSXXMASM MSAMXMSMSA AMXSXMAAMM MSAMASMSMX XMASAMXAMM XXAMMXXAMA SMSMSASXSS
 * SAXAMASAAA MAMMMXMMMM MXMXAXMASX
 *
 * In this word search, XMAS occurs a total of 18 times; here's the same word
 * search again, but where letters not involved in any XMAS have been replaced
 * with .:
 *
 * ....XXMAS. .SAMXMS... ...S..A... ..A.A.MS.X XMASAMX.MM X.....XA.A S.S.S.S.SS
 * .A.A.A.A.A ..M.M.M.MM .X.X.XMASX
 *
 * Take a look at the little Elf's word search. How many times does XMAS appear?
 *
 * @author peek4bUh
 */
public class Puzzle1 implements BasePuzzle {

    public static final int UP_RIGHT = 2;
    public static final int UP_LEFT = 4;

    @Override
    public void play() {
        List<String> horizontalWords = ReadFile.getResourceFileAsList("input-day04.txt");
        List<String> verticalWords = getMatrixVertical(horizontalWords);
        List<String> diagonalsLeftWords = getMatrixDiagonal(horizontalWords, UP_LEFT);
        List<String> diagonalsRightWords = getMatrixDiagonal(horizontalWords, UP_RIGHT);
        int total = getCount(horizontalWords)
                + getCount(verticalWords)
                + getCount(diagonalsLeftWords)
                + getCount(diagonalsRightWords);

        System.out.println("Solution 4a: " + total);
    }

    private List<List<Character>> getCharacterList2d(List<String> myList) {
        return myList.stream()
                .map(str -> str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private List<String> getMatrixVertical(List<String> myList) {
        List<List<Character>> charList = getCharacterList2d(myList);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < charList.size(); i++) {
            String vline = "";

            for (int j = 0; j < charList.get(i).size(); j++) {
                vline += String.valueOf(charList.get(j).get(i));
            }
            list.add(vline);
        }

        return list;
    }

    public List<String> getMatrixDiagonal(List<String> grid, int direction) {
        List<String> list = new ArrayList<String>();
        List<List<Character>> charList = getCharacterList2d(grid);
        int rows = charList.size();
        int cols = charList.get(0).size();

        if (direction == UP_LEFT) {
            for (int i = 0; i < rows; i++) {
                String newWord = "";

                for (int j = i; j > -1; j--) {
                    newWord += String.valueOf(charList.get(j).get(i - j));
                }
                list.add(newWord);
            }

            for (int i = 1; i < rows; i++) {
                String newWord = "";

                for (int j = rows - 1; j >= i; j--) {
                    newWord += String.valueOf(charList.get(j).get(i + rows - 1 - j));
                }
                list.add(newWord);
            }
        }

        if (direction == UP_RIGHT) {
            Integer altura = charList.size(), anchura = charList.get(0).size();

            for (Integer diagonal = 1 - anchura; diagonal <= altura - 1; diagonal += 1) {
                String newWord = "";

                for (Integer vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal); vertical < altura && horizontal < anchura; vertical += 1, horizontal += 1) {
                    newWord += String.valueOf(charList.get(vertical).get(horizontal));
                }
                list.add(newWord);
            }
        }
        return list;
    }

    private int getCount(List<String> grid) {
        String regex = "(?=XMAS|SAMX)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(grid.toString());
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        return count;
    }

}
