package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Lessons120876Test {
    Lessons120876 solution = new Lessons120876();
    int[][] lines1 = {{0, 1}, {2, 5}, {3, 9}}; // 2-3
    int[][] lines2 = {{-1, 1}, {1, 3}, {3, 9}}; // x
    int[][] lines3 = {{0, 5}, {3, 9}, {1, 10}}; // 1-2-3
    int[][] lines4 = {{0, 2}, {2, 6}, {4, 6}};
    int[][] lines5 = {{0, 3}, {1, 2}, {3, 4}}; // 실패 케이스
    int result1 = 2;
    int result2 = 0;
    int result3 = 8;
    int result4 = 2;
    int result5 = 1; // 실패 케이스

    @Test
    void solution() {
        assertEquals(result1, solution.solution1_1(lines1));
        assertEquals(result2, solution.solution1_1(lines2));
        assertEquals(result3, solution.solution1_1(lines3));
        assertEquals(result4, solution.solution1_1(lines4));
        assertEquals(result5, solution.solution1_1(lines5));
    }
}