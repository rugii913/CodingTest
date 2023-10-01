package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lessons181861Test {

    Lessons181861 solutions = new Lessons181861();

    @Test
    void solution3_1() { // 실패
        int[] input = new int[]{100, 100};
        int[] expectSolution = solutions.solution3_1(input);
        int[] actualSolution = solutions.solution3(input);
        assertArrayEquals(expectSolution, actualSolution);
    }

    @Test
    void solution3_2() {
        int[] input = new int[]{100, 100};
        int[] expectSolution = solutions.solution3_2(input);
        int[] actualSolution = solutions.solution3(input);
        assertArrayEquals(expectSolution, actualSolution);
    }
}