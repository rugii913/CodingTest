package programmers.lv1;

import java.util.Stack;

// lv1 같은 숫자는 싫어
public class Lessons12906 {

    // 풀이 1
    public int[] solution1(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        stack.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (stack.peek() != arr[i]) {
                stack.push(arr[i]);
            }
        }

        return stack.stream().mapToInt(i -> i).toArray();
    }

    // 풀이 2
    public int[] solution2(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        stack.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (stack.peek() != arr[i]) {
                stack.push(arr[i]);
            }
        }

        int[] answer = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            answer[i] = stack.pop();
        }

        return answer;
    }
}
