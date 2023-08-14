package programmers.lv0;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// lv0 배열 회전시키기
public class Lessons120844 {
    /*
    * - 풀이 2 중 이중 콜론 :: 메서드 참조 표현식
    * */

    // 풀이 1 - 일반적인 for문 + switch case
    public int[] solution1(int[] numbers, String direction) {
        int length = numbers.length;
        switch (direction) {
            case "left":
                int last = numbers[0];
                for (int i = 0; i < length - 1; i++) {
                    numbers[i] = numbers[i + 1];
                }
                numbers[length - 1] = last;
                break;
            case "right":
                int first = numbers[length - 1];
                for (int i = length - 1; i > 0; i--) {
                    numbers[i] = numbers[i - 1];
                }
                numbers[0] = first;
                break;
        }
        return numbers;
    }

    // 풀이 2(다른 풀이 참고) - Arrays.stream(): IntStream 사용 - 느린 건 어쩔 수 없다...
    // List<Integer> list = Arrays.asList(numbers);
    // -> int[]를 바로 list로 바꿀 수가 없고, IntStream에서 boxed()로 Integer로 감싸줘야 한다.
    public int[] solution2(int[] numbers, String direction) {
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());

        if (direction.equals("right")) {
            list.add(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);
        } else {
            list.add(list.size(), list.get(0));
            list.remove(0);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
