package programmers.lv0;

import java.util.stream.IntStream;

// lv0 특별한 이차원 배열 2
public class Lessons181831 {

    // 풀이 1
    public int solution1(int[][] arr) {
        int n = arr.length;
        int answer = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != arr[j][i]) {
                    answer = 0;
                    break;
                }
            }
        }

        return answer;
    }

    // 풀이 2(다른 풀이 참고) - stream
    public int solution2(int[][] arr) {
        return IntStream.range(0, arr.length).allMatch(i -> IntStream.range(i + 1, arr.length).allMatch(j -> arr[i][j] == arr[j][i])) ? 1 : 0;
        // 두 번째 IntStream에서 range를 0 ~ arr.length가 아니라 i + 1, arr.length로 줌으로써 중복을 피할 수 있다.
        // (range를 0 ~ arr.length로 주게 되면 뒤집었을 때 같아지는 pair에 대해서 중복해서 검사하게 됨)
        // 물론 i가 아니라 i + 1인 것도 trivial, arr[i][i]를 뒤집어도 arr[i][i]이므로 당연히 같으므로
    }
}
