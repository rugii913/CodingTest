package programmers.lv0;

import java.util.stream.IntStream;

// lv0 순서 바꾸기
public class Lessons181891 {

    // 풀이 1
    public int[] solution1(int[] num_list, int n) {
        int[] answer = new int[num_list.length];

        // num_list index 0 ~ n-1까지(n개) || num_list index n ~ num_list.length - 1까지(num_list.length - n)개
        System.arraycopy(num_list, 0, answer, num_list.length - n, n);
        System.arraycopy(num_list, n, answer, 0, num_list.length - n);

        return answer;
    }

    // 풀이 1-1(다른 풀이 참고)
    public int[] solution1_1(int[] num_list, int n) {
        int idx = 0;
        int[] answer = new int[num_list.length];
        for (int i = n; i < num_list.length; i++)
            answer[idx++] = num_list[i];
        for (int i = 0; i < n; i++)
            answer[idx++] = num_list[i];
        return answer;
    }

    // 풀이 1-2(풀이 1-1 수정) - 수정하긴 했는데 풀이 1-1이 더 직관적이다.
    public int[] solution1_2(int[] num_list, int n) {
        int[] answer = new int[num_list.length];
        for (int i = n; i < num_list.length; i++) {
            answer[i - n] = num_list[i];
        }
        for (int i = 0; i < n; i++) {
            answer[i + num_list.length - n] = num_list[i];
        }
        return answer;
    }

    // 풀이 1-3(다른 풀이 참고)
    public int[] solution1_3(int[] num_list, int n) {
        int[] answer = new int[num_list.length];

        for (int i = 0; i < num_list.length; i++) {
            if (n == num_list.length) { // n++해주다가 n == num_list.length에 도달하면 0으로 초기화 시켜줌
                n = 0;
            }
            answer[i] = num_list[n++];
        }
        return answer;
    }

    // 풀이 1-4(다른 풀이 수정) - 풀이 1-3 + 풀이 2 아이디어 섞기
    public int[] solution1_4(int[] num_list, int n) {
        int[] answer = new int[num_list.length];

        for (int i = 0; i < num_list.length; i++) {
            answer[i] = num_list[n++ % num_list.length];
        }
        return answer;
    }

    // 풀이 2(다른 풀이 참고) - stream - 좋은 아이디어 - 나머지 사용
    public int[] solution2(int[] num_list, int n) {
        return IntStream.range(0, num_list.length).map(i -> num_list[(i + n) % num_list.length]).toArray();
    }
}
