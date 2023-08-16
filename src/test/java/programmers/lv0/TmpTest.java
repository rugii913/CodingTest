package programmers.lv0;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TmpTest {
    Lessons120852 solution = new Lessons120852();
    int n = 420;
    int[] result = {2, 3, 5, 7};

    @Test
    void solution() {
        // assertEquals(result, solution.solution1(n));
        // 배열 비교는 assertEquals(~) 사용하면 안 되고 assertArrayEquals() 사용해야 함
        Arrays.stream(solution.solution2(n)).forEach(System.out::println);
        assertArrayEquals(result, solution.solution2(n));
    }
}