package programmers.lv0;

import java.util.StringTokenizer;
import java.util.stream.Stream;

// lv0 공백으로 구분하기 2, 1
public class Lessons181868_181869 {

    // 풀이 1 - regex 설명 - Pattern 클래스에 요약되어 있음 혹은 https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html
    public String[] solution1(String my_string) {
        return my_string.trim().split("\\s+");
    }

    // 풀이 1-1(다른 풀이 참고)
    public String[] solution1_1(String my_string) {
        return my_string.trim().split("[ ]+");
    }
    
    // 풀이 2 - StringTokenizer - legacy이므로 자제
    public String[] solution2(String my_string) {
        StringTokenizer st = new StringTokenizer(my_string);
        int N = st.countTokens();
        String[] result = new String[N];
        for (int n = 0;n < N;n++)
            result[n] = st.nextToken();
        return result;
    }

    // 풀이 3(다른 풀이 참고) - stream
    public String[] solution3(String my_string) {
        return Stream.of(my_string.split(" ")).filter(str -> !str.isEmpty()).toArray(String[]::new);
    }
}
