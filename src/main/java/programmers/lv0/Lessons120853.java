package programmers.lv0;

import java.util.Stack;

// lv0 컨트롤 제트
public class Lessons120853 {
    /*
    * - Integer.valueOf(~) parseInt(~) 차이
    *   -> valueOf(~)는 Integer 반환, parseInt(~)는 int 반환
    *   + valueOf(~)는 유니코드 관련 작업 처리 가능
    *   -> 딱 숫자만 필요하다고 하면 parseInt(~) 사용 / valueOf(~)는 내부적으로 parseInt(~) 사용함
    *   (참고) https://dejavuhyo.github.io/posts/java-parseint-vs-valueof/
    *
    * - 풀이 3, 4 Stack 사용 - 4가 더 정석에 가까울 듯
    * */

    // 풀이 1 - Integer.valueOf(~)
    // 생각보다 오래 걸린다.
    public int solution1(String s) {
        String[] strings = s.split(" ");
        int answer = 0;

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("Z")) {
                answer -= Integer.valueOf(strings[i - 1]);
            } else {
                answer += Integer.valueOf(strings[i]);
            }
        }
        return answer;
    }

    // 풀이 2 - Integer.parseInt(~)
    public int solution2(String s) {
        String[] strings = s.split(" ");
        int answer = 0;

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("Z")) {
                answer -= Integer.parseInt(strings[i - 1]);
            } else {
                answer += Integer.parseInt(strings[i]);
            }
        }
        return answer;
    }
    
    // 풀이 3(다른 풀이 힌트 참고) - Stack 사용
    public int solution3(String s) {
        Stack<String> stack = new Stack();
        for (String substring : s.split(" ")) {
            stack.push(substring);
        }

        int answer = 0;
        while (!stack.empty()) {
            String popped = stack.pop();
            // popped.equals("Z") ? stack.pop() : sum += Integer.parseInt(popped);
            // 위 식은 Serializable 관련 문제로 컴파일 불가능
            if (popped.equals("Z")) {
                stack.pop();
            } else {
                answer += Integer.parseInt(popped);
            }
        }
        return answer;
    }

    // 풀이 4(다른 풀이) - Stack 사용 - 이게 더 정석에 가까울 듯
    public int solution4(String s) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for (String w : s.split(" ")) {
            if (w.equals("Z")) {
                stack.pop();
            } else {
                stack.push(Integer.parseInt(w));
            }
        }
        for (int i : stack) { // Integer로 들어갔지만 int로 꺼내도 자동 형변환됨
            answer += i;
        }
        return answer;
    }
}
