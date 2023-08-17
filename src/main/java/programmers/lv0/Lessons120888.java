package programmers.lv0;

import java.util.HashSet;
import java.util.Set;

// lv0 중복된 문자 제거
public class Lessons120888 {

    // 풀이 1 - StringBuilder와 String의 메서드들 사용 - sb.indexOf(~),deleteCharAt(~), string.substring(~)
    //       - 풀리긴 하는데 조잡한 느낌
    //       - 속도는 괜찮음
    public String solution1(String my_string) {
        StringBuilder sb = new StringBuilder(my_string);
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.indexOf(my_string.substring(i, i + 1)) != i) {
                sb.deleteCharAt(i);
            }
        }
        return sb.toString();
    }

    // 풀이 2 - 단순하게 배열과 StringBuilder를 쓴다면
    public String solution2(String my_string) {
        char[] chars = my_string.toCharArray();
        StringBuilder sb = new StringBuilder();

        /*
        // 방법 1 - 일단 추가하고, 중복 발견하면 삭제
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            for (int j = 0; j < i; j++) {
                if (chars[i] == chars[j]) {
                    sb.deleteCharAt(sb.length() - 1);
                    break;
                }
            }
        }
         */
        // 방법 2 - 중복 감지하는 변수, 중복 false면 추가
        for (int i = 0; i < chars.length; i++) {
            boolean duplication = false;
            for (int j = 0; j < i; j++) {
                if (chars[i] == chars[j]) {
                    duplication = true;
                    break;
                }
            }
            if (!duplication) {
                sb.append(chars[i]);
            }
        }

        return sb.toString();
    }

    // 풀이 3 - Set이 중복을 없애주는 것을 이용
    public String solution3(String my_string) {
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = my_string.toCharArray();

        for (char ch1 : chars) {
            set.add(ch1);
        }
        for (int i = 0; i < chars.length; i++) {
            char ch2 = chars[i];
            if (set.contains(ch2)) { // 형 변환 없어도 가능
                sb.append(ch2);
                set.remove(ch2);
            }
        }

        return sb.toString();
    }
}
