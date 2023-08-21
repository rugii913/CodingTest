package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TmpTest {
    Lessons120913 solution = new Lessons120913();
    String my_str = "abc1Addfggg4556b";
    int n = 6;
    String[] result = {"abc1Ad", "dfggg4", "556b"};

    @Test
    void solution() {
        assertArrayEquals(result, solution.solution1(my_str, n));
    }
}