package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TmpTest {
    Lessons120848 solution = new Lessons120848();
    int n = 3628800;
    int result = 10;

    @Test
    void solution() {
        assertEquals(result, solution.solution4(n));
    }
}