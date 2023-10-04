package programmers.lv0;

import java.util.stream.IntStream;

// lv0 등차수열의 특정한 항만 더하기
public class Lessons181931 {

    // 풀이 1 - 빠르긴 하겠지만 의미는 잘 드러나지 않을 듯 // 0.01ms ~ 0.03ms
    public int solution1(int a, int d, boolean[] included) {
        int answer = 0;
        for (int i = 0; i < included.length; i++) {
            if (included[i]) {
                answer += a + d * i;
            }
        }
        return answer;
    }

    // 풀이 1-1 // 0.02ms ~ 0.05ms
    public int solution1_1(int a, int d, boolean[] included) {
        int[] arithmeticSequence = new int[included.length];

        int answer = 0;

        arithmeticSequence[0] = a;
        answer += included[0] ? arithmeticSequence[0] : 0;

        for (int i = 1; i < included.length; i++) {
            arithmeticSequence[i] = arithmeticSequence[i - 1] + d;
            answer += included[i] ? arithmeticSequence[i] : 0;
        }

        return answer;
    }

    // 풀이 2 - stream // 1.63ms ~ 6.25ms
    public int solution2(int a, int d, boolean[] included) {
        return IntStream.range(0, included.length)
                .filter(i -> included[i])
                .reduce(0, (prevInt, currentIndex) -> prevInt + (a + d * currentIndex));
    }

    // 풀이 2-1(다른 풀이 참고) - stream // 2.48ms ~ 15.46ms
    public int solution2_1(int a, int d, boolean[] included) {
        return IntStream.range(0, included.length)
                .map(idx -> included[idx] ? a + (idx * d) : 0)
                .sum();
    }
}
