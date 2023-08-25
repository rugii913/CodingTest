package programmers.lv0;

// lv0 평행
public class Lessons120875 {
    // cf. combination과는 약간 다른 형태임
    // 4개 중 순서 없이 2개를 골라냄 (x)
    // 4개 중 순서 없이 2개를 골라냄 - 남은 2개 중 2개를 골라냄 - 앞에서 선택하든 뒤에서 선택하든 결과는 같으므로 (/ 2) 해야 함 => 3
    // 실제로는 기준을 0번 인덱스로 정하고 3점 중 한 점을 고르면 나머지 점은 알아서 골라지는 형태가 됨

    // 풀이 x - "주어진 네 개의 점을 두 개씩 이었을 때"가 (점 1 ~ 점 2) (점 1 ~ 점 3) 이렇게 같은 점 있는 경우는 포함되지 않는 것이라고 함.
    // https://school.programmers.co.kr/questions/44837 참고
    // -> 4점 중 3점이 일직선에 있고 한 점이 떨어져 있는 경우가 문제
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

    // 풀이 1 - 기울기가 같으면 분모로 나타냈을 때 (분자 1 * 분모 2) == (분자 2 * 분모 1)임을 활용
    // ?? continue로 앞에서 사용한 수이면 거르는 로직을 더 간단하게 만들 수 없을지??
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
    // 풀이 1-1 - 풀이 1 수정
    // (1) if (j == i)  if (k == i || k == j) 거르는 부분 약간 정리
    // (2) 2/6이나 1/3이나 double로 가도 같으므로 double을 사용해도 됨
    // cf. 풀이 1이나 풀이 1-1이나 거의 같고, 경우의 수가 3개 뿐이라 문제가 생기지 않았을 뿐이지 근본적으로 좋은 순회는 아니라고 생각함
    // - 그럼 어떻게 고쳐야 할지?
    public int solution1_1(int[][] dots) {
        // 3가지 경우의 수 (0-1 2-3 비교) (0-2 1-3 비교) (0-3 1-2 비교)
        int answer = 0;

        for (int i = 1; i < dots.length; i++) {
            double slope = ((dots[i][1] - dots[0][1]) / (double) (dots[i][0] - dots[0][0]));

            double comparison = 0;
            for (int j = 1; j < dots.length; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 1; k < dots.length; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    comparison = ((dots[j][1] - dots[k][1]) / (double) (dots[j][0] - dots[k][0]));
                }
            }

            if (slope == comparison) {
                answer = 1;
                break;
            }
        }
        return answer;
    }
    // 풀이 1-2 - 풀이 1, 1-1 수정
    public int solution1_2(int[][] dots) {
        // 3가지 경우의 수 (0-1 2-3 비교) (0-2 1-3 비교) (0-3 1-2 비교)
        int answer = 0;

        for (int i = 1; i < dots.length; i++) { // i = dots[0]과 이어질 dot의 인덱스
            boolean[] isUsedIndex = {true, false, false, false}; // 사용 중인 인덱스를 표시할 변수 - for문 돌 때마다 초기화

            isUsedIndex[i] = true; // i 인덱스를 표시해둔다.
            double slope = ((dots[i][1] - dots[0][1]) / (double) (dots[i][0] - dots[0][0]));

            double comparison = 0;
            int compEndIndex1 = -1;
            int compEndIndex2 = -1;
            for (int j = 1; j < dots.length; j++) {
                if (!isUsedIndex[j]) {
                    isUsedIndex[j] = true;
                    compEndIndex1 = j;
                    break;
                }
            }
            for (int k = 1; k < dots.length; k++) {
                if (!isUsedIndex[k]) {
                    // isUsedIndex[k] = true; 마지막이므로 굳이 표시할 필요는 없다. - for문 앞으로 돌아갈 때 초기화될 것이기도 하고.
                    compEndIndex2 = k;
                    break;
                }
            }
            comparison = ((dots[compEndIndex1][1] - dots[compEndIndex2][1]) / (double) (dots[compEndIndex1][0] - dots[compEndIndex2][0]));

            if (slope == comparison) {
                answer = 1;
                break;
            }
        }
        return answer;
    }
    // 풀이 1-3 - 줄일 수 있는 부분 더 줄이기
    public int solution1_3(int[][] dots) {
        // 3가지 경우의 수 (0-1 2-3 비교) (0-2 1-3 비교) (0-3 1-2 비교)
        int answer = 0;

        for (int i = 1; i < dots.length; i++) { // i = dots[0]과 이어질 dot의 인덱스
            boolean[] isUsedIndex = {true, false, false, false}; // 사용 중인 인덱스를 표시할 변수 - for문 돌 때마다 초기화

            isUsedIndex[i] = true; // i 인덱스를 표시해둔다.
            double slope = ((dots[i][1] - dots[0][1]) / (double) (dots[i][0] - dots[0][0]));

            double comparison = 0;
            int compEndIndex1 = 1;
            int compEndIndex2 = 1;

            while (isUsedIndex[compEndIndex1]) { // isUsedIndex == false 나올 때까지 인덱스 + 1하기 // while 본문 비우고 compEndIndex1++ 형태로 둬도 되지만, IDE에서 노란 경고 띄워서 아래로 내려줬음
                compEndIndex1++;
            }
            isUsedIndex[compEndIndex1] = true;
            while (isUsedIndex[compEndIndex2]) {
                compEndIndex2++;
            }

            comparison = ((dots[compEndIndex1][1] - dots[compEndIndex2][1]) / (double) (dots[compEndIndex1][0] - dots[compEndIndex2][0]));

            if (slope == comparison) {
                answer = 1;
                break;
            }
        }
        return answer;
    }
}
