package programmers.lv0;

import java.util.stream.IntStream;

// lv0 배열 만들기 1
public class Lessons181901 {

    // 풀이 1
    public int[] solution1(int n, int k) {
        int length = n / k;
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = (i + 1) * k;
        }
        return answer;
    }

    // 풀이 2 - IntStream 연산 후 toArray 반환하면, 그 반환 타입은 Integer[]가 아니라 int[]임
    public int[] solution2(int n, int k) {
        return IntStream.rangeClosed(1, n).filter(i -> i % k == 0).toArray();
    }
}
