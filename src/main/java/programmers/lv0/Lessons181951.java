package programmers.lv0;

import java.io.*;
import java.util.Scanner;

// lv0 a와 b 출력하기 - 숫자 문자열 섞어서 출력
// cf. sc. nextInt()는 입력 간 빈 줄을 허용하지만
// br.readLine()은 입력 간 빈 줄은 허용하지 않는다.
public class Lessons181951 {

    // 풀이 1 - 135.19 ~ 172.25ms
    public void solution1() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.printf("a = %d\n", a);
        System.out.printf("b = %d", b);

        sc.close();
    }

    // 풀이 1-1 - 141.76 ~ 199.46ms
    public void solution1_1() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.println("a = " + a + "\n" + "b = " + b);

        sc.close();
    }

    // 풀이 2(다른 풀이 참고) - 143.01 ~ 163.67ms
    // BufferedReader 생성자 - 인자로 Reader 필요 / InputStreamReader 생성자 - 인자로 InputStream 필요
    // BufferedWriter 생성자 - 인자로 Writer 필요 / OutputStreamWriter 생성자 - 인자로 OutputStream 필요
    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] params = br.readLine().split(" ");
        bw.write("a = " + params[0] + "\n");
        bw.write("b = " + params[1]);

        bw.flush();
        bw.close();
        br.close();

    }
}
