package programmers.lv0;

// lv0 수열과 구간 쿼리 1 - 기초적인 배열 탐색 문제, 딱히 더 나은 풀이가 없는 듯하다.
public class Lessons181883 {

    // 풀이 1
    public int[] solution1(int[] arr, int[][] queries) {
        for (int[] query : queries) {
            for (int i = query[0]; i <= query[1]; i++) {
                arr[i]++;
            }
        }
        return arr;
    }
}
