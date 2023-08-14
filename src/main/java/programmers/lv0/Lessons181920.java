package programmers.lv0;

import java.util.stream.IntStream;

// lv0 카운트 업
public class Lessons181920 {

    // 풀이 1
    public int[] solution1(int start, int end) {
        int[] answer = new int[end - start + 1];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = start++;
        }
        return answer;
    }

    // 풀이 2(다른 풀이 참고) - IntStream
    public int[] solution2(int start, int end) {
        return IntStream.rangeClosed(start, end).toArray();
    }
}
