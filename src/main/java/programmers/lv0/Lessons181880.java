package programmers.lv0;

import java.util.Arrays;

// lv0 1로 만들기
// Lessons181881 lv0 조건에 맞게 수열 변환하기 2 먼저 풀고 그 다음에 풀어서 헷갈렸는데, 서로 다른 문제임
public class Lessons181880 {

    // 풀이 x - Lessons181881 lv0 조건에 맞게 수열 변환하기 2 먼저 풀고 그 다음에 풀어서 헷갈렸는데, 서로 다른 문제임
    public int solutionx(int[] num_list) {
        boolean allIs1 = false;
        int repeatCount = 0;

        while (!allIs1) {
            allIs1 = true;
            repeatCount++;

            for (int i = 0; i < num_list.length; i++) {
                if (num_list[i] == 1) {
                    // 아무 연산도 하지 않고 넘어감
                } else { // int 연산이므로 홀수든 짝수든 같다.
                    num_list[i] /= 2;
                }

                if (num_list[i] != 1) {
                    allIs1 = false;
                }
            }
        }

        return repeatCount;
    }

    // 풀이 1
    public int solution1(int[] num_list) {
        int workCount = 0;

        for (int i = 0; i < num_list.length; i++) {
            while (num_list[i] != 1) {
                // int 연산이므로 홀수든 짝수든 같다.
                num_list[i] /= 2;
                workCount++;
            }
        }

        return workCount;
    }
    
    // 풀이 2(다른 풀이 참고)
    // - binary string의 길이가 2라면 1번 나누면 1이 된다. - 10, 11
    //                        3이라면 2번 나누면 1이 된다. - 111, 110, 101, 100 ...
    //   => n이면 n-1번 나누면 1이 된다는 아이디어
    public int solution2(int[] num_list) {
        return Arrays.stream(num_list).map(i -> Integer.toBinaryString(i).length() - 1).sum();
    }
}
