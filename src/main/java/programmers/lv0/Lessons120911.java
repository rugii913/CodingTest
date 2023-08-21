package programmers.lv0;

import java.util.Arrays;

// lv0 문자열 정렬하기 (2)
public class Lessons120911 {
    /*
    * String 생성자 new String(char[])
    * */

    // 풀이 1(마지막 부분 다른 풀이 확인) - 배열, sb만 사용 - 생각보다 그리 빠르지 않음
    // 선택, 버블, 삽입 정렬 참고
    // https://shyun00.tistory.com/73
    // https://school.programmers.co.kr/questions/16030#:~:text=import%20java.util.Arrays%3B%20class%20Solution%20%7B%20public%20int%5B%5D%20solution%28int%5B%5D,arr%5Bcommands%5Bi%5D%20-%201%5D%3B%20%7D%20return%20answer%3B%20%7D%20%7D
    public String solution1(String my_string) {
        char[] chars = my_string.toCharArray();
        char smallA = 'a';
        int distance = 'a' - 'A';

        // 소문자로 변환 - 정렬 코드 안에 포함 시키는 것보다 따로 분리하는 게 낫다고 판단함
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < smallA) {
                chars[i] += distance;
            }
        }

        // 정렬
        /*
        // 버블 정렬 스타일
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] > chars[j]) {
                    char tmp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = tmp;
                }
            }
        }
        */
        // 삽입 정렬 스타일
        for (int i = 1; i < chars.length - 1; i++) {
            for (int j = i; j >= 0; j--) {
                if (chars[j] > chars[j + 1]) {
                    char tmp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = tmp;
                }
            }
        }

        // StringBuilder 생성자에 char[]를 바로 받는 생성자는 없지만,
        // char[]를 append할 수는 있음
        // return new StringBuilder(chars.length).append(chars).toString();

        // 그런데 new String(chars)로 String의 생성자에는 char[]를 받는 게 있다!
        // (다른 풀이 참고)
        return new String(chars);
    }

    // 풀이 2 - Arrays.sort(~) 사용
    public String solution2(String my_string) {
        char[] chars = my_string.toLowerCase().toCharArray();
        Arrays.sort(chars);
        return new StringBuilder(chars.length).append(chars).toString();
    }

    // 풀이 3(다른 풀이 참고) - Arrays.sort(~) 사용
    public String solution3(String my_string) {
        // return Arrays.stream(my_string.toLowerCase(Locale.ROOT).split("")).sorted().collect(Collectors.joining());
        // https://zorba10004.tistory.com/400 - Locale.ROOT 사용하는 이유
        return my_string.toLowerCase().chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}
