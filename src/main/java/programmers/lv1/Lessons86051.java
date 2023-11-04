package programmers.lv1;

import java.util.HashSet;
import java.util.Set;

// lv1 없는 숫자 더하기
public class Lessons86051 {

    // 풀이 1 - HashSet 이용
    public int solution1(int[] numbers) {
        // numbers의 모든 원소는 서로 다릅니다.
        Set<Integer> numbersSet = new HashSet<>(Set.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        for (int number : numbers) {
            numbersSet.remove(number);
        }

        int sum = 0;
        for (int number : numbersSet) {
            sum += number;
        }
        return sum;
    }

    // 풀이 2
    public int solution2(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum = sum + i - numbers[i];
        }
        for (int i = numbers.length; i < 10; i++) {
            sum += i;
        }
        return sum;
    }

    // 풀이 2-1(다른 풀이 참고)
    public int solution2_1(int[] numbers) {
        int sum = 45;
        for (int i : numbers) {
            sum -= i;
        }
        return sum;
    }

    // 풀이 3 - 기수 정렬 같은 느낌으로
    public int solution3(int[] numbers) {
        int[] indices = new int[10];
        for (int number : numbers) {
            indices[number]++;
        }

        int sum = 0;
        for (int index = 0; index < indices.length; index++) {
            if (indices[index] == 0) {
                sum += index;
            }
        }
        return sum;
    }
}
