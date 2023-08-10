package programmers.lv0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// lv0 최빈값 구하기
public class Lessons120812 {

    Class<?> parameterType = int.class;
    Class<?> returnType = int[].class;

    //풀이 1
    public int solution1(int[] array) {
        int[] count = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] == array[j]) {
                    count[i]++;
                }
            }
        }

        int countMax = 0;
        for (int i : count) {
            if (countMax < i) {
                countMax = i;
            }
        }

        int numOfCountMax = 0;
        for (int i : count) {
            if (countMax == i) {
                numOfCountMax++;
            }
        }

        if (numOfCountMax != countMax) {
            return -1;
        }

        int indexOfMode = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] == countMax) {
                indexOfMode = i;
            }
        }
        return array[indexOfMode];
    }

    //풀이 2(다른 풀이 참고)
    public int solution2(int[] array) {
        List<Map.Entry<Integer, List<Integer>>> list =
                new ArrayList<>(Arrays.stream(array).boxed()
                        .collect(Collectors.groupingBy(o -> o))
                        .entrySet()).stream()
                        .sorted((t0, t1) -> Integer.compare(t1.getValue().size(), t0.getValue().size()))
                        .collect(Collectors.toList());

        return list.size() > 1 &&
                list.get(0).getValue().size() - list.get(1).getValue().size() == 0
                ? -1 : list.get(0).getKey();
    }

}
