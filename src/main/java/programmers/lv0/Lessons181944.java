package programmers.lv0;

import java.io.PrintStream;
import java.util.Scanner;

// lv0 홀짝 구분하기 (lv1 짝수와 홀수 + 문자열과 숫자를 함께 출력하기) - 삼항연산자 생각해보기
public class Lessons181944 {

    // 풀이 1
    public void solution1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n % 2 == 0) {
            System.out.printf("%d is even", n);
        } else {
            System.out.printf("%d is odd", n);
        }
    }

    // 풀이 1-1
    public void solution1_1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        PrintStream printStream = n % 2 == 0 ? System.out.printf("%d is even", n) : System.out.printf("%d is odd", n);
        // 삼항 연산자는 항상 돌려받는 값이 있어야 한다.
        // Object object = n % 2 == 0 ? System.out.printf("%d is even", n) : System.out.printf("%d is odd", n);
        // 어차피 출력만 할 것이니 위처럼 Object로 받아도 상관은 없다.
    }

    // 풀이 1-2(다른 풀이 참고)
    public void solution1_2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.printf("%d is %s", n, n % 2 == 0 ? "even" : "odd"); // 이런 식으로 짧게 가능하다. 하지만 굳이...?
    }
}
