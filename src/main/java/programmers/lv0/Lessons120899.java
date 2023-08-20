package programmers.lv0;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.IntStream;

// lv0 가장 큰 수 찾기
public class Lessons120899 {

    // 풀이 1
    public int[] solution1(int[] array) {
        int greatest = Integer.MIN_VALUE;
        int indexOfGreatest =  -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > greatest) {
                greatest = array[i];
                indexOfGreatest = i;
            }
        }

        int[] answer = {greatest, indexOfGreatest};
        return answer;
    }

    // 풀이 2 - 가장 큰 값을 맨 앞으로 가져오는 스타일
    public int[] solution2(int[] array) {
        int[] answer = new int[2];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > answer[0]) {
                answer[0] = array[i];
                answer[1] = i;
            }
        }

        return answer;
    }

    // 풀이 3 - stream
    public int[] solution3(int[] array) {
        return IntStream.range(0, array.length)
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(i, array[i]))
                .max(Map.Entry.comparingByValue())
                .map(entry -> new int[]{entry.getValue(), entry.getKey()})
                .orElseThrow();
        /*
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        int max = list.stream().max(Integer::compareTo).orElse(0);
        int index = list.indexOf(max);
        return new int[] {max, index};
         */
    }
}
