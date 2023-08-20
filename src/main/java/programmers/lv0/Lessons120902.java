package programmers.lv0;

import java.util.Arrays;

// lv0 문자열 계산하기
public class Lessons120902 {

    // 풀이 1 +7점??
    public int solution1(String my_string) {
        String[] stringNumbers = my_string.split(" ");
        int answer = Integer.parseInt(stringNumbers[0]);
        for (int i = 0; i < stringNumbers.length; i++) {
            if (stringNumbers[i].equals("+")) {
                answer += Integer.parseInt(stringNumbers[i + 1]);
            } else if (stringNumbers[i].equals("-")) {
                answer -= Integer.parseInt(stringNumbers[i + 1]);
            }
        }
        return answer;
    }

    // 풀이 1-1(다른 풀이 참고)
    public int solution1_1(String my_string) {
        int answer = 0;
        String[] s = my_string.trim().split(" ");
        int oper = 1;
        for (String s1 : s) {
            if (s1.equals("+") || s1.equals("-")) {
                oper = s1.equals("-") ? -1 : 1;
            } else {
                answer += Integer.parseInt(s1) * oper;
            }
        }
        return answer;
    }

    // 풀이 2(다른 풀이 참고) - 빼기 연산을 음의 정수로 바꿔쳐서 sum
    public int solution2(String my_string) {
        return Arrays.stream(my_string.replaceAll("- ", "-").replaceAll("[+] ", "").trim().split(" ")).mapToInt(Integer::parseInt).sum();
    }

}
