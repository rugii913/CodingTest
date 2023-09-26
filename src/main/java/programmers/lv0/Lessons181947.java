package programmers.lv0;


import java.util.Scanner;

// lv0 덧셈식 출력하기
public class Lessons181947 {

    // 풀이 1 - format string syntax
    public void solution1() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.printf("%d + %d = %d", a, b, a + b);

        sc.close();
    }
}
