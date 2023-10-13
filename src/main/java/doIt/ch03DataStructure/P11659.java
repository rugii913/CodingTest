package doIt.ch03DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

// Do it 코딩테스트 ch3-문제 003 구간 합 구하기 // P11659
/*
 * p.42
 * - 목적: 시간 복잡도를 줄이기 위함 - 합 배열을 미리 구해 놓으면 기존 배열의 일정 범위의 합을 구하는 시간 복잡도가 O(N)에서 O(1)로 감소
 * - 합 배열을 만드는 공식: S[i] = S[i - 1] + A[i]
 * - 구간 합을 구하는 공식: S[j] - S[i - 1] // i에서 j까지 구간 합
 * */
public class P11659 {
    // 첫번째 줄 입력: 수의 개수 N(1 <= N <= 100,000), 합을 구해야 하는 횟수 M(1 <= M <= 100,000)
    // 두번째 줄 입력: N개의 수(각 수 <= 1,000) - 구간 합을 구할 대상 배열
    // 그 이후 M개의 줄: 합을 구해야 하는 구간 i, j
    /*
     * 시간 복잡도 관련
     * 질의 개수 M 최대 10^5, 수의 개수 최대 10^5, 제한 시간 1초(2 * 10^8)
     * -> 최악의 경우 10^10 연산이 되므로 정직하게 배열을 순회하면서 합을 구하면 안 됨
     * -> 구간 합 배열 사용
     */

    // 풀이 1 - 풀이 2 책 풀이처럼 index 0을 dummy로 만들면 연산 작업도 줄고, 생각하기도 편할 것
    public void solution1() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[] numbers = new int[N];
        int[] sum = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt();
            sum[i] = i == 0 ? numbers[i] : sum[i - 1] + numbers[i];
        }

        for (int i = 0; i < M; i++) {
            int startIndex = scanner.nextInt() - 1;
            int endIndex = scanner.nextInt() - 1;

            if (startIndex == 0) {
                System.out.println(sum[endIndex]);
            } else {
                System.out.println(sum[endIndex] - sum[startIndex - 1]);
            }
        }

        scanner.close();
    }

    // 풀이 2(책)
    // - 아예 배열 S의 길이를 suNo가 아니라 suNo + 1로 줌 ~> 구간 합 계산 편하게 하기 위해 + 인덱스 기준이 아니라 i, j번째 수로 계산 편하게 하기 위해
    // - cf. StringTokenizer는 legacy임에 유의할 것 // BufferedReader, InputStreamReader, System.in 생각해보기
    public void solution2() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int suNo = Integer.parseInt(stringTokenizer.nextToken());
        int quizNo = Integer.parseInt(stringTokenizer.nextToken());
        long[] S = new long[suNo + 1];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= suNo; i++) {
            S[i] = S[i - 1] + Integer.parseInt(stringTokenizer.nextToken());
        }
        for (int q = 0; q < quizNo; q++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int i = Integer.parseInt(stringTokenizer.nextToken());
            int j = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println(S[j] - S[i - 1]);
        }
        
        bufferedReader.close();
    }
}
