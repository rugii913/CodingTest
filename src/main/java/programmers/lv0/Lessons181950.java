package programmers.lv0;

import java.util.Scanner;

// lv0 문자열 반복해서 출력하기
public class Lessons181950 {

    // 풀이 1
    // - 굳이 StringBuilder로 반복작업 할 필요는 없음, 속도 차이도 없고, IDE에서도 repeat()을 쓰라고 추천함
    // - string.repeat()은 내부에서 character storage를 위한 byte[]와 System.arraycope(~)를 사용함
    public void solution1() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int n = sc.nextInt();

        /*
        String repeatStr = str.repeat(n);
        System.out.print(repeatStr);
        */
        System.out.print(str.repeat(n));

        sc.close();
    }
}
