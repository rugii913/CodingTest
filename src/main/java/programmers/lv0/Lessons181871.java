package programmers.lv0;

import java.util.stream.Stream;

// lv0 문자열이 몇 번 등장하는지 세기
public class Lessons181871 {

    // 풀이 1
    public int solution1(String myString, String pat) {
        /*
        // banana 같은 경우 때문에 안 됨
        int originalLegnth = myString.length();
        int processedLength = myString.replace(pat, "").length();
        return (originalLegnth - processedLength) / pat.length();
         */
        int count = 0;
        for (int i = 0; i <= myString.length() - pat.length(); i++) {
            if (pat.equals(myString.substring(i, i + pat.length()))) {
                count++;
            }
        }

        return count;
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(String myString, String pat) {
        int cnt = 0;
        for (int i = 0; i < myString.length(); i++) {
            if (myString.substring(i).startsWith(pat)) {
                cnt++;
            }
        }
        return cnt;
    }

    // 풀이 3(다른 풀이 참고)
    public int solution3(String myString, String pat) {
        return (int) Stream.iterate(0, i -> i + 1)
                .limit(myString.length() - pat.length() + 1)
                .filter(i -> myString.startsWith(pat, i))
                .count();
    }
}
