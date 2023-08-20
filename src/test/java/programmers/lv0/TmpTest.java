package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TmpTest {
    Lessons120896 solution = new Lessons120896();
    String s = "hello";
    String result = "eho";

    @Test
    void solution() {
        assertEquals(result, solution.solution2(s));
    }
}