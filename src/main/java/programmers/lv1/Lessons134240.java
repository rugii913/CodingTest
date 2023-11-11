package programmers.lv1;

import java.util.Stack;

// lv1 푸트 파이트 대회
public class Lessons134240 {

    // 풀이 1 // cf. sb2 = sb1.reverse() 이런 식으로 하면, sb2만 거꾸로된 문자들이 저장되는 게 아니라, sb1도 문자들 순서가 바뀜
    public String solution1(int[] food) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < food.length; i++) {
            int criteria = food[i];

            if (food[i] % 2 != 0) {
                food[i]--;
            }

            while (food[i] > criteria / 2) {
                sb.append(i);
                food[i]--;
            }
        }
        return sb.toString().concat("0").concat(sb.reverse().toString());
    }

    // 풀이 1-1 // 풀이 2 보고 수정 -> 다른 풀이에도 이런 풀이들 많음
    public String solution1_1(int[] food) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < food.length; i++) {
            sb.append(String.valueOf(i).repeat(food[i] / 2));
        }
        return sb.toString().concat("0").concat(sb.reverse().toString());
    }

    // 풀이 1-2 // 풀이 2 보고 수정, 이렇게도 가능은 하지만 의미 전달은 떨어짐
    public String solution1_2(int[] food) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < food.length; i++) {
            int criteria = food[i] + 1;

            while (food[i] > criteria / 2) {
                sb.append(i);
                food[i]--;
            }
        }
        return sb.toString().concat("0").concat(sb.reverse().toString());
    }

    // 풀이 2(다른 풀이 참고 수정)
    public String solution2(int[] food) {
        StringBuilder sb = new StringBuilder("0");
        for (int i = food.length - 1; i > 0; i--) {
            for (int j = 0; j < food[i] / 2; j++) { // 이 부분이 깔끔하다고 느낌, % 2 이런 거 사용하지 않음
                sb.append(i).insert(0, i);
            }
        }
        return String.valueOf(sb);
    }

    // 풀이 3(다른 풀이 참고) - Stack
    public String solution3(int[] food) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < food.length; i++) {
            int criteria = food[i] / 2;
            for (int j = 0; j < criteria; j++) {
                sb.append(i);
                stack.push(i);
            }
        }
        sb.append(0);
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
