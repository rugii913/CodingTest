package programmers.lv3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Lessons86053Sol1Test {

    Lessons86053 solutions = new Lessons86053();

    @Test
    void testcase1() {
        Assertions.assertEquals(50, solutions.solution(10, 10, new int[]{100}, new int[]{100}, new int[]{7}, new int[]{10}));
    }

    @Test
    void testcase2() {
        Assertions.assertEquals(499, solutions.solution(90, 500, new int[]{70,70,0}, new int[]{0,0,500}, new int[]{100,100,2}, new int[]{4,8,1}));
    }
}