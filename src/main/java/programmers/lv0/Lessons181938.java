package programmers.lv0;

// lv0 두 수의 연산값 비교하기 - lv0 더 크게 합치기와 거의 유사한 문제
public class Lessons181938 {
    // (1) 풀이 1 string.contat(~) 사용 vs. 풀이 2 int만 사용
    // (2) Integer.toString(~) 사용 vs. String.valueOf(~) 사용
    // (3) 메서드 분리 vs. 메서드 분리 x
    // 세 비교에서 모두 크게 신경쓸만한 속도 차이는 보이지 않음
    // 가장 빠른 것은 log를 사용한 풀이 3(다른 풀이 참고)였음 - 하지만 수행할 때마다 조금씩 달라지므로 크게 신경쓸만한 부분은 아닌 듯함

    // 풀이 1 - string.contat(~) 사용 // 0.04ms ~ 0.12 ms
    public int solution1(int a, int b) {
        return Math.max(circlePlus1(a, b), 2 * a * b);
    }

    private int circlePlus1(int a, int b) {
        String answerString = String.valueOf(a).concat(String.valueOf(b));
        // String answerString = Integer.toString(a).concat(Integer.toString(b)); // 0.04ms ~ 0.13 ms // 별 차이 없음
        return Integer.parseInt(answerString);
    }

    // 풀이 1-1 - 메서드 분리 x // 0.04ms ~ 0.15 ms
    public int solution1_1(int a, int b) {
        String answerString = String.valueOf(a).concat(String.valueOf(b));

        return Math.max(Integer.parseInt(answerString), 2 * a * b);
    }

    // 풀이 2 - String 변환 없이 int만으로 // 0.03ms ~ 0.10 ms
    public int solution2(int a, int b) {
        int digitsOfB = 0;
        int tmpB = b;

        while (tmpB > 0) {
            digitsOfB++;
            tmpB /= 10;
        }

        int aCirclePlusB = a * (int) Math.pow(10, digitsOfB) + b;

        return Math.max(aCirclePlusB, 2 * a * b);
    }

    // 풀이 2-1 - 메서드 분리했을 때 속도 차이 비교 // 0.03ms ~ 0.15ms
    public int solution2_1(int a, int b) {
        return Math.max(circlePlus2(a, b), 2 * a * b);
    }

    private int circlePlus2(int a, int b) {
        int digitsOfB = 0;
        int tmpB = b;

        while (tmpB > 0) {
            digitsOfB++;
            tmpB /= 10;
        }

        return a * (int) Math.pow(10, digitsOfB) + b;
    }

    // 풀이 3(다른 풀이 참고) // 0.03ms ~ 0.06ms
    public int solution3(int a, int b) {
        return Math.max(
                a * (int)Math.pow(10, (int)Math.log10(b) + 1) + b,
                2 * a * b
        );
    }
}
