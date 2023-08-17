package programmers.lv0;

import java.util.stream.Collectors;

// lv0 대문자와 소문자
public class Lessons120893 {

    // 풀이 1 - 테스트 5~7에서 5 ~ 10ms 정도로 느림
    public String solution1(String my_string) {
        StringBuilder sb = new StringBuilder(my_string.length());

        for (int i = 0; i < my_string.length(); i++) {
            char convertedCh = convertCase(my_string.toCharArray()[i]);
            sb.append(convertedCh);
        }

        return sb.toString();
    }
    private char convertCase(char ch) {
        if (Character.isLowerCase(ch)) {
            return Character.toUpperCase(ch);
        } else if (Character.isUpperCase(ch)) {
            return Character.toLowerCase(ch);
        } else {
            return ch;
        }
    }

    // 풀이 2(다른 풀이 참고) - 오히려 스트림이 빠른 경우 - 테스트 5 ~ 7에서도 2 ~ 3ms 정도
    public String solution2(String my_string) {
        return my_string.chars()
                .mapToObj(operand -> String.valueOf((char) (Character.isLowerCase(operand) ? Character.toUpperCase(operand) : Character.toLowerCase(operand))))
                .collect(Collectors.joining());
    }
}
