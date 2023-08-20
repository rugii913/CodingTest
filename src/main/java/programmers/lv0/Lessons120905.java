package programmers.lv0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// lv0 n의 배수 고르기
public class Lessons120905 {

    // 풀이 1
    public int[] solution1(int n, int[] numlist) {
        int countNonMultiple = 0;
        for (int i = 0; i < numlist.length; i++) {
            if (numlist[i] % n != 0) {
                numlist[i] = -1;
                countNonMultiple++;
            }
        }

        int answerIndex = 0; // 어차피 개수가 확실하다면 중첩 for를 돌리지 말고 이렇게 처리하자
        int[] answer = new int[numlist.length - countNonMultiple];
        for (int i = 0; i < numlist.length; i++) {
            if (numlist[i] > 0) {
                answer[answerIndex++] = numlist[i];
            }
        }
        return answer;
    }

    // 풀이 1-1(다른 풀이 참고) - 어차피 길어질 거라면 배수가 아닌 것을 세는 게 아니라 배수를 바로 세면 됨
    public int[] solution1_1(int n, int[] numlist) {
        int count = 0;
        for (int i : numlist) {
            if (i % n == 0) {
                count++;
            }
        }

        int[] answer = new int[count];
        int idx = 0;
        for (int i : numlist) {
            if (i % n == 0) {
                answer[idx] = i;
                idx++;
            }
        }

        return answer;
    }

    // 풀이 2 - 이 경우엔 stream이 훨씬 간결
    public int[] solution2(int n, int[] numlist) {
        return Arrays.stream(numlist).filter(num -> num % n == 0).toArray();
    }

    // 풀이 2-1 - 맨 마지막에만 stream을 사용한 경우
    public int[] solution2_1(int n, int[] numlist) {
        List<Integer> answer = new ArrayList<>();
        for(int num : numlist){
            if(num % n == 0){
                answer.add(num);
            }
        }
        return answer.stream().mapToInt(x -> x).toArray();
    }
}
