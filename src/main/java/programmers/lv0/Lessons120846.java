package programmers.lv0;

import java.util.stream.IntStream;

// lv0 합성수 찾기
public class Lessons120846 {
    /*
    * 합성수 - 약수의 개수가 세 개 이상인 수(소수가 아닌 것으로 이해하면 될 것 같다.)
    * */

    // 풀이 1 - brute force
    public int solution1(int n) {
        int count = 0;
        if (n < 3) {
            return 0;
        }
        for (int i = 3; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    // 풀이 2(다른 풀이 힌트) - 함수를 분리할 수도 있겠다.
    public int solution2(int n) {
        int count = 0;
        if (n < 3) {
            return 0;
        }
        for (int i = 3; i <= n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }
    private boolean isPrime(int i) {
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                return true;
            }
        }
        return false;
    }

    // 풀이 3(다른 풀이 참고)
    public int solution3(int n) {
        return (int) IntStream.rangeClosed(1, n)
                .filter(i -> (int) IntStream.rangeClosed(1, i).filter(j -> i % j == 0).count() > 2)
                .count();
    }
}
