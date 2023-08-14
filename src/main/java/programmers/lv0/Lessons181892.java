package programmers.lv0;

import java.util.Arrays;

// lv0 n 번째 원소부터
public class Lessons181892 {
    /*
    * - Arrays.copyOfRange(~) 메서드
    * - System.arraycopy(~) 메서드
    * */

    // 풀이 1
    public int[] solution1(int[] num_list, int n) {
        int[] answer = new int[num_list.length - n + 1];
        for (int i = 0; i < num_list.length; i++) {
            if (i >= n - 1) {
                answer[i - (n - 1)] = num_list[i];
            }
        }
        return answer;
    }

    // 풀이 2
    public int[] solution2(int[] num_list, int n) {
        int[] answer = new int[num_list.length - n + 1];
        for (int i = 0; i < num_list.length - n + 1; i++) {
            answer[i] = num_list[i + n - 1];
        }
        return answer;
    }

    // 풀이 x - stream 사용 시도 실패
    public int[] solutionx(int[] num_list, int n) {
        // return Arrays.asList(num_list).subList(n - 1, num_list.length -1).stream().mapToInt(...??).toArray();
        return null;
    }

    // 풀이 3(다른 풀이 참고) - Arrays.copyOfRange(~) 메서드
    public int[] solution3(int[] num_list, int n) {
        return Arrays.copyOfRange(num_list, n - 1, num_list.length);
    }

    // 풀이 4(다른 풀이 참고) - System.arraycopy(~) 메서드
    public int[] solution4(int[] num_list, int n) {
        int[] answer = new int [num_list.length - n + 1];
        System.arraycopy(num_list, n - 1, answer, 0, answer.length);
        return answer;
    }
}
