package programmers.lv0;

import java.util.Scanner;

// lv0 대소문자 바꿔서 출력하기
public class Lessons181949 {

    // 풀이 1 - Character 클래스의 isLowerCase(~), toLowerCase(~) 등 + new String(char[]) 사용
    public void solution1() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        char[] charArrayFromStr = str.toCharArray();
        for (int i = 0; i < charArrayFromStr.length; i++) {
            if (Character.isLowerCase(charArrayFromStr[i])) {
                charArrayFromStr[i] = Character.toUpperCase(charArrayFromStr[i]);
            } else {
                charArrayFromStr[i] = Character.toLowerCase(charArrayFromStr[i]);
            }
        }

        System.out.println(new String(charArrayFromStr));

        sc.close();
    }

    // 풀이 1-1(다른 풀이 참고) - 아스키 코드 +32  *각 문자를 얻을 때마다 PrintStream이 한 번씩 print 하게 해도 생각보다는 느리지 않음
    public void solution1_1() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                System.out.print((char) (c + 32));
            } else {
                System.out.print((char) (c - 32));
            }
        }

        sc.close();
    }
}
