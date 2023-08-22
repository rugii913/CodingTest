package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TmpTest {
    Lessons120863 solution = new Lessons120863();
    String polynomial = "3 + 5x + 6 + 8 + 9 + 10x";
    String result = "4x + 7";

    @Test
    void solution() {
        assertEquals(result, solution.solutionex(polynomial));
    }
}