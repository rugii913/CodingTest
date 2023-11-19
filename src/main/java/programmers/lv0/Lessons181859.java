package programmers.lv0;

import java.util.*;

// lv0 배열 만들기 6
public class Lessons181859 {
    // 복잡하게 ~하고 1을 더한다고 써놓았지만, 배열 순회하는 것과 다를 것 없는 상황(모든 경우의 수에서 i에 1을 더함)

    // 풀이 1 - Deque를 사용해봄
    public int[] solution1(int[] arr) {
        Deque<Integer> deque = new ArrayDeque<>();
        // Deque<Integer> deque = new LinkedList<>(); // cf. Java의 LinkedList도 Deque를 구현함
        for (int element : arr) {
            if (deque.isEmpty() || !deque.peekLast().equals(element)) {
                deque.addLast(element);
            } else if (deque.peekLast().equals(element)) { // else if 조건이 아니라 단순하게 else로 묶을 수도 있지만, 명확하게 보여주자...
                deque.removeLast();
            }
        }

        int[] stk;
        if (deque.isEmpty()) {
            stk = new int[]{-1};
        } else {
            stk = new int[deque.size()];
            int answerIndex = 0;
            while (!deque.isEmpty()) {
                stk[answerIndex++] = deque.pollFirst();
            }
        }

        return stk;
    }

    // 풀이 1-1 - 배열로 변환할 때 stream 사용
    public int[] solution1_1(int[] arr) {
        Deque<Integer> deque = new LinkedList<>();
        for (int element : arr) {
            if (deque.isEmpty() || !deque.peekLast().equals(element)) {
                deque.addLast(element);
            } else if (deque.peekLast().equals(element)) {
                deque.removeLast();
            }
        }

        int[] stk = deque.stream().mapToInt(i -> i).toArray();
        return stk.length == 0 ? new int[]{-1} : stk;
    }

    // 풀이 1_2 - (Array)List를 사용해봄
    public int[] solution1_2(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int element : arr) {
            if (list.isEmpty() || !list.get(list.size() - 1).equals(element)) {
                list.add(element);
            } else if (list.get(list.size() - 1).equals(element)) { // else if 조건이 아니라 단순하게 else로 묶을 수도 있지만, 명확하게 보여주자...
                list.remove(list.size() - 1);
            }
        }

        int[] stk;
        if (list.isEmpty()) {
            stk = new int[]{-1};
        } else {
            stk = new int[list.size()];
            int answerIndex = 0;
            for (Integer number : list) {
                stk[answerIndex++] = number;
            }
        }

        return stk;
    }

    // 풀이 2(다른 풀이 참고) - 무난하게 stack 사용
    public int[] solution2(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (int number : arr) {
            if (!stack.isEmpty() && number == stack.peek()) {
                stack.pop();
            } else {
                stack.push(number);
            }
        }

        return stack.isEmpty() ? new int[]{-1} : stack.stream().mapToInt(i -> i).toArray();
    }
}
