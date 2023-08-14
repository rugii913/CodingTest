package programmers.lv0;

import java.util.Arrays;

// lv0 원소들의 곱과 합
public class Lessons181929 {
    /*
    * - 풀이 2 IntStream에서 모든 수의 곱을 구하는 방법
    * */

    // 풀이 1
    public int solution1(int[] num_list) {
        int product = 1;
        int sum = 0;

        for (int num : num_list) {
            product *= num;
            sum += num;
        }

        return product < (sum * sum) ? 1 : 0;
    }

    // 풀이 2(다른 풀이 참고) - 당연히 느리다
    public int solution2(int[] num_list) {
        return (Arrays.stream(num_list).reduce((acc, i) -> acc * i).getAsInt() < Math.pow(Arrays.stream(num_list).sum(), 2)) ? 1 : 0;
    }
}
