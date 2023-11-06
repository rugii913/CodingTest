package programmers.lv1;

// lv1 이상한 문자 만들기
public class Lessons12930 {

    // 풀이 1
    public String solution1(String s) {
        char[] charArray = s.toCharArray();

        int indexInWord = 0;
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];

            if (ch == ' ') {
                indexInWord = 0;
            } else {
                charArray[i] = indexInWord++ % 2 == 0 ? Character.toUpperCase(ch) : Character.toLowerCase(ch);
            }
        }

        return String.valueOf(charArray);
        // return new String(charArray);
    }

    // 풀이 2(다른 풀이 참고)
    public String solution2(String s) {
        String answer;
        answer = s.toUpperCase();
        char[] chars = answer.toCharArray();

        //앞문자가 대문자라면 소문자로 치환
        for (int i = 1; i < chars.length; i++) {
            if (62 <= chars[i - 1] && chars[i - 1] <= 90) {
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }
        answer = String.valueOf(chars);
        return answer;
    }

    // 풀이 3(다른 풀이 참고)
    public String solution3(String s) {
        StringBuilder answer = new StringBuilder();
        int cnt = 0;
        String[] array = s.split("");

        for (String ss : array) {
            cnt = ss.contains(" ") ? 0 : cnt + 1;
            answer.append(cnt % 2 == 0 ? ss.toLowerCase() : ss.toUpperCase());
        }
        return answer.toString();
    }
}
