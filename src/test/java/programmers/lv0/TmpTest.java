package programmers.lv0;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TmpTest {
    Lessons120897 solution = new Lessons120897();
    int n = 24;
    int[] result = {1, 2, 3, 4, 6, 8, 12, 24};

    @Test
    void solution() {
        Arrays.stream(solution.solution1(n)).forEach(System.out::println);
        assertArrayEquals(result, solution.solution1(n));
    }
}