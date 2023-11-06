package programmers.lv1;

import java.util.Arrays;

// lv1 예산
public class Lessons12982 {

    // 풀이 1 - 구간합(prefix sum) 사용
    public int solution1(int[] d, int budget) {
        Arrays.sort(d);

        int[] prefixSum = new int[d.length + 1];
        // prefixSum[0] == 0
        // prefixSum[]의 인덱스 i = prefixSum[i]에 저장된 값이 예산이라고 할 때, 지원 가능한 부서 개수
        int maxDivisionCount = 0;
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + d[i - 1];

            if (prefixSum[i] > budget) {
                maxDivisionCount = i - 1;
                break;
            } else if (i == prefixSum.length - 1) { // i 끝까지 for가 break 되지 않은 경우
                maxDivisionCount = i;
            }
        }

        return maxDivisionCount;
    }

    // 풀이 2(다른 풀이 참고) - 뺄셈 사용, 더 깔끔하고 알아보기 쉽다.
    public int solution2(int[] d, int budget) {
        int answer = 0;

        Arrays.sort(d);

        for (int intInD : d) {
            budget -= intInD;

            if (budget < 0) {
                break;
            }

            answer++;
        }

        return answer;
    }
}
