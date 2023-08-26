package programmers.lv0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// lv0 등수 매기기
public class Lessons120882 {
    /*
    * 풀이 4가 풀이 1보다 효율적 - 보조 변수 사용해서 정렬하는 수고가 덜어짐
    * 조건에 따라 사용 불가능한 경우도 있을 것 같지만 보조변수를 적극 활용해볼 것
    * */

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

    // 풀이 2(다른 풀이 참고) - ArrayList 사용
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

    // 풀이 3(다른 풀이 참고) - stream
    public int[] solution3(int[][] score) {
        return Arrays.stream(score).map(ints -> Arrays.stream(ints).average().orElse(0))
                .mapToInt(d -> Arrays.stream(score).map(ints -> Arrays.stream(ints).average().orElse(0)).sorted(Comparator.reverseOrder()).collect(Collectors.toList()).indexOf(d) + 1)
                .toArray();
    }

    // 풀이 4(다른 풀이 참고) - count라는 보조 변수를 사용함으로써 정렬하는 수고를 줄임
    // 같은 점수인 경우 같은 등수가 되는 조건이 있으므로 이런 형태가 가능한 듯
    public int[] solution4(int[][] score) {
        // 풀이 원본에서 변수명 변경 map -> avg
        double[] avg = new double[score.length];
        for (int i = 0; i < score.length; i++) {
            avg[i] = (score[i][0] + score[i][1]) / 2.0;
        }

        int[] result = new int[score.length];
        for (int i = 0; i < score.length; i++) {
            int count = 0; // 보조변수
            for (int j = 0; j < score.length; j++) {
                if (avg[i] < avg[j]) {
                    count++;
                }
            }
            result[i] = count + 1;
        }

        return result;
    }
}
