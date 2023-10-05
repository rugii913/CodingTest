package programmers.lv0;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// lv0 수열과 구간 쿼리 3
public class Lessons181924 {

    // 풀이 1 - enhanced for 가독성
    public int[] solution1(int[] arr, int[][] queries) {
        int tmp;

        /* // 0.03ms ~ 0.10ms
        for (int i = 0; i < queries.length; i++) {
            tmp = arr[queries[i][0]];
            arr[queries[i][0]] = arr[queries[i][1]];
            arr[queries[i][1]] = tmp;
        }
         */
        // enhanced for 0.03ms ~ 0.10ms
        for (int[] query : queries) {
            tmp = arr[query[0]];
            arr[query[0]] = arr[query[1]];
            arr[query[1]] = tmp;
        }

        return arr;
    }

    // 풀이 2(다른 풀이 참고) - stream // 4.83ms ~ 10.44ms
    public int[] solution2(int[] arr, int[][] queries) {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        for (int[] query : queries) {
            Collections.swap(list, query[0], query[1]);
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
