package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TmpTest {
    Lessons120864 solution = new Lessons120864();
    String my_string = "abc1bcd";
    int result = 0;

    @Test
    void solution() {
        assertEquals(result, solution.solution2(my_string));
    }
}