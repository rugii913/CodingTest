package programmers.lv0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class Lessons120812Test {

    Lessons120812 solutions = new Lessons120812();

    int[] input1 = {1, 2, 3, 3, 3, 4};
    int output1 = 3;
    int[] input2 = {1, 1, 2, 2};
    int output2 = -1;
    int[] input3 = {1};
    int output3 = 1;

    Map<int[], Integer> testIOMap = new HashMap<>();

    @BeforeEach
    public void setTestIOMap() {
        testIOMap.put(input1, output1);
        testIOMap.put(input2, output2);
        testIOMap.put(input3, output3);
    }

    @Test
    void solution1Test() {
        for (int[] inputs : testIOMap.keySet()) {
            Assertions.assertEquals(solutions.solution1(inputs), testIOMap.get(inputs));
        }
    }

    @Test
    void solution2Test() {
        for (int[] inputs : testIOMap.keySet()) {
            Assertions.assertEquals(solutions.solution2(inputs), testIOMap.get(inputs));
        }
    }
}