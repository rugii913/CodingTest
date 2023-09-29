package programmers.lv0;

import java.util.Arrays;
import java.util.stream.Collectors;

// lv0 꼬리 문자열
public class Lessons181841 {

    // 풀이 1 // 0.02 ms ~ 0.07 ms
    public String solution1(String[] str_list, String ex) {
        String answer = "";

        for (String string : str_list) {
            if (!string.contains(ex)) {
                answer = answer.concat(string);
            }
        }

        return answer;
    }

    // 풀이 1-1(다른 풀이 참고) - StringBuilder 사용 // 0.03 ms ~ 0.09 ms
    public String solution1_1(String[] str_list, String ex) {
        StringBuilder sb = new StringBuilder();

        for (String s : str_list) {
            sb.append(s.contains(ex) ? "" : s);
        }

        return sb.toString();
    }

    // 풀이 1-2(다른 풀이 수정) - StringBuilder 사용 // 0.03 ms ~ 0.06 ms // 차라리 풀이 1-1이 더 깔끔한 것 같기도 하고...
    public String solution1_2(String[] str_list, String ex) {
        StringBuilder sb = new StringBuilder();

        for (String string : str_list) {
            sb = string.contains(ex) ? sb : sb.append(string); // (IDE 경고) 변수가 이미 이 값에 대입되었습니다 - if문으로 바꾸라고 추천
        }

        return sb.toString();
    }

    // 풀이 2 - stream reduce(~)사용 // 0.75 ms ~ 1.24 ms
    public String solution2(String[] str_list, String ex) {
        return Arrays.stream(str_list)
                .filter(string -> !string.contains(ex))
                .reduce("", String::concat);
    }

    // 풀이 2-1(다른 풀이 참고) - stream collect(~), Collectors.joining() 사용 // 1.28 ms ~ 2.30 ms
    public String solution2_1(String[] str_list, String ex) {
        return Arrays.stream(str_list)
                .filter(string -> !string.contains(ex))
                .collect(Collectors.joining());
    }
}
