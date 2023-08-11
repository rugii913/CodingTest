package programmers.lv0;

import java.util.stream.IntStream;

// lv0 정수 찾기
public class Lessons181840 {

    // 풀이 1 - 다른 풀이 참고하여 수정, 크게 다르진 않을 듯
    public int solution1(int[] num_list, int n) {
        int answer = 0;

        /*
        for (int i : num_list) {
            if (i == n) {
                answer = 1;
                break;
            }
        }
        */
        for (int i : num_list) {
            if (i == n) {
                return 1;
            }
        }

        return answer;
    }

    // 풀이 2(다른 풀이 참고) - IntStream. ... .anyMatch(~) 사용
    public int solution2(int[] num_list, int n) {
        return IntStream.of(num_list).anyMatch(num -> num == n) ? 1 : 0;
    }
}
