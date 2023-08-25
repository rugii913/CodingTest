package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TmpTest {
    Lessons120878 solution = new Lessons120878();
    int a = 29;
    int b = 23;
    int result = 1;

    @Test
    void solution() {
        assertEquals(result, solution.solution2(a, b));
    }
}