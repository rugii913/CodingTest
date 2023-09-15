package programmers.lv0;

import java.util.Arrays;

// lv0 A로 B 만들기
public class Lessons120886 {

    // 풀이 1 - new String(~)은 char[]를 인자로 받을 수 있음
    // 풀이 1-1처럼 O(n^2) 알고리즘으로 직접 정렬하는 것보다 Arrays 사용하는 게 더 빠름 0.32ms ~ 0.76ms
    // before, after의 length가 작을 때는 직접 정렬이 빠른데, 문제 제한 조건 1,000에 가까워질 수록 더 효율적인 알고리즘을 사용할
    // Arrays.sort(~)를 사용하는 것이 더 나은 듯하다.
    public int solution1(String before, String after) {
        char[] beforeChars = before.toCharArray();
        char[] afterChars = after.toCharArray();
        int length = beforeChars.length;

        Arrays.sort(beforeChars);
        Arrays.sort(afterChars);

        return new String(beforeChars).equals(new String(afterChars)) ? 1 : 0;
    }

    // 풀이 1-1 오히려 풀이 1처럼 Arrays.sort(~) 사용하는 게 더 빠름 // 0.02ms ~ 9.16ms
    public int solution1_1(String before, String after) {
        char[] beforeChars = before.toCharArray();
        char[] afterChars = after.toCharArray();
        int length = beforeChars.length;

        for (int i = 1; i < length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (beforeChars[j] > beforeChars[j + 1]) {
                    char tmp = beforeChars[j];
                    beforeChars[j] = beforeChars[j + 1];
                    beforeChars[j + 1] = tmp;
                }

                if (afterChars[j] > afterChars[j + 1]) {
                    char tmp = afterChars[j];
                    afterChars[j] = afterChars[j + 1];
                    afterChars[j + 1] = tmp;
                }
            }
        }

        // return new String(beforeChars).equals(new String(afterChars)) ? 1 : 0; // 크게 차이 없음 0.02ms ~ 9.72ms
        for (int i = 0; i < length; i++) {
            if (beforeChars[i] != afterChars[i]) {
                return 0;
            }
        }
        return 1;
    }

    // 풀이 2(다른 풀이 참고) 느림 0.10ms ~ 16.10ms
    public int solution2(String before, String after) {
        for (int i = 0; i < before.length(); i++) {
            after = after.replaceFirst(before.substring(i, i + 1), "");
        }
        return after.length() == 0 ? 1 : 0;
    }
}
