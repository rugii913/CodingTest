package programmers.lv0;

import java.util.Arrays;
import java.util.Comparator;

// lv0 뒤에서 5등까지
public class Lessons181853 {

    // 풀이 1 - 간단한 풀이 // 0.31ms ~ 0.60ms
    public int[] solution1(int[] num_list) {
        Arrays.sort(num_list);

        int[] answer = new int[5];
        System.arraycopy(num_list, 0, answer, 0, answer.length);

        return answer;
    }

    // 풀이 1-1(다른 풀이 참고) - Arrays.copyOfRange(~) 사용 // 0.34ms ~ 0.51ms
    public int[] solution1_1(int[] num_list) {
        Arrays.sort(num_list);

        return Arrays.copyOfRange(num_list, 0, 5);
    }

    // 풀이 2 - 효율적이라고 생각한 풀이 // 0.02ms ~ 0.04ms
    // 일단 length 5 배열 만든 뒤 int 최댓값 채움 - num_list 각 원소 별로 삽입 정렬과 유사한 방식으로 정렬
    public int[] solution2(int[] num_list) {
        int[] result = new int[5];
        Arrays.fill(result, Integer.MAX_VALUE);

        for (int number : num_list) {
            if (number < result[4]) {
                result[4] = number;
                sortSimilarInsertionSort(result);
            }
        }

        return result;
    }

    private void sortSimilarInsertionSort(int[] result) {
        for (int j = 4; j >= 1; j--) {
            if (result[j] < result[j - 1]) {
                int tmp = result[j - 1];
                result[j - 1] = result[j];
                result[j] = tmp;
            } else {
                break;
            }
        }
    }

    // 풀이 3 - stream (cf. 역순으로 앞에서 5등까지 뽑아내려면? - IntStream에는 역순 sorted()가 없음) // 2.31ms ~ 9.55ms - N이 작아서 더 비효율적
    public int[] solution3(int[] num_list) {
        return Arrays.stream(num_list).sorted().limit(5).toArray();
    }
}
