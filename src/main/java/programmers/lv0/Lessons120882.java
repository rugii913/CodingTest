package programmers.lv0;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// lv0 등수 매기기
public class Lessons120882 {

    // 풀이 1(테스트 3,6 관련 질문하기 참고) - 평균이 int가 아니라 double인 경우
    public int[] solution1(int[][] score) {
        int headcount = score.length;

        double[] avg = new double[headcount];
        for (int i = 0; i < headcount; i++) {
            avg[i] = (score[i][0] + score[i][1]) / 2.0;
        }

        double[] avgSorted = new double[headcount];
        System.arraycopy(avg, 0, avgSorted, 0, headcount);
        for (int i = 1; i < headcount; i++) {
            for (int j = i - 1; j >= 0; j--) { // 내림차순 정렬
                if (avgSorted[j] < avgSorted[j + 1]) {
                    double tmp = avgSorted[j];
                    avgSorted[j] = avgSorted[j + 1];
                    avgSorted[j + 1] = tmp;
                }
            }
        }

        int[] rank = new int[headcount];
        for (int i = 0; i < headcount; i++) {
            for (int j = 0; j < headcount; j++) {
                if (avg[i] == avgSorted[j]) { // 인덱스 [i] 해당 평균 점수를 avgSorted의 몇 번째 인덱스 [j]에서 발견했는지 확인
                    rank[i] = j + 1;
                    break; // 발견했을 때 j에 대한 for문(avgSorted의 인덱스 = 등수 찾기) break 없으면 동점자들 순위가 밀림
                }
            }
        }
        return rank;
    }

    // 풀이 2(테스트 3,6 관련 질문하기 참고) - 평균이 int가 아니라 double인 경우
    public int[] solution2(int[][] score) {
        List<Integer> scoreList = new ArrayList<>();
        for(int[] t : score){
            scoreList.add(t[0] + t[1]); // 평균 내진 않고, 총 더한 값으로 비교해서 double 사용 안 함
        }
        scoreList.sort(Comparator.reverseOrder()); 
        // List의 sort(comparator) 사용
        // Comparator.reverseOrder()는 Collections.reversOrder() 호출
        // ====> (Comparator<T>) ReverseComparator.REVERSE_ORDER 반환

        int[] answer = new int[score.length];
        for (int i = 0; i < score.length; i++) {
            answer[i] = scoreList.indexOf(score[i][0] + score[i][1]) + 1;
            // List의 indexOf(object) 메서드를 사용할 수 있으므로 풀이 1처럼 표면적으로 중첩 for문은 안 쓰게 됨
            // 물론 ArrayList 구현 상 indexOf(object) -> indexOfRange(Object o, int start, int end)는 for문을 사용함 
        }
        return answer;
    }
}
