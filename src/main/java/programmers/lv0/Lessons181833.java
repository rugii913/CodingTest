package programmers.lv0;

// lv0 특별한 이차원 배열 1
public class Lessons181833 {

    // 풀이 1
    public int[][] solution1(int n) {
        int[][] answer = new int[n][n];
        for (int i = 0 ; i < n; i++) {
            answer[i][i] = 1;
        }
        return answer;
    }
}
