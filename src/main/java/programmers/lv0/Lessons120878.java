package programmers.lv0;

// lv0 유한소수 판별하기
public class Lessons120878 {

    // 풀이 1 +12점..? - 최대공약수 찾아서 분모 나누기 - 분모를 유사 소인수 분해(Lessons120852 참고)
    public int solution1(int a, int b) {
        int gcd = getGCD(a, b);

        int irreducibleB = b / gcd;
        while (irreducibleB % 2 == 0) {
            irreducibleB /= 2;
        }
        while (irreducibleB % 5 == 0) {
            irreducibleB /= 5;
        }

        return irreducibleB == 1 ? 1 : 2;
    }
    private int getGCD(int a, int b) {
        if (a < b) {
            return getGCD(b, a);
        }
        if (a % b == 0) {
            return b;
        } else {
            return getGCD(b, a % b);
        }
    }

    // 풀이 1-1(다른 풀이 참고) - 어차피 반복문 횟수는 같을 것이라, 풀이 1 방식이 더 보기 편한 듯하다.
    public int solution1_1(int a, int b) {
        int gcd = getGCD(a, b);

        int irreducibleB = b / gcd;
        while (irreducibleB != 1) {
            if (irreducibleB % 5 == 0) {
                irreducibleB /= 5;
            } else if (irreducibleB % 2 == 0) {
                irreducibleB /= 2;
            } else {
                return 2;
            }
        }
        return 1;
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(int a, int b) {
        for (; b % 2 == 0; ) { // for를 while처럼 사용함
            b = b / 2;
        }
        for (; b % 5 == 0; ) {
            b = b / 5;
        }

        int answer;
        if ((a / (double) b) % 1 == 0) {
            answer = 1;
        } else {
            answer = 2;
        }

        return answer;
        // gcd 로직 없이
        // "유한소수가 되기 위한 분수의 조건은 다음과 같습니다. - 기약분수로 나타내었을 때, 분모의 소인수가 2와 5만 존재해야 합니다."
        // => 분모를 소인수 분해했을 때, 2와 5를 없애주면 정수가 나와야 함
        // => (a / (double) b) % 1 == 0
    }
}
