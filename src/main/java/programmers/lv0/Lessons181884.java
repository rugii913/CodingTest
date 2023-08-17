package programmers.lv0;

import java.util.Arrays;

// lv0 n보다 커질 때까지 더하기
public class Lessons181884 {
    /*
    * - 풀이 2 -> for의 조건 자체를 줄 수 있음
    * - 풀이 3 -> stream.reduce() 안에서 조건을 줄 수 있음
    * */

    // 풀이 1
    public int solution1(int[] numbers, int n) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
            if (sum > n) {
                break;
            }
        }
        return sum;
    }

    // 풀이 2(다른 풀이 참고) - for의 조건 자체를 줄 수 있음
    public int solution2(int[] numbers, int n) {
        int sum = 0;
        for (int i = 0; sum <= n; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    // 풀이 3(다른 풀이 참고) - stream.reduce(~) acc는 accumulator의 약자
    public int solution3(int[] numbers, int n) {
        return Arrays.stream(numbers).reduce(0, (acc, number) -> n >= acc ? acc + number : acc);
    }
}
