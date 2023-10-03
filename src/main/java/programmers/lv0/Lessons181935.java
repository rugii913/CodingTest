package programmers.lv0;

import java.util.stream.IntStream;

// lv0 홀짝에 따라 다른 값 반환하기
public class Lessons181935 {

    // 풀이 1 // 0.01ms ~ 0.04ms
    public int solution1(int n) {
        return n % 2 != 0 ? getSumOfOddTo1(n) : getSumOfSquareOfEvenTo1(n);
    }

    private int getSumOfOddTo1(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i += 2) {
            sum += i;
        }
        return sum;
    }

    private int getSumOfSquareOfEvenTo1(int n) {
        int sumOfSquare = 0;
        for (int i = 2; i <= n; i += 2) {
            sumOfSquare += i * i;
        }
        return sumOfSquare;
    }

    // 풀이 1-1(다른 풀이 참고) - 등차수열 합 등 // 0.01ms ~ 0.03ms
    public int solution1_1(int n) {
        if (n % 2 != 0) {
            return (n + 1) * (n + 1) / 2 / 2;
        } else {
            return 4 * n / 2 * (n / 2 + 1) * (2 * n / 2 + 1) / 6;
        }
    }

    // 풀이 2(다른 풀이 참고) - stream // 0.01ms ~ 0.04ms
    public int solution2(int n) {
        if (n % 2 == 0) {
            return IntStream.rangeClosed(1, n).filter(i -> i % 2 == 0).map(i -> (int) Math.pow(i, 2)).sum();
        } else {
            return IntStream.rangeClosed(1, n).filter(i -> i % 2 == 1).sum();
        }
    }

    // 풀이 2-1(다른 풀이 참고) - stream // 0.01ms ~ 0.04ms
    public int solution2_1(int n) {
        return n % 2 != 0 ? IntStream.iterate(1, i -> i <= n, i -> i + 2).sum()
                : (int) IntStream.iterate(2, i -> i <= n, i -> i + 2).mapToDouble(operand -> Math.pow(operand, 2)).sum();
    }
}
