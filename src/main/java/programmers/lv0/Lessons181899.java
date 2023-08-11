package programmers.lv0;

import java.util.stream.IntStream;

// lv0 카운트 다운
public class Lessons181899 {

    // 풀이 1
    public int[] solution1(int start, int end) {
        int length = start - end + 1;
        int[] answer = new int[length];

        for (int i = 0; i < length; i++) {
            answer[i] = start - i;
        }

        return answer;
    }

    // 풀이 2(다른 풀이 참고) - 위보다는 아래가 나을 듯
    public int[] solution2(int start, int end) {
        // return IntStream.rangeClosed(end, start).boxed().collect(Collectors.toList()).stream().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        return IntStream.rangeClosed(-start, -end).map(i -> -i).toArray();
    }
}
