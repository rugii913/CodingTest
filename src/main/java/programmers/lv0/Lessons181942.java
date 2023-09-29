package programmers.lv0;

// lv0 문자열 섞기
public class Lessons181942 {

    // 풀이 1 // 0.03 ms ~ 0.06 ms
    public String solution1(String str1, String str2) {
        int length = str1.length();
        StringBuilder answerBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            answerBuilder.append(str1.charAt(i)).append(str2.charAt(i));
        }

        return answerBuilder.toString();
    }

    // 풀이 1-1 - stringBuilder.insert(~) 사용 // 0.03 ms ~ 0.06 ms
    public String solution1_1(String str1, String str2) {
        int length = str1.length();
        StringBuilder answerBuilder = new StringBuilder(str1);

        for (int i = 0; i < length; i++) {
            answerBuilder.insert(2 * i + 1, str2.charAt(i));
        }

        return answerBuilder.toString();
    }
}
