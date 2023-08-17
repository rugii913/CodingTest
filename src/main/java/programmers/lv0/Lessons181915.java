package programmers.lv0;

import java.util.Arrays;
import java.util.stream.Collectors;

// lv0 글자 이어 붙여 문자열 만들기
public class Lessons181915 {
    /*
    * - StringBuilder 기본적인 사용 방법 문제
    * - cf. StringBuilder , StringBuffer 차이 관련 참고 https://velog.io/@heoseungyeon/StringBuilder와-StringBuffer는-무슨-차이가-있는가
    *   -> 빠른 건 StringBuilder, 멀티 스레드 고려하면 StringBuffer
    * */

    // 풀이 1 - StringBuilder의 기본적인 사용
    public String solution1(String my_string, int[] index_list) {
        StringBuilder sb = new StringBuilder(index_list.length);
        for (int index : index_list) {
            sb.append(my_string.charAt(index));
        }
        return sb.toString();
    }

    // 풀이 2 - stream
    public String solution2(String myString, int[] indexList) {
        return Arrays.stream(indexList).mapToObj(operand -> String.valueOf(myString.charAt(operand))).collect(Collectors.joining());
    }
}
