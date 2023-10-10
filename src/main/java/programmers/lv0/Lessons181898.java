package programmers.lv0;

import java.util.stream.IntStream;

// lv0 가까운 1 찾기
public class Lessons181898 {

    // 풀이 1
    public int solution1(int[] arr, int idx) {
        int answer = -1;
        for (int i = idx; i < arr.length; i++) { // 문제는 '크면서'라고 되어 있는데, 크거나 같으면서 조건임
            if (arr[i] == 1) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    // 풀이 2 - stream
    public int solution2(int[] arr, int idx) {
        return IntStream.range(0, arr.length)
                .filter(i -> i >= idx) // 문제는 '크면서'라고 되어 있는데, 크거나 같으면서 조건임
                .filter(i -> arr[i] == 1)
                .findFirst().orElse(-1);
    }

    // 풀이 2-1(다른 풀이 참고) - stream - filter 두 번 걸 필요 없었음...
    public int solution2_1(int[] arr, int idx) {
        return IntStream.range(idx, arr.length).filter(i -> arr[i] == 1).findFirst().orElse(-1);
    }
}
