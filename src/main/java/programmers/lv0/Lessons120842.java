package programmers.lv0;

// lv0 2차원으로 만들기
public class Lessons120842 {
    /*
    * 2차원 배열, 중첩 반복 줄이기
    * */

    // 풀이 1
    public int[][] solution1(int[] num_list, int n) {
        int[][] answer = new int[num_list.length / n][n];
        for (int i = 0; i < num_list.length / n; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = num_list[i * n + j];
            }
        }
        return answer;
    }

    // 풀이 2(다른 풀이 참고) - 중첩 반복 줄이기, 인덱스를 어디에 둘 것인지, 나머지 연산자 % 활용
    // 종료 조건은 i < num_list.length / n에서 i < num_list.length로 바뀌고 i의 사용방법 바뀜
    public int[][] solution2(int[] num_list, int n) {
        int[][] answer = new int[num_list.length / n][n];
        for (int i = 0; i < num_list.length; i++) {
            answer[i / n][i % n] = num_list[i];
        }
        return answer;
    }
}
