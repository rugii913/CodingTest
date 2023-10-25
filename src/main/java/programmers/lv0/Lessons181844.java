package programmers.lv0;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// lv0 배열의 원소 삭제하기
public class Lessons181844 {

    // 풀이 1
    public int[] solution1(int[] arr, int[] delete_list) {
        List<Integer> list = new LinkedList<>();
        for (int i : arr) {
            list.add(i);
        }
        for (Integer j : delete_list) {
            list.remove(j);
        }
        int[] answer = new int[list.size()];
        for (int k = 0; k < answer.length; k++) {
            answer[k] = list.get(k);
        }
        return answer;
    }

    // 풀이 2
    public int[] solution2(int[] arr, int[] delete_list) { // filter 사용, delete_list에 있는 것들은 거름
        return Arrays.stream(arr).filter(number -> !isElementOfArray(number, delete_list)).toArray();
    }

    private boolean isElementOfArray(int number, int[] array) {
        for (int arrayElement : array) {
            if (number == arrayElement) {
                return true;
            }
        }
        return false;
    }

    // 풀이 2-1(다른 풀이 참고) - IntStream 두 개 만들어서 한 줄로 끝내기
    public int[] solution2_1(int[] arr, int[] delete_list) {
        return IntStream.of(arr).filter(i -> IntStream.of(delete_list).noneMatch(s -> s == i)).toArray();
    }

    // 풀이 2-2(다른 풀이 참고) - Set 사용
    public int[] solution2_2(int[] arr, int[] delete_list) {
        Set<Integer> delete = new HashSet<>();
        for (int del : delete_list)
            delete.add(del);
        return Arrays.stream(arr).filter(i -> !delete.contains(i)).toArray();
    }

    // 풀이 2-3(다른 풀이 참고 수정) - 풀이 2-2 수정해서 한 줄로
    public int[] solution2_3(int[] arr, int[] delete_list) {
        return IntStream.of(arr).filter(i -> !Arrays.stream(delete_list).boxed().collect(Collectors.toSet()).contains(i)).toArray();
    }
}
