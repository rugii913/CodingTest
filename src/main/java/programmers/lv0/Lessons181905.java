package programmers.lv0;

// lv0 문자열 뒤집기
public class Lessons181905 {
    /*
     * Lessons181913 - lv0 문자열 여러 번 뒤집기도 참고
     */

    // 풀이 1 - StringBuilder 별도 배열을 생성하지 않기 // 0.03ms ~ 0.58ms
    public String solution1(String my_string, int s, int e) {
        int midIndex = (s + e) / 2;
        StringBuilder sb = new StringBuilder(my_string);

        for (int i = 0; i < midIndex - s; i++) {
            char tmp = sb.charAt(s + i);
            sb.setCharAt(s + i, sb.charAt(e - i));
            sb.setCharAt(e - i, tmp);
        }

        return sb.toString();
    }

    // 풀이 1-1 - toCharArray(), 별도 배열을 생성하지 않기 // 0.02ms ~ 0.09ms
    public String solution1_1(String my_string, int s, int e) {
        int midIndex = (s + e) / 2;
        char[] charArray = my_string.toCharArray();

        for (int i = 0; i <= midIndex - s; i++) {
            char tmp = charArray[s + i];
            charArray[s + i] = charArray[e - i];
            charArray[e - i] = tmp;
        }

        return new String(charArray);
    }

    // 풀이 1-2 - toCharArray(), 위 풀이 1-1과 거의 같으나, midIndex 사용하지 않음 // 0.02ms ~ 0.07ms 
    public String solution1_2(String my_string, int s, int e) {
        char[] arr = my_string.toCharArray();
        while (s < e) {
            char temp = arr[s];
            arr[s++] = arr[e];
            arr[e--] = temp;
        }

        return new String(arr);
    }

    // 풀이 2 - stringBuilder.reverse() // 0.05ms ~ 0.17ms
    public String solution2(String my_string, int s, int e) {
        StringBuilder sb = new StringBuilder(my_string);
        String reversedSubstring = new StringBuilder(sb.subSequence(s, e + 1)).reverse().toString();
        return sb.replace(s, e + 1, reversedSubstring).toString();
    }

    // 풀이 2-1(다른 풀이 참고) - stringBuilder.reverse() // 9.61ms ~ 15.54ms
    // - sb를 바로 + 로 연결할 수도 있음
    public String solution2_1(String my_string, int s, int e) {
        StringBuilder sb = new StringBuilder(my_string.substring(s, e + 1));
        sb.reverse();
        return my_string.substring(0, s) + sb + my_string.substring(e + 1);
    }

    // 풀이 3 - toCharArray(), 별도 배열을 생성 // 0.02ms ~ 0.08ms
    public String solution3(String my_string, int s, int e) {
        char[] charArray = my_string.toCharArray();
        char[] reversedSubCharArray = new char[e - s + 1];

        for (int i = 0; i < reversedSubCharArray.length; i++) {
            reversedSubCharArray[i] = charArray[e - i];
        }
        System.arraycopy(reversedSubCharArray, 0, charArray, s, reversedSubCharArray.length);

        return new String(charArray);
    }
}
