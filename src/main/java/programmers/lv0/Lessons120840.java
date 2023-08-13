package programmers.lv0;

import java.math.BigInteger;

// lv0 구슬을 나누는 경우의 수
public class Lessons120840 {
    /*
    * +16점...?
    * 반환값이 int이지만, 곱셈에서 overflow 발생하는 것이 문제
    * ? factorial을 사용할 때는 왜 double을 사용했을 때 실패하는 걸까?
    * - 풀이 2 실패하는 경우 3까지 연구 필요할 듯 -> 직접 테스트 해보자
    * - 조합(combination), BigInteger
    * */

    /*
    * - 조합 기본 공식: nCm = n! / {(n - m)! * m!}
    *
    * - n-1, r-1과의 관계: nCm = (n / m) * (n-1)C(m-1)
    *   <=<= nCm = n! / {(n-m)! * m!}
    *            = {n * (n-1)!} / [{(n-1) - (m-1)}! * (m - 1)! * m]
    *            = (n / m) * (n-1)C(m-1)
    *   =>=> 풀이 3에서 이용
    *
    * - 점화식: nCm = (n-1)C(r-1) + (n-1)Cr
    *   (참고 https://velog.io/@soyeon207/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%A1%B0%ED%95%A9-Combination)
    *   // (n-1)C(r-1) 어떤 특정한 원소를 이미 뽑은 경우
    *   // (n-1)Cr 어떤 특정한 원소를 이미 제외한 경우
    * */

    // 풀이 1
    public int solution1(int balls, int share) {
        double answer = 1;
        for (int i = balls; i > (balls - share); i--) {
            answer *= i;
        }
        for (int i = share; i > 0; i--) {
            answer /= i;
        }
        return (int) answer;
    }

    // 풀이 1 실패하는 경우 - overflow 고려 x
    public int solution1x(int balls, int share) {
        int answer = 1;
        for (int i = balls; i > (balls - share); i--) {
            answer *= i;
        }
        for (int i = share; i > 0; i--) {
            answer /= i;
        }
        return answer;
    }

    // 풀이 2 - solution2xx를 참고하여 overflow를 막기 위한 꼼수
    public int solution2(int balls, int share) {
        return (int) (pseudoFactorialToM(balls, balls - share) / factorial2(share));
    }
    private double factorial2(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial2(n-1);
        }
    }
    private double pseudoFactorialToM(int n, int m) {
        if (n == m) {
            return 1;
        } else {
            return n * pseudoFactorialToM(n-1, m);
        }
    }

    // 풀이 2 실패하는 경우 1 - overflow 고려 x
    public int solution2x(int balls, int share) {
        return factorial2x(balls) / factorial2x(balls - share) / factorial2x(share);
    }
    private int factorial2x(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial2x(n-1);
        }
    }

    // 풀이 2 실패하는 경우 2 - overflow 고려하여 double을 반환하는 factorial을 사용했음에도 실패한다. // 85.7점
    public int solution2xx(int balls, int share) {
        return (int) (factorial2(balls) / factorial2(balls - share) / factorial2(share));
    }

    // 풀이 2 실패하는 경우 3 - 위처럼 실패하는 경우, 소수점을 고려하지 않아서 그런 것인지 궁금해서 반올림했음에도 실패한다.
    // 점수도 오히려 위보다 떨어짐 // 77.1점
    public int solution2xxx(int balls, int share) {
        long a = Math.round(factorial2(balls) / factorial2(balls - share));
        long b = Math.round(a / factorial2(share));
        return (int) b;
    }

    // 풀이 3(다른 풀이 참고) - nCm = (n / m) * (n-1)C(m-1)
    public int solution3(int balls, int share) {
        return (int) solution3Recursive(balls, share);
    }
    private long solution3Recursive(int balls, int share) {
        /*
        share = Math.min(balls - share, share);
        if (share == 0) {
            return 1;
        }
        // 주석처리된 부분이 원래 참고 코드, 아래 주석 없는 코드 사용해도 무방함
        */
        /*
        int d = (balls - share) > share ? share : balls - share;
        if (d == 0) return 1;
        // 위 주석 코드와 거의 같은 코드
        */
        if (share == 0 || share == balls) {
            return 1;
        }

        /*
        long result = solution3Recursive(balls - 1, share - 1);
        result *= balls;
        result /= share;
        // 주석처리된 부분이 원래 참고 코드, 아래처럼 사용하는 편이 오히려 보기 편할 듯
        */

        return solution3Recursive(balls - 1, share - 1) * balls / share;
    }

    // 풀이 4(점화식 참고) - 점화식: nCm = (n-1)C(r-1) + (n-1)Cr // 특정 케이스에서 매우 매우 느려진다. 거의 500ms까지...
    public int solution4(int balls, int share) {
        return (int) solution4Recursive(balls, share);
    }
    private long solution4Recursive(int balls, int share) {
        if (share == 0 || share == balls) {
            return 1;
        }
        return solution4Recursive(balls - 1, share - 1) + solution4Recursive(balls - 1, share);
    }

    // 풀이 5(다른 풀이 참고) - BigInteger 사용 // 1,2,3보다 성능이 좋진 않다. 하지만 아주 나쁘진 않음. 대체로 1ms 이하
    public int solution5(int balls, int share) {
        return factorial5(balls).divide(factorial5(balls - share).multiply(factorial5(share))).intValue();
    }
    private BigInteger factorial5(int n){
        BigInteger result = BigInteger.ONE;
        BigInteger from = BigInteger.ONE;
        BigInteger to = new BigInteger(String.valueOf(n));

        for (BigInteger i = from; i.compareTo(to) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }

        return result;
    }
}
