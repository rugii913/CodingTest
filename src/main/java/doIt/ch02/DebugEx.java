package doIt.ch02;

import java.util.Scanner;

public class DebugEx {
    public static void main(String[] args) {
        // TODO 배열에서 주어진 범위의 합을 구하는 프로그램을 구하시오
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        // int answer = 0; // 오류 1. 변수 초기화 오류 찾아보기
        int A[] = new int[100_001];
        int S[] = new int[100_001];
        // for (int i = 1; i < 10_000; i++) { // 오류 2. 반복문에서 인덱스 범위 지정 오류 찾아보기
        for (int i = 1; i <= 100_000; i++) { // 반복 범위를 잘못 지정하거나 비교 연산자를 반대로 사용하는 것 유의
            A[i] = (int) (Math.random() * Integer.MAX_VALUE);
            S[i] = S[i - 1] + A[i];
        }
        for (int t = 1; t < testcase; t++) {
            int query = sc.nextInt(); // query는 합할 범위의 개수라고 생각하면 될 것 같다.
            int answer = 0; // 오류 1. 변수 초기화 오류 찾아보기 - 그 다음 테스트로 넘어갈 때 answer는 초기화되어야 한다.
            for (int i = 0; i < query; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                answer += S[end] - S[start - 1];
                // System.out.println(testcase + " " + answer);
                // 오류 3. 잘못된 변수 사용 오류 찾아보기 - 출력 부분이나 로직 안에서 다른 변수를 혼동하여 잘못 사용하는 것 유의
                // ex. 반복문에서 반복 변수를 사용해야 하는데 기준 변수를 사용, 변수 이름 자체가 비슷해서 잘못 사용
                System.out.println(t + " " + answer);
            }
        }
    }
}
