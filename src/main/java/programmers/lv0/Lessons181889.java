package programmers.lv0;

import java.util.Arrays;
import java.util.stream.IntStream;

// lv0 n 번째 원소까지
public class Lessons181889 {
    /*
    * - System.arraycopy(num_list,0, answer, 0, n); 혹은 Arrays.copyOfRange(num_list, 1, n-1);
    * - stream.limit(long maxSize) 메서드
    * */

    // 풀이 1
    public int[] solution1(int[] num_list, int n) {
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = num_list[i];
        }
        return answer;
    }

    // 풀이 2 System.arraycopy(num_list,0, answer, 0, n); 혹은 Arrays.copyOfRange(num_list, 1, n-1);
    public int[] solution2(int[] num_list, int n) {
        /*
        // 이런 방법도 있다. 코드는 좀 더 길지만, import java.util.Arrays;가 필요 없음
        int[] answer = new int[n];
        System.arraycopy(num_list,0, answer, 0, n);
        return answer;
        */
        return Arrays.copyOfRange(num_list, 0, n);
    }

    // 풀이 3 - stream.limit(long maxSize) 메서드 사용
    public int[] solution3(int[] num_list, int n) {
        // return Arrays.stream(num_list).limit(n).toArray();
        return IntStream.of(num_list).limit(n).toArray();
    }
}
