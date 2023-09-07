package programmers.lv0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// lv0 겹치는 선분의 길이 - 성공 코드 및 다른 사람 풀이들
public class Lessons120876 {
    /*
     * 나의 풀이를 제외하면 대체로, 시작 시점은 닫고, 끝 지점은 열어서
     * 선끼리 맞닿는 경우를 회피하고 있다.
     * - 다른 풀이들이 그렇게 좋아보이진 않는다.
     * - 그래도 Map을 사용하는 방법은 괜찮아 보인다.
     */

    // 풀이 1 - 점으로 따지기 수정 0.5 사용 - 통과 -> Lessons120876Fail에서 클래스 옮김
    public int solution1(int[][] lines) {
        int max = Math.max(Math.max(lines[0][1], lines[1][1]), lines[2][1]);
        int min = Math.min(Math.min(lines[0][0], lines[1][0]), lines[2][0]);

        int[] linesOverDots = new int[max - min];
        for (int i = 0; i < max - min; i++) { // i = 0일 때 (min + 0.5), i = max - min - 1일 때가 (max - 0.5)와 대응됨
            for (int[] line : lines) {
                if (min + i + 0.5 > line[0] && min + i + 0.5 < line[1]) {
                    linesOverDots[i]++;
                }
            }
        }

        int countDot = 0;
        for (int linesOverDot : linesOverDots) {
            if (linesOverDot >= 2) {
                countDot++;
            }
        }

        return countDot;
    }

    // 풀이 2(다른 풀이 참고) - Map의 merge(~), Integer.sum(int, int) 사용, 맞닿는 경우 회피 / 0.85 ~ 1.99 ms
    // *** default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
    // 원래 있던 값과 새로 들어갈 값의 관계를 설정해서 새로 매핑
    // - 왜 from은 포함하고 to는 제외하는가?
    //  -> 이렇게 하면 겹치는 게 아닌 두 선이 맞닿고 있는 경우가 자연스럽게 처리됨(약간 꼼수라고 볼 수 있음
    public int solution2(int[][] lines) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] line : lines) {
            int from = line[0];
            int to = line[1];
            for (int i = from; i < to; i++) {
                map.merge(i, 1, Integer::sum);
                // map.merge(i, 1, (prevCount, one) -> prevCount + one);
                // 위처럼 해도 문제 없는데 IDE에서 Integer::sum으로 메서드 참조하라고 추천해줌
            }
        }

        return (int) map.values().stream().filter(i -> i > 1).count();
    }

    // 풀이 2-1(다른 풀이 참고) - Map merge(~) 대신 getOrDefault로 간단한 덧셈, 맞닿는 경우 회피 / 0.05 ~ 0.49 ms
    public int solution2_1(int[][] lines) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] line : lines) {
            int from = line[0];
            int to = line[1];

            for (int j = from; j < to; j++) {
                map.put(j, map.getOrDefault(j, 0) + 1);
                // 원래 key j가 있었으면 j의 value, 없으면 0 반환 -> 반환값에 1 더한 뒤 다시 put
            }
        }

        int answer = 0;
        for (int value : map.values()) {
            if (value >= 2) {
                answer++;
            }
        }
        /*
        // 원래 풀이는 좀 이상
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 2) {
                answer++;
            }
        }
        */
        return answer;
    }

    // 풀이 3(다른 풀이 참고) - 컬렉션 미사용, 맞닿는 경우 회피 / 0.02 ~ 0.05 ms
    public int solution3(int[][] lines) {
        int answer = 0;
        for (int i = -100; i <= 100; i++) { // 문제 조건 사용 // cf. 원래 풀이는 i < 100으로 되어있는데 테스트 케이스 문제인 듯
            int line = 0;
            if (lines[0][0] <= i && lines[0][1] > i) line++;
            if (lines[1][0] <= i && lines[1][1] > i) line++;
            if (lines[2][0] <= i && lines[2][1] > i) line++;
            // 여기서도 line의 시작점은 포함되게 하고, line의 끝은 포함되지 않게 처리해서
            // 맞닿는 경우의 문제를 회피했다.

            if (line > 1) answer++;
        }
        return answer;
    }

    // 풀이 4(다른 풀이 참고) - ArrayList 사용, 맞닿는 경우 회피 / 0.03 ~ 1.58 ms
    // List<Integer> overlap에 해당 Integer가 없으면 추가, 있으면 제거하면서 count++
    // 세 번 겹치는 Integer인 경우 그 Integer로 인해 count는 올라갔지만, 마지막 overLap에는 그 숫자가 남아있음
    // -> 선이 3개보다 많은 경우로 일반화 불가능
    // -> 풀이는 되는데 그렇게 매력적인 방법으로 보이진 않음
    // 이렇게 푸느니 풀이 3처럼 푸는 게 훨씬 나아보인다.
    public int solution4(int[][] lines) {
        List<Integer> overLap = new ArrayList<>();
        int count = 0;

        for (int i = 0; i <= 2; i++) {
            int from = lines[i][0];
            int to = lines[i][1];
            for (int j = from; j < to; j++) {
                if (overLap.contains(j)) {
                    overLap.remove(Integer.valueOf(j));
                    count++;
                } else overLap.add(j);
            }
        }
        return count;
    }
}
