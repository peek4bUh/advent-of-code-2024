package com.github.peek4bUh.day01;

import com.github.peek4bUh.aoc2024.BasePuzzle;
import com.github.peek4bUh.utils.ReadFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * --- Part Two ---
 *
 * Your analysis only confirmed what everyone feared: the two lists of location
 * IDs are indeed very different.
 *
 * Or are they?
 *
 * The Historians can't agree on which group made the mistakes or how to read
 * most of the Chief's handwriting, but in the commotion you notice an
 * interesting detail: a lot of location IDs appear in both lists! Maybe the
 * other numbers aren't location IDs at all but rather misinterpreted
 * handwriting.
 *
 * This time, you'll need to figure out exactly how often each number from the
 * left list appears in the right list. Calculate a total similarity score by
 * adding up each number in the left list after multiplying it by the number of
 * times that number appears in the right list.
 *
 * Here are the same example lists again:
 *
 * 3 4
 * 4 3
 * 2 5
 * 1 3
 * 3 9
 * 3 3
 *
 * For these example lists, here is the process of finding the similarity score:
 *
 * The first number in the left list is 3. It appears in the right list three
 * times, so the similarity score increases by 3 * 3 = 9. The second number in
 * the left list is 4. It appears in the right list once, so the similarity
 * score increases by 4 * 1 = 4. The third number in the left list is 2. It does
 * not appear in the right list, so the similarity score does not increase (2 *
 * 0 = 0). The fourth number, 1, also does not appear in the right list. The
 * fifth number, 3, appears in the right list three times; the similarity score
 * increases by 9. The last number, 3, appears in the right list three times;
 * the similarity score again increases by 9.
 *
 * So, for these example lists, the similarity score at the end of this process
 * is 31 (9 + 4 + 0 + 0 + 9 + 9).
 *
 * Once again consider your left and right lists. What is their similarity
 * score?
 *
 *
 * @author peek4bUh
 */
public class Puzzle2 implements BasePuzzle {

    @Override
    public void play() {
        List<String> inputs = ReadFile.getResourceFileAsList("input-day01.txt");
        List<Long> a = new ArrayList<>();
        List<Long> b = new ArrayList<>();

        for (int i = 0; i < inputs.size(); i++) {
            Long n1 = Long.valueOf(inputs.get(i).split("   ")[0]);
            Long n2 = Long.valueOf(inputs.get(i).split("   ")[1]);
            a.add(n1);
            b.add(n2);
        }

        Collections.sort(a);
        Collections.sort(b);

        long total = 0;

        for (int i = 0; i < a.size(); i++) {
            total += calculateNumberTimes(a.get(i), b);
        }

        System.out.println("Solution 1b: " + total);
    }

    private static long calculateNumberTimes(long n, List<Long> list) {
        int nTotalTimes = 0;

        for (Long element : list) {
            if (n == element) {
                nTotalTimes += 1;
            }
        }

        return n * nTotalTimes;
    }
}
