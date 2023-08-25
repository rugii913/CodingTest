package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TmpTest {
    Lessons120875 solution = new Lessons120875();
    int[][] dots = {{1, 4}, {9, 2}, {3, 8}, {11, 6}};
    int result = 1;

    @Test
    void solution() {
        assertEquals(result, solution.solution1_2(dots));
    }
}