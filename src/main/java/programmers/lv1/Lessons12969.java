package programmers.lv1;

import java.util.Scanner;

// lv1 직사각형 별찍기
public class Lessons12969 {

    // 풀이 1
    public static void solution1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            System.out.println("*".repeat(n));
        }
    }

    // 풀이 1_1(다른 풀이 참고)
    public static void solution2() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a; i++) { // (IDE 검사) 'String.repeat()'으로 바꿀 수 있습니다
            sb.append("*");
        }
        for (int i = 0; i < b; i++) {
            System.out.println(sb);
        }
    }
}
