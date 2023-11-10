package programmers.lv1;

import java.util.Arrays;

// lv1 K번째수
public class Lessons42748 {
    // 0이 아니라 1부터 시작해서 헷갈리게 만드는 문제

    // 풀이 1 - Arrays.copyOfRange(~)의 to도 exclusive임에 유의
    public int[] solution1(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < answer.length; i++) {
            int[] targetRange = Arrays.copyOfRange(array, commands[i][0] -1, commands[i][1]);
            Arrays.sort(targetRange);
            answer[i] = targetRange[commands[i][2] - 1];
        }
        return answer;
    }

    // 풀이 1-1(다른 풀이 참고) - 변수 사용해서 약간 더 알아보기 편하게 만들었을 뿐
    public int[] solution2(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        int answerIndex = 0;
        for (int[] command : commands) {
            int i = command[0];
            int j = command[1];
            int k = command[2];

            int[] targetRange = Arrays.copyOfRange(array, i - 1, j);
            Arrays.sort(targetRange);
            answer[answerIndex] = targetRange[k - 1];
            answerIndex++;
        }

        return answer;
    }
}
