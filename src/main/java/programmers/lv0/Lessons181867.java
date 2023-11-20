package programmers.lv0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// lv0 x 사이의 개수
public class Lessons181867 {
    // !!! 문제점) x로 끝나는 경우 split은 맨 끝의 빈 원소를 만들지 않는다.
    // 그래서 단순히 
    // String[] targetArray = myString.split("x");
    // int[] answer = new int[targetArray.length];
    // for (int i = 0; i < targetArray.length; i++) {
    //      answer[i] = targetArray[i].length();
    // }
    // return answer;
    // 이렇게 풀 수 없는 게 문제
    
    // 풀이 1 - 더 좋은 방법이 있을 듯하다.
    public int[] solution1(String myString) {
        String[] targetArray = myString.split("x");

        if (myString.endsWith("x")) {
            String[] tmp = new String[targetArray.length + 1];
            System.arraycopy(targetArray, 0, tmp, 0, targetArray.length);
            tmp[tmp.length - 1] = "";
            targetArray = tmp;
        }

        int[] answer = new int[targetArray.length];
        for (int i = 0; i < targetArray.length; i++) {
            answer[i] = targetArray[i].length();
        }

        return answer;
    }

    // 풀이 1-1(다른 풀이 참고) - 딱히 더 나은 방법이 없는 듯하다...
    public int[] solution1_1(String myString) {
        List<Integer> list = new ArrayList<>();
        String[] tmp = myString.split("x");

        for (String str : tmp) {
            list.add(str.length());
        }

        if (myString.endsWith("x")) {
            list.add(0);
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    // 풀이 1-2 - 풀이 2, 2-1 확인하니, ... 더 나은 방법이 있었다. split(~) 메서드의 limit 파라미터
    /*
    * The limit parameter controls the number of times the pattern is applied and therefore affects the length of the resulting array.
    * - If the limit is positive then the pattern will be applied at most limit - 1 times, the array's length will be no greater than limit, and the array's last entry will contain all input beyond the last matched delimiter.
    * - If the limit is zero then the pattern will be applied as many times as possible, the array can have any length, and trailing empty strings will be discarded.
    * - If the limit is negative then the pattern will be applied as many times as possible and the array can have any length.
    * ====> 흔하게 쓰는 split(String regex)의 경우 split(regex, 0)이다. - 즉 맨 끝 빈 문자열을 버림(trailing empty strings will be discarded)
    * ** (참고) https://stackoverflow.com/questions/27689065/how-to-split-string-with-trailing-empty-strings-in-result
    * ======> limit을 아무 음수로 넣을 경우 -> 맨 끝이 빈 문자열이라도 버리지 않음
    * ======> limit을 양수로 줄 경우 -> 자르는 기준 패턴이 (limit - 1)번 적용 -> array의 원소는 limit개
    *         + the array's last entry will contain all input beyond the last matched delimiter. 마지막 delimiter 이후로의 모든 input을 포함 -> 마지막 빈 문자열까지 포함
    * ========> limit을 -1로 해도 가능, myString.length()로 해도 가능
    * */
    public int[] solution1_2(String myString) {
        String[] targetArray = myString.split("x", myString.length());
        // String[] targetArray = myString.split("x", -1);

        int[] answer = new int[targetArray.length];
        for (int i = 0; i < targetArray.length; i++) {
            answer[i] = targetArray[i].length();
        }

        return answer;
    }

    // 풀이 2(다른 풀이 참고)
    public int[] solution2(String myString) {
        return Arrays.stream(myString.split("x", myString.length())).mapToInt(String::length).toArray();
    }

    // 풀이 2-1(다른 풀이 참고)
    public int[] solution2_1(String myString) {
        return Arrays.stream(myString.split("x", -1)).mapToInt(String::length).toArray();
    }
}
