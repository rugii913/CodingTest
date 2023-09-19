package programmers.lv0;

// lv0 다음에 올 숫자
public class Lessons120924 {

    // 풀이 1 - 등차수열 - 등비수열 판별 분기
    public int solution1(int[] common) {
        int answer;
        int length = common.length;
        int firstDifference = common[1] - common[0];
        int lastDifference = common[length - 1] - common[length - 2];

        if (firstDifference == lastDifference) { // 등차수열인 경우
            answer = common[length - 1] + firstDifference;
        } else { // 등비수열인 경우
            // 문제 조건 상 등비수열인 경우에도 공비는 0이 아닌 정수 - double 사용할 필요 없음
            answer = common[length - 1] * (common[1] / common[0]);
        }

        return answer;
    }
}
