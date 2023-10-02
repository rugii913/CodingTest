package programmers.lv1;

import java.util.stream.IntStream;

// lv1 나머지가 1이 되는 수 찾기
public class Lessons87839 {

    // 풀이 1 // 0.01ms ~ 4.90ms
    public int solution1(int n) {
        int answer = 0;

        for (int x = 1; x < n; x++) {
            if (n % x == 1) {
                answer = x;
                break;
            }
        }

        return answer;
    }

    // 풀이 1-1(다른 풀이 참고) // 0.01ms ~ 3.67ms
    public int solution1_1(int n) {
        int answer = 1;

        /*
        // (IDE 경고) 루프 내 조건부 break - 검사 정보: 루프의 시작 또는 끝 부분에 조건부 줄 바꿈을 보고하고 코드를 줄이기 위해 루프 조건을 추가할 것을 제안합니다.
        while (true) {
            if (n % answer == 1) {
                break;
            }
            answer++;
        }
         */
        while (n % answer != 1) {
            answer++;
        }

        return answer;
    }

    // 풀이 2(다른 풀이 참고) - stream // 1.37ms ~ 13.48ms
    public int solution2(int n) {
        return IntStream.range(2, n).filter(i -> n % i == 1).findFirst().orElse(0);
    }
}
