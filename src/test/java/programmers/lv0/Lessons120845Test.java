package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lessons120845Test {
    Lessons120845 solution = new Lessons120845();
    int[] box = {10, 8, 6};
    int n = 3;
    int result = 12;

    @Test
    void solution3() {
        assertEquals(result, solution.solution3(box, n));
    }
}