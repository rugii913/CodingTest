package programmers.lv0;

import java.util.Arrays;

// lv0 문자 리스트를 문자열로 변환하기
public class Lessons181941 {

    // 풀이 1 // 0.03ms ~ 0.06ms
    public String solution1(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (String singleElementString : arr) {
            sb.append(singleElementString);
        }
        return sb.toString();
    }

    // 풀이 2 - 나는 이게 가장 마음에 든다. // 0.02ms ~ 0.04ms
    public String solution2(String[] arr) {
        char[] charArray = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            charArray[i] = arr[i].charAt(0);
        }
        return new String(charArray);
    }

    // 풀이 3 // 0.69ms ~ 2.04ms
    public String solution3(String[] arr) {
        return Arrays.stream(arr).reduce("", String::concat);
    }

    // 풀이 4(다른 풀이 참고) // 0.06ms ~ 0.41ms
    public String solution4(String[] arr) {
        return String.join("", arr); // join(CharSequence delimiter, CharSequence... elements)
    }
}
