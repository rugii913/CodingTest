package programmers.lv0;

// lv0 문자열을 정수로 변환하기
public class Lessons181848 {
    /*
    * Integer.parseInt(~) 자체가 성능이 괜찮아서 굳이 대신 구현을 할 필요가 없을 것 같다.
    * */

    // 풀이 1
    public int solution1(String n_str) {
        return Integer.parseInt(n_str);
    }

    // 풀이 2 - 풀이 1보다 보기 어려운데 빠르지도 않다.
    public int solution2(String n_str) {
        int answer = 0;

        char[] charArray = n_str.toCharArray();
        int length = charArray.length;
        int codePointOfZero = Character.codePointAt("0", 0);

        int operand = 1;
        for (int i = 0; i < length; i++) {
            // int a = Character.getNumericValue(charArray[length - 1 - i]);
            int digit = charArray[length - 1 - i] - codePointOfZero;
            answer += digit * operand;

            operand *= 10;
        }

        return answer;
    }
}
