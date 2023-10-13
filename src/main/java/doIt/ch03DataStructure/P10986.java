package doIt.ch03DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

// Do it 코딩테스트 ch3-문제 005 나머지 합 구하기 // P10986
public class P10986 {
    // 첫번째 줄 입력: 수의 개수(1 <= N <= 10^6), 나누어 떨어지게 할 수(2 <= M <= 10^3)
    // 두번째 줄 입력: N개의 수 A1, ..., AN (0 <= Ai <= 10^9)

    /*
     * 나머지 합 문제 풀이의 핵심 아이디어
     * 1. 구간 합 배열을 이용한 식 S[j] - S[i]는 원본 배열의 i + 1부터 j까지의 구간 합이다.
     * 2. (A + B) % C은 ((A % C) + (B % C)) % C와 같다.
     *  - 즉 특정 구간 수들의 나머지 연산을 더해 나머지 연산을 한 값과 이 구간 합의 나머지 연산을 한 값은 동일하다.
     * 3. S[j] % M의 값과 S[i] % M의 값이 같다면 (S[j] - S[i]) % M은 0이다.
     *  - 즉, 구간 합 배열의 원소를 M으로 나눈 나머지로 업데이트하고
     *  - S[j]와 S[i]가 같은 (i, j) 쌍을 찾으면
     *  - 원본 배열에서 i + 1부터 j까지의 구간 합이 M으로 나누어 떨어진다는 것을 알 수 있다.
     *
     * cf. 나눗셈 대상 수 dividend, 나눗셈하는 수 divisor, 몫 quotient, 나머지 remainder
     *  dividend = divisor * quotient + remainder (0 <= remainder < quotient)
     *  위 아이디어 2 확인
     *  - (A + B) % C = Rab => A + B = C * Qab + Rab
     *    => Rab = A + B - C * Qab
     *  - ((A % C) + (B % C)) % C = Rs => (A % C) + (B % C) = C * Qs + Rs
     *    => Rs = (A % C) + (B % C) - C * Qs
     *    => Rs = Ra + Rb - C * Qs where Ra = A - C * Qa, Rb = B - C * Qb
     *    => Rs = A - C * Qa + B - C * Qb - C * Qs = A + B - C(Qa + Qb + Qs)
     *    here let (Qa + Qb + Qs) = Qab, then
     *    => Rs = A + B - C * Qab
     *    => Rab = Rs
     */

    /*
     * 각 풀이 비교
     *   - scanner.nextInt(~)가 역시 가독성은 좋지만, 성능은 좋지 않다.
     *   - BufferedReader는 한 번 원본 배열을 거쳐가고, Scanner는 원본 배열을 거치치 않는 것처럼 보이는데도 Scanner가 더 느리다.
     * - 풀이 1: Scanner, scanner.nextInt(~) 사용
     *   - 메모리 328,056 KB, 시간 1,828 ms
     * - 풀이 2: BufferedReader, InputStream, string.split(~) 사용
     *   - 메모리 138,824 KB, 시간 516 ms
     */

    // 풀이 x - 시간 초과 - 이중 for문 등
    public void solutionx() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        // int[] inputInts = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        inputs = br.readLine().split(" ");

        int[] intervalSums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            intervalSums[i] = intervalSums[i - 1] + Integer.parseInt(inputs[i - 1]);
        }

        int count = 0;
        for (int j = 1; j <= N; j++) {
            for (int i = 1; i <= j; i++) {
                if ((intervalSums[j] - intervalSums[i - 1]) % M == 0) {
                    count++;
                }
            }
        }

        System.out.println(count);

        br.close();
    }

    // 풀이 1(책 구현 코드) - 포기해서 책 구현 코드 확인
    public void solution1() throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        long[] S = new long[N];
        long[] C = new long[M];
        long answer = 0;

        S[0] = sc.nextInt(); // 이번엔 길이 N + 1 짜리 배열을 만들지 않고 N 짜리 배열을 만듦
        for (int i = 1; i < N; i++) { // 수열 합 배열 만들기
            S[i] = S[i - 1] + sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            // 합 배열의 모든 값마다 % 연산 수행, 이 나머지 값으로 작업할 것
            int remainder = (int) (S[i] % M);
            // 0 ~ i까지 구간 합 자체가 0인 경우를 정답에 더하기
            if (remainder == 0) {
                answer++;
            }
            // 나머지가 x이면 C[x]의 카운트를 하나 늘려준다. -> 나머지가 같은 인덱스의 개수 카운트
            C[remainder]++;
        }

        for (int i = 0; i < M; i++) {
            if (C[i] > 1) { // 2개를 뽑아야 하므로, 카운트가 1인 경우는 거른다.
                // 나머지가 같은 인덱스 중 2개를 뽑는 경우의 수를 더하기
                answer = answer + C[i] * (C[i] - 1) / 2;
            }
        }

        System.out.println(answer);

        sc.close();
    }

    // 풀이 1-1(책 구현 코드 수정 가능한지 확인)
    public void solution1_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);
        long[] S = new long[N + 1];
        long[] C = new long[M];
        long answer = 0;

        inputs = br.readLine().split(" ");

        for (int i = 1; i <= N; i++) {
            S[i] = S[i - 1] + Integer.parseInt(inputs[i - 1]);

            int remainder = (int) (S[i] % M);
            if (remainder == 0) {
                answer++;
            }
            C[remainder]++;
        }

        for (int i = 0; i < M; i++) {
            if (C[i] > 1) {
                answer = answer + C[i] * (C[i] - 1) / 2;
            }
        }

        System.out.println(answer);

        br.close();
    }
}
