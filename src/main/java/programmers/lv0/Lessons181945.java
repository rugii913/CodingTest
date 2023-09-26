package programmers.lv0;

import java.util.Scanner;

// lv0 문자열 돌리기
public class Lessons181945 {

    // 풀이 1
    public void solution1() {
        Scanner sc = new Scanner(System.in);
        sc.next().chars().forEach(i -> System.out.println((char) i));
        // sc.next().codePoints().forEach(i -> System.out.println((char) i));
        // chars()와 codePoints()의 차이는 CharsSpliterator를 사용하는지 CodePointSpliterator를 사용하는지
        // -> char value와 unicode codepoint의 차이

        sc.close();
    }

    // 풀이 1_1
    public void solution1_1() {
        Scanner sc = new Scanner(System.in);
        char[] strToCharArray = sc.next().toCharArray();
        for (char c : strToCharArray) {
            System.out.println(c);
        }

        sc.close();
    }
}
