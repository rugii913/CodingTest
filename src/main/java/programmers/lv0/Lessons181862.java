package programmers.lv0;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

// lv0 세 개의 구분자
public class Lessons181862 {
    // split()과 Tokenizer 비교 - P11660 링크 참고
    // regex 설명은 Patterns 클래스

    // 풀이 1 - string.split 사용 // 17.78ms ~ 68.85ms
    public String[] solution1(String myStr) {
        String[] strings = myStr.split("[abc]");

        int countNotEmpty = 0;
        String[] tmp = new String[strings.length];
        for (String str : strings) {
            if (!str.isBlank()) {
                tmp[countNotEmpty++] = str;
            }
        }

        return countNotEmpty == 0 ? new String[]{"EMPTY"} : Arrays.copyOf(tmp, countNotEmpty);
    }

    // 풀이 1-1 // 23.66ms ~ 61.10ms
    public String[] solution1_1(String myStr) {
        String[] strings = myStr.split("[abc]");

        int countNotEmpty = 0;
        String[] tmp = new String[strings.length];
        for (String str : strings) {
            if (!str.isBlank()) {
                tmp[countNotEmpty++] = str;
            }
        }

        String[] result = new String[countNotEmpty];
        System.arraycopy(tmp, 0, result, 0, result.length);

        return countNotEmpty == 0 ? new String[]{"EMPTY"} : result;
    }

    // 풀이 1-2 - stream // 20.05ms ~ 60.05 ms - N의 크기가 10^6 정도 되면 stream을 쓰나 풀이 1처럼 배열을 사용하나 별로 차이 나지 않는다.
    public String[] solution1_2(String myStr) {
        String[] result = Stream.of(myStr.split("[abc]")).filter(str -> !str.isBlank()).toArray(String[]::new);
        return result.length == 0 ? new String[]{"EMPTY"} : result;
    }

    // 풀이 1-3 - LinkedList - Collection의 removeIf() 메서드 // 14.58ms ~ 70.03ms
    public String[] solution1_3(String myStr) {
        LinkedList<String> strings = new LinkedList<>(List.of(myStr.split("[abc]")));
        strings.removeIf(String::isBlank);

        return strings.isEmpty() ? new String[]{"EMPTY"} : strings.toArray(String[]::new);
    }

    // 풀이 2(다른 풀이 참고) - StringTokenizer 사용
    public String[] solution2(String myStr) {
        String[] answer;

        StringTokenizer st = new StringTokenizer(myStr, "abc");
        if (st.countTokens() != 0) {
            answer = new String[st.countTokens()];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = st.nextToken();
            }
        } else {
            answer = new String[1];
            answer[0] = "EMPTY";
        }

        return answer;
    }
}
