package programmers.lv1;

// lv1 시저 암호
public class Lessons12926 {

    // 풀이 1
    public String solution1(String s, int n) {
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = push1(charArray[i], n);
        }

        return String.valueOf(charArray);
    }

    private char push1(char ch, int length) {
        if (ch == ' ') {
            return ' ';
        }

        int targetCodePoint = ch + length;
        char result;
        if (ch <= 'Z') {
            if (targetCodePoint <= 'Z') {
                result = (char) targetCodePoint;
            } else {
                result = (char) (targetCodePoint % 'Z' + 'A' - 1);
            }
        } else { // 입력은 알파벳 소문자, 대문자, 공백만
            if (targetCodePoint <= 'z') {
                result = (char) targetCodePoint;
            } else {
                result = (char) (targetCodePoint % 'z' + 'a' - 1);
            }
        }

        return result;
    }

    // 풀이 1-1(다른 풀이 참고 수정) - Character의 메서드들 사용
    public String solution1_1(String s, int n) {
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = push1_1(charArray[i], n);
        }

        return String.valueOf(charArray);
    }

    private char push1_1(char ch, int length) {
        if (ch == ' ') {
            return ' ';
        }

        int targetCodePoint = ch + length;
        char result;
        if (Character.isLowerCase(ch)) {
            if (targetCodePoint <= 'Z') {
                result = (char) targetCodePoint;
            } else {
                result = (char) (targetCodePoint % 'Z' + 'A' - 1);
            }
        } else { // 입력은 알파벳 소문자, 대문자, 공백만
            if (targetCodePoint <= 'z') {
                result = (char) targetCodePoint;
            } else {
                result = (char) (targetCodePoint % 'z' + 'a' - 1);
            }
        }

        return result;
    }
}
