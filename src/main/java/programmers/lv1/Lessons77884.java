package programmers.lv1;

// lv1 약수의 개수와 덧셈
public class Lessons77884 {

    // 풀이 1
    public int solution1(int left, int right) {
        int answer = 0;

        for (int i = left; i <= right; i++) {
            if (isEvenDivisorCount(i)) {
                answer += i;
            } else {
                answer -= i;
            }
        }

        return answer;
    }

    private boolean isEvenDivisorCount(int number) {
        int floorIntOfSqrt = (int) Math.sqrt(number);

        return floorIntOfSqrt * floorIntOfSqrt != number;
        // return number % floorIntOfSqrt != 0; // x 이건 안 됨 ex. number = 15이면 floorIntOfSqrt = 3, 나머지 0 나옴
        // --> 아래 풀이 1-1처럼 double 자료형 그대로 따져야 한다.
        // return number / floorIntOfSqrt != floorIntOfSqrt; // x 이것도 안 됨 ex. number = 17이면 floorIntOfSqrt = 4 ==> number / floorIntOfSqrt == floorIntOfSqrt
    }

    // 풀이 1-1(다른 풀이 참고)
    public int solution1_1(int left, int right) {
        int answer = 0;

        for (int i = left; i <= right; i++) {
            // 제곱수인 경우 약수의 개수가 홀수
            if (i % Math.sqrt(i) == 0) {
                answer -= i;
            }
            // 제곱수가 아닌 경우 약수의 개수가 짝수
            else {
                answer += i;
            }
        }

        return answer;
    }
}
