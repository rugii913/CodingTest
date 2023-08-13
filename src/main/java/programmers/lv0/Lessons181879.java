package programmers.lv0;

import java.util.stream.IntStream;

// lv0 길이에 따른 연산
public class Lessons181879 {

    // 풀이 1
    public int solution1(int[] num_list) {
        int answer = 0;
        if (num_list.length >= 11) {
            for (int num : num_list) {
                answer += num;
            }
        } else {
            answer = 1;
            for (int num : num_list) {
                answer *= num;
            }
        }
        return answer;
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(int[] num_list) {
        int numListLength = num_list.length;
        int answer = numListLength < 11 ? 1 : 0;

        if (numListLength < 11) {
            for (int num : num_list) {
                answer *= num;
            }
        } else {
            for (int num : num_list) {
                answer += num;
            }
        }

        return answer;
    }

    // 풀이 3(다른 풀이 참고)
    public int solution3(int[] num_list) {
        IntStream stream = IntStream.of(num_list);
        return num_list.length < 11 ? stream.reduce(1, (a, b) -> a * b) : stream.sum();
    }
}
