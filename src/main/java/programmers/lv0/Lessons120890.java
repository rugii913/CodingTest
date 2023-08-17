package programmers.lv0;

import java.util.Arrays;
import java.util.stream.Collectors;

// lv0 가까운 수
public class Lessons120890 {
    /*
    * 풀이 1 - 별도 변수 선언 vs. 풀이 3 - 정렬 후 배열 내에서 해결
    * */

    // 풀이 1 - 13점...? - 전부 비교, 별도 변수 선언
    public int solution1(int[] array, int n) {
        int closest = Integer.MAX_VALUE;
        int closestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (distance(n, array[i]) < closestDistance) {
                closest = array[i];
                closestDistance = distance(n, array[i]);
            } else if (distance(n, array[i]) == closestDistance) {
                closest = Math.min(array[i], closest);
            }
        }

        return closest;
    }
    private int distance(int a, int b) {
        return Math.abs(a - b);
    }

    // 풀이 2(다른 풀이 참고) - stream 이런 것도 있다...
    public int solution2(int[] array, int n) {
        return Math.min(
                array[Arrays.stream(array).map(operand -> Math.abs(n - operand)).boxed().collect(Collectors.toList()).indexOf(Arrays.stream(array).map(operand -> Math.abs(n - operand)).min().orElse(0))],
                array[Arrays.stream(array).map(operand -> Math.abs(n - operand)).boxed().collect(Collectors.toList()).lastIndexOf(Arrays.stream(array).map(operand -> Math.abs(n - operand)).min().orElse(0))]
        );
    }

    // 풀이 3(다른 풀이 참고) - Arrays.sort() 사용 - 간결하지만 sort()는 느리다.
    public int solution3(int[] array, int n) {
        Arrays.sort(array);
        for (int i = 1; i < array.length; i++) {
            // 비교를 >=가 아닌 >로 하고,
            // 정렬되어 있기 때문에
            // "가장 가까운 수가 여러 개일 경우 더 작은 수를 return 합니다." 제한사항에서 문제가 발생하지 않는다.
            if (Math.abs(n - array[0]) > Math.abs(n - array[i])) {
                array[0] = array[i];
            }
        }
        return array[0];
    }
}
