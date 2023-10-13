package doIt.ch03DataStructure;

import java.util.Scanner;

// Do it 코딩테스트 ch3-문제 006 연속된 자연수의 합 구하기(수들의 합 5) // P2018
public class P2018 {
    // 첫번째 줄 입력: 분석할 자연수(1 <= N <= 10,000,000) -> 연속된 자연수의 합으로 나타내는 가짓수를 출력해야함
    
    /*
     * 투 포인터: 2개의 포인터로 알고리즘의 시간 복잡도를 최적화 - 알고리즘 자체는 매우 간단
     * * 제한 시간에 비해, N의 개수가 클 때 많이 사용
     * (투 포인터 이동 원칙)
     * case 1. sum > N
     *         sum - start_index;
     *         start_index++;
     * case 2. sum < N
     *         end_index++;
     *         sum = sum + end_index;
     * case 3. sum == N
     *         end_index++;
     *         sum = sum + end_index;
     *         count++;
     */

    // 풀이 x(규칙성 찾아보기) - 실패, 포기, 다른 방식 찾아보기
    public void solutionx() {
        // x: 연속된 수의 개수라고 하자.
        // (1) x % 2 != 0(홀수인 경우) 해당 홀수로 나눠지는 경우 가능
        // (2) x % 2 == 0(짝수인 경우) N * 2가 x로 나눠지면서 N이 x로는 나눠지지 않으면 가능 + 크기 관련 제한 어떻게 줄지 고민...
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int count = 0;
        for (int x = 1; x <= N; x++) {
            if (x % 2 != 0) {
                if (N % x == 0) {
                    count++;
                }
            } else {
                if ((N * 2) % x == 0 && N % x != 0 && 2 * N / x > x) {
                    count++;
                }
            }
        }

        System.out.println(count);

        scanner.close();
    }

    // 풀이 1(합 공식 사용 + 연속된 수들의 특성 사용)
    // (1) 자연수 1부터 x까지의 합: x * (x + 1) / 2
    // (2) (N - sumToX) % x == 0이면 x개 연속된 수의 합으로 표현 가능
    //     - sumToX: 1부터 x까지 x개의 연속된 수의 합
    //       => 2부터 x+1까지 x개의 연속된 수의 합은? sumToX + x (구성하는 모든 수에 1씩 더해졌으므로)
    //       => (N - sumToX) % x == 0이면 N은 연속된 x개의 수의 합으로 표현 가능한 수
    //     - 반복문 종료 조건: x * (x + 1) / 2 > N
    //       => 1부터 x까지의 합이 N보다 크면 (N - sumToX) < 0이므로 (N - sumToX) % x == 0이 자연수에서는 의미가 없어짐
    public void solution1() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int count = 0;
        for (int x = 1; x * (x + 1) / 2 <= N; x++) { // 종료 조건 자체가 x값이 커짐에 따라 동적으로 변경됨
            int sumToX = x * (x + 1) / 2;

            if ((N - sumToX) % x == 0) {
                count++;
            }
        }

        System.out.println(count);

        scanner.close();
    }

    // 풀이 2(책) - 투 포인터
    public void solution2() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int count = 1; // N 혼자 구성된 경우 미리 포함
        int start_index = 1;
        int end_index = 1;
        int sum = 1;

        while (end_index != N) { // start_index == N, end_index == N인 경우는 count에 이미 포함됨
            if (sum ==N) { // 현재 연속 합이 N과 같은 경우
                count++;
                end_index++;
                sum = sum + end_index;
            } else if (sum > N) { // 현재 연속 합이 N보다 더 큰 경우
                sum -= start_index;
                start_index++;
            } else { // 현재 연속 합이 N보다 작은 경우
                end_index++;
                sum += end_index;
            }
        }

        System.out.println(count);

        sc.close();
    }
}
