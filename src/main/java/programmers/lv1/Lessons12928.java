package programmers.lv1;

// lv1 약수의 합
public class Lessons12928 {
    /*
    * lv0 소인수 분해 Lessons120852 다시 볼 것
    * */

    // 풀이 1 - 약수의 특성 활용하여 sqrt까지 줄인 경우
    public int solution1(int n) {
        int sum = 0;
        double sqrtN = Math.sqrt(n);

        for (int i = 1; i < sqrtN; i++) { // 종료 조건을 i <= sqrtN로 둘 경우, n이 제곱수일 때 반례가 생긴다. ex. n = 16
            if (n % i == 0) {
                sum += i;
                sum += n / i;
            }
        }

        if ((int) sqrtN * (int) sqrtN == n) { // sqrtN * sqrtN == n으로 둘 경우 실패, 정확히 어떤 반례가 발생하는지는 모르겠음
            sum += (int) sqrtN;
        }

        return sum;
    }

    // 풀이 1-1 - 가능
    public int solution1_1(int n) {
        int sum = 0;
        int maxIntegerLessThanSqrtN = (int) Math.sqrt(n);

        for (int i = 1; i <= maxIntegerLessThanSqrtN; i++) {
            if (n % i == 0) {
                sum += i;
                sum += n / i;
            }
        }

        if (maxIntegerLessThanSqrtN * maxIntegerLessThanSqrtN == n) {
            sum -= maxIntegerLessThanSqrtN;
        }

        return sum;
    }

    // 풀이 2(다른 풀이 참고) - 1/2까지로 줄인 경우
    public int solution2(int n) {
        int answer = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                answer += i;
            }
        }

        return answer + n;
    }
}
