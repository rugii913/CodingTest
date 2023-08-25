package programmers.lv0;

// lv0 평행
public class Lessons120875 {

    // 풀이 x - "주어진 네 개의 점을 두 개씩 이었을 때"가 (점 1 ~ 점 2) (점 1 ~ 점 3) 이렇게 같은 점 있는 경우는 포함되지 않는 것이라고 함.
    // https://school.programmers.co.kr/questions/44837 참고
    public int solutionx(int[][] dots) {
        // 직선 6개 기울기 -> 기약분수로 만들어서 비교
        int combination2OfDots = factorial(dots.length) / (factorial(dots.length - 2) * 2);
        int[][] slopes = new int[combination2OfDots][2];

        int numOfSlopes = 0;
        for (int i = 0; i < dots.length - 1; i++) {
            for (int j = i + 1; j < dots.length; j++) {
                // 제한사항에 의해 x의 차가 0이거나 y의 차가 0인 경우는 없음
                int denominator = dots[j][0] - dots[i][0]; // x의 차 -> 분모 denominator
                int numerator = dots[j][1] - dots[i][1]; // y의 차 -> 분자 numerator

                if (numerator < 0 && denominator < 0) { // 둘 다 음수라면, 둘 다 양수로 바꿔줌
                    numerator *= -1;
                    denominator *= -1;
                } else if (numerator > 0 && denominator < 0) { // 분자가 양수 분모가 음수라면 - 분자가 음수, 분모가 양수가 되도록 바꿔줌
                    numerator *= -1;
                    denominator *= -1;
                }

                slopes[numOfSlopes][0] = numerator; // 인덱스 0에 분자 저장
                slopes[numOfSlopes++][1] = denominator; // 인덱스 1에 분모 저장
            }
        }

        for (int[] slope : slopes) {
            int gcd;
            if (slope[0] < 0) {
                gcd = getGCD(-slope[0], slope[1]);
            } else {
                gcd = getGCD(slope[0], slope[1]);
            }
            slope[0] /= gcd;
            slope[1] /= gcd;
        }

        int answer = 0;
        for (int i = 0; i < slopes.length; i++) {
            for (int j = i + 1; j < slopes.length; j++) {
                if (slopes[i][0] == slopes[j][0] && slopes[i][1] == slopes[j][1]) {
                    answer = 1;
                    return answer;
                }
            }
        }
        return answer;
    }
    private int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
    private int getGCD(int a, int b) {
        if (a < b) {
            return getGCD(b, a);
        }
        if (a % b == 0) {
            return b;
        } else {
            // return getGCD(b, a - b);
            return getGCD(b, a % b);
        }
    }

    // 풀이 1 - continue로 앞에서 사용한 수이면 거르는 로직을 더 간단하게 만들 수 없을지??
    public int solution1(int[][] dots) {
        // 3가지 경우의 수 (0-1 2-3 비교) (0-2 1-3 비교) (0-3 1-2 비교)
        int answer = 0;

        for (int i = 1; i < dots.length; i++) {
            int criteriaSlopeDenominator = dots[i][0] - dots[0][0];
            int criteriaSlopeNumerator = dots[i][1] - dots[0][1];

            int compSlopeDenominator = 0;
            int compSlopeNumerator = 0;
            for (int j = 1; j < dots.length; j++) {
                if (j == i) {
                    continue;
                } else {
                    for (int k = 1; k < dots.length; k++) {
                        if (k == i || k == j) {
                            continue;
                        } else {
                            compSlopeDenominator = dots[j][0] - dots[k][0];
                            compSlopeNumerator = dots[j][1] - dots[k][1];
                        }
                    }
                }
            }

            if (criteriaSlopeDenominator * compSlopeNumerator == criteriaSlopeNumerator * compSlopeDenominator) {
                answer = 1;
                break;
            }
        }
        return answer;
    }
}
