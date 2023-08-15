package programmers.lv0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TmpTest {
    Lessons120846 solution = new Lessons120846();
    int n = 10;
    int result = 5;

    @Test
    void solution() {
        assertEquals(result, solution.solution1(n));
    }
}