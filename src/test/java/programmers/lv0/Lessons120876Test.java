package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Lessons120876Test {
    Lessons120876 solution = new Lessons120876();
    int[][] lines = {{0, 5}, {3, 9}, {1, 10}};
    int result = 8;

    @Test
    void solution() {
        assertEquals(result, solution.solution2(lines));
    }
}