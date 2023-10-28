package programmers.lv0;

import java.util.Comparator;
import java.util.NavigableMap;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.stream.IntStream;

// lv0 전국 대회 선발 고사
public class Lessons181851 {
    /*
    * 처음 생각한 아이디어 (1) rank를 정렬하면서 attendance도 함께 정렬하든지 (x 사용 불가) (2) 높은 등수의 인덱스를 얻든지
    * */

    // 풀이 1 - (2) 방식
    public int solution1(int[] rank, boolean[] attendance) {
        int checkTargetRank = 1; // 1부터 rank.length + 1까지 체크

        int[] studentIndices = new int[3];
        int studentIndicesFilled = 0;

        while (studentIndicesFilled < 3) {
            for (int i = 0; i < rank.length; i++) {
                if (rank[i] == checkTargetRank && attendance[i]) {
                    studentIndices[studentIndicesFilled++] = i;
                }
            }

            checkTargetRank++;
        }

        return studentIndices[0] * 10_000 + studentIndices[1] * 100 + studentIndices[2];
    }

    // 풀이 x - (1) 방식 - 정렬하면 학생의 번호가 흐트러지므로 사용할 수 없음...
    public int solutionx(int[] rank, boolean[] attendance) {
        for (int i = 1; i < rank.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (rank[j] < rank[j - 1]) {
                    int tmpInt = rank[j];
                    rank[j] = rank[j - 1];
                    rank[j - 1] = tmpInt;

                    boolean tmpBoolean = attendance[j];
                    attendance[j] = attendance[j - 1];
                    attendance[j - 1] = tmpBoolean;
                } else {
                    break;
                }
            }
        }

        int[] answerRanks = new int[3];
        int ranksFilled = 0;

        for (int i = 0; i < attendance.length; i++) {
            if (attendance[i]) {
                answerRanks[ranksFilled++] = rank[i];
            }
            if (ranksFilled == 3) {
                break;
            }
        }

        return answerRanks[0] * 10_000 + answerRanks[1] * 100 + answerRanks[2];
    }

    // 풀이 2 - TreeMap 사용
    public int solution2(int[] rank, boolean[] attendance) {
        NavigableMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < rank.length; i++) {
            if (attendance[i]) {
                map.put(rank[i], i);
            }
        }

        int answer = 0;
        for (int i = 1; i <= 3; i++) {
            answer *= 100;
            answer += map.pollFirstEntry().getValue();
        }

        return answer;
    }

    // 풀이 3 - 다른 풀이 첫번째 풀이가 stream이길래 정확히 내용은 안 보고 stream 생각해봄
    public int solution3(int[] rank, boolean[] attendance) {
        return IntStream.range(0, rank.length)
                .filter(i -> attendance[i])
                .boxed().sorted(Comparator.comparingInt(i -> rank[i])).mapToInt(i -> i)
                .limit(3).reduce(0, (prev, current) -> prev * 100 + current);
    }

    // 풀이 3-1(다른 풀이 참고) - 확인해보니 내용 거의 같음
    public int solution3_1(int[] rank, boolean[] attendance) {
        return IntStream.range(0, rank.length)
                .filter(i -> attendance[i])
                .boxed()
                .sorted(Comparator.comparing(i -> rank[i]))
                .limit(3L)
                .reduce((current, next) -> current * 100 + next)
                .get();
    }

    // 풀이 4(다른 풀이 참고 수정) - PriorityQueue 사용
    public int solution4(int[] rank, boolean[] attendance) {
        // PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> rank[a] - rank[b]);
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> rank[a]));
        for (int i = 0; i < attendance.length; i++) {
            if (attendance[i])
                queue.add(i);
        }

        return queue.poll() * 10000 + queue.poll() * 100 + queue.poll();
    }
}
