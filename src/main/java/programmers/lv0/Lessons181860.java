package programmers.lv0;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;
import java.util.stream.Stream;

// lv0 빈 배열에 추가, 삭제하기
public class Lessons181860 {

    // 풀이 1 - stack 단순 사용 - 뒤에서 뺀다는 점 때문제 stack을 사용함
    public int[] solution1(int[] arr, boolean[] flag) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < flag.length; i++) {
            if (flag[i]) {
                for (int j = 0; j < 2 * arr[i]; j++) {
                    stack.push(arr[i]);
                }
            } else {
                for (int j = 0; j < arr[i]; j++) {
                    stack.pop();
                }
            }
        }

        Integer[] integerArr = stack.toArray(Integer[]::new);
        int[] answer = new int[integerArr.length];
        for (int i = 0; i < integerArr.length; i++) {
            answer[i] = integerArr[i];
        }

        return answer;
    }

    // 풀이 1-1 - stack stream 사용해서 간결하게 반환
    public int[] solution1_1(int[] arr, boolean[] flag) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < flag.length; i++) {
            if (flag[i]) {
                for (int j = 0; j < 2 * arr[i]; j++) {
                    stack.push(arr[i]);
                }
            } else {
                for (int j = 0; j < arr[i]; j++) {
                    stack.pop();
                }
            }
        }

        return stack.stream().mapToInt(i -> i).toArray();
    }

    // 풀이 2(다른 풀이 참고) - 이 경우 굳이 stack을 사용할 필요가 없다.
    // array로 바꾸는 편의성을 생각하면 list를 사용하는 게 낫다. ArrayList를 사용한다면, 맨 뒤 인덱스를 찾아서 빠르게 제거할 수도 있다.
    // (맨 뒤에서 뽑아내야 하므로 LinkedList는 좋은 선택은 아닐 듯하다.)
    // cf. 다만 stream을 사용한다면... stack이든 list든 int[]로 바꾸기 어렵지 않을 것
    public int[] solution2(int[] arr, boolean[] flag) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (flag[i]) {
                for (int j = 0; j < arr[i] * 2; j++) {
                    list.add(arr[i]);
                }
            } else {
                for (int j = 0; j < arr[i]; j++) {
                    list.remove(list.size() - 1);
                }
            }
        }

        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    // 풀이 2-1(다른 풀이 참고) - deque를 사용하는 방법
    public int[] solution2_1(int[] arr, boolean[] flag) {
        Deque<Integer> list = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            int cnt = arr[i];
            if (flag[i]) {
                cnt *= 2;
                while (cnt-- > 0) {
                    list.add(arr[i]);
                }
            } else {
                while (cnt-- > 0) {
                    list.removeLast();
                }
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    // 풀이 3(다른 풀이 참고) - String으로 따지기
    public int[] solution3(int[] arr, boolean[] flag) {
        String answer = "";

        for (int idx = 0; idx < arr.length; idx++) {
            if (flag[idx]) {
                answer += String.valueOf(arr[idx]).repeat(arr[idx] * 2);
            } else {
                answer = answer.substring(0, answer.length() - arr[idx]);
            }
        }

        return Stream.of(answer.split("")).mapToInt(Integer::parseInt).toArray();
    }
}
