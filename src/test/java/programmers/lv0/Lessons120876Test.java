package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Lessons120876Test {
    Lessons120876 solution = new Lessons120876();
    int[][] lines1 = {{0, 1}, {2, 5}, {3, 9}}; // 2-3
    int[][] lines2 = {{-1, 1}, {1, 3}, {3, 9}}; // x
    int[][] lines3 = {{0, 5}, {3, 9}, {1, 10}}; // 1-2-3
    int[][] lines4 = {{0, 2}, {2, 6}, {4, 6}};
    int[][] lines5 = {{0, 3}, {1, 2}, {3, 4}}; // 풀이 1-1 실패 케이스 dots = [1, 2, 2, 2, 1]이 되므로 실패
    int[][] lines6 = {{0, 10}, {1, 4}, {4, 8}}; // 풀이 1-1 실패 케이스 dots = [1, 2, 2, 2, 1]이 되므로 실패
    int[][] lines7 = {{0, 10}, {1, 5}, {4, 8}}; // 풀이 1-1 실패 케이스 dots = [1, 2, 2, 2, 1]이 되므로 실패
    int result1 = 2;
    int result2 = 0;
    int result3 = 8;
    int result4 = 2;
    int result5 = 1; // 실패 케이스(풀이 2만 해결)
    int result6 = 7; // 실패 케이스
    int result7 = 7; // 실패 케이스

    @Test
    void solution1_1() {
        assertEquals(result1, solution.solution1_1(lines1));
        assertEquals(result2, solution.solution1_1(lines2));
        assertEquals(result3, solution.solution1_1(lines3));
        assertEquals(result4, solution.solution1_1(lines4));
        assertEquals(result5, solution.solution1_1(lines5));
        assertEquals(result6, solution.solution1_1(lines6));
        assertEquals(result7, solution.solution1_1(lines7));
    }

    @Test
    void solution1_2() {
        assertEquals(result1, solution.solution1_2(lines1));
        assertEquals(result2, solution.solution1_2(lines2));
        assertEquals(result3, solution.solution1_2(lines3));
        assertEquals(result4, solution.solution1_2(lines4));
        assertEquals(result5, solution.solution1_2(lines5));
        assertEquals(result6, solution.solution1_2(lines6));
        assertEquals(result7, solution.solution1_2(lines7));
    }

    @Test
    void solution2() {
        assertEquals(result1, solution.solution2(lines1));
        assertEquals(result2, solution.solution2(lines2));
        assertEquals(result3, solution.solution2(lines3));
        assertEquals(result4, solution.solution2(lines4));
        assertEquals(result5, solution.solution2(lines5));
        assertEquals(result6, solution.solution2(lines6));
        assertEquals(result7, solution.solution2(lines7));
    }
}