package programmers.lv0;

import java.util.Stack;

// lv0 배열 만들기 4
public class Lessons181918 {
    // 유사 스택 작성, iterator, enumeration?

    // 풀이 1 - 유사 스택 클래스 작성 // 1.51ms ~ 8.26ms
    public int[] solution1(int[] arr) {
        int i = 0;
        IntStk intStk = new IntStk(arr.length);

        /*
        while (i < arr.length) {
            if (intStk.elementSize == 0) {
                intStk.push(arr[i++]);
            } else if (intStk.peek() < arr[i]) {
                intStk.push(arr[i++]); // 내용이 같으므로 아래처럼 하나로 묶을 수 있음
            } else {
                intStk.pop();
            }
        }
         */
        while (i < arr.length) {
            if (intStk.elementSize == 0 || intStk.peek() < arr[i]) {
                intStk.push(arr[i++]);
            } else {
                intStk.pop();
            }
        }

        return intStk.toArray();
    }

    private static class IntStk {

        int[] stkArray;
        int elementSize;

        IntStk(int capacity) {
            this.stkArray = new int[capacity];
        }

        public void push(int intElement) {
            stkArray[elementSize++] = intElement;
        }

        public void pop() {
            stkArray[elementSize-- - 1] = 0;
        }

        public int peek() {
            return stkArray[elementSize - 1];
        }

        public int[] toArray() {
            int[] returnArray = new int[elementSize];
            System.arraycopy(stkArray, 0, returnArray, 0, elementSize);
            return returnArray;
        }
    }

    // 풀이 2 - Stack 사용 // 6.73ms ~ 55.59ms
    public int[] solution2(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;

        while (i < arr.length) {
            if (stack.empty()) {
                stack.push(arr[i++]);
            } else if (stack.peek() < arr[i]) {
                stack.push(arr[i++]);
            } else {
                stack.pop();
            }
        }

        return stack.stream().mapToInt(intElement -> intElement).toArray();
    }

    // 풀이 2-1 - Stack 사용 - stream 대신 iterator 사용 // 4.13ms ~ 35.19ms
    public int[] solution2_1(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;

        while (i < arr.length) {
            if (stack.empty()) {
                stack.push(arr[i++]);
            } else if (stack.peek() < arr[i]) {
                stack.push(arr[i++]);
            } else {
                stack.pop();
            }
        }

        int[] stk = new int[stack.size()];
        int reverseIndex = stk.length - 1;
        while (stack.iterator().hasNext()) {
            stk[reverseIndex--] = stack.pop();
        }
        return stk;
    }

    // 풀이 2-2 - Stack 사용 - Enumeration - elements().hasMoreElements()? 처음 사용해봄 // 4.62ms ~ 32.75ms
    public int[] solution2_2(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;

        while (i < arr.length) {
            if (stack.empty()) {
                stack.push(arr[i++]);
            } else if (stack.peek() < arr[i]) {
                stack.push(arr[i++]);
            } else {
                stack.pop();
            }
        }

        int[] stk = new int[stack.size()];
        int reverseIndex = stk.length - 1;
        while (stack.elements().hasMoreElements()) {
            stk[reverseIndex--] = stack.pop();
        }
        return stk;
    }
}
