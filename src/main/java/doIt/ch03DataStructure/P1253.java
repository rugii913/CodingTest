package doIt.ch03DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Do it 코딩테스트 ch3-문제 008 '좋은 수' 구하기(문제 제목: 좋다) // P1253
public class P1253 {
    // 첫번째 줄 입력: 수의 개수(1 <= N <= 2,000)
    // 두번째 줄 입력: N개의 수들 Ai(|Ai| <= 1,000,000,000

    /*
     * (투 포인터 이동 원칙)
     * case 1. A[i] + A[j] > K
     *         j--;
     * case 2. A[i] + A[j] < K
     *         i++;
     * case 3. A[i] + A[j] == K
     *         i++;
     *         j--;
     *         count++;
     */
    /*
     * 시간 복잡도 관련
     * N 최대 2,000, 제한 시간 2초(2 * 10^8)이므로
     * -> O(nlogn) 알고리즘 사용 가능 - 기본 정렬 사용 가능 (log_2(15000) * 15000 ~~ 208090 = 2 * 10^5)
     * -> O(n^2) 알고리즘 사용 가능 - (2,000)^2 = 4 * 10^6 < 10^8
     * --> 책 구현 형태를 보면 대략 O(nlogn + n^2) = O(n^2) 형태라고 보면 될 것 같다.
     */

    // 풀이 x1부터 x4까지 보자면, 어떻게든 시간 복잡도를 줄이기 위해, count 후 break 할 수 있는 경우 break 하려고 했던 건데 굳이 그럴 필요가 없었음
    // n^2 알고리즘이 사용 가능하므로, 책처럼 그냥 n^2를 사용하면 된다. - 투 포인터를 사용함으로써 이미 n^3을 n^2으로 줄여낸 것

    // 풀이 1(책) - 포기 후 책 구현 코드 확인
    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int Result = 0;
        long[] A = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(A);

        for (int k = 0; k < N; k++) {
            long find = A[k];
            int i = 0;
            int j = N - 1;
            // 투 포인터 알고리즘
            while (i < j) {
                if (A[i] + A[j] == find) {
                    // find는 서로 다른 두 수의 합이어야 함을 체크 - i나 j 중 하나라도 k와 같으면 넘어감
                    if (i != k && j != k) {
                        Result++;
                        break;
                    } else if (i == k) {
                        i++;
                    } else if (j == k) { // else로 쓸 수 있지만, 명확하게 보여주기 위해 else if (j == k) 남겨둠
                        j--; // 여기를 j++;로 잘못 작성해서 계속 ArrayIndexOutOfBoundsException 터졌음
                    }
                } else if (A[i] + A[j] < find) {
                    i++;
                } else {
                    j--;
                }
            }
        }

        System.out.println(Result);
        br.close();
    }

    // 풀이 1-1(책 풀이 수정) - long이 아니라 int로 받아도 통과함
    public void solution1_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        int count = 0;
        for (int k = 0; k < N; k++) {
            int target = A[k];
            int i = 0;
            int j = N - 1;

            // 투 포인터 알고리즘
            while (i < j) {
                if (A[i] + A[j] == target) {
                    // find는 서로 다른 두 수의 합이어야 함을 체크 - i나 j 중 하나라도 k와 같으면 넘어감
                    if (i != k && j != k) {
                        count++;
                        break;
                    } else if (i == k) {
                        i++;
                    } else {
                        j--;
                    }
                } else if (A[i] + A[j] < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }

        System.out.println(count);
        br.close();
    }

    // 풀이 x1 - 시간 초과 걸릴 것 같다는 느낌이 들지만 일단 작성 - 틀림 -> 풀이 x2로 수정 시도
    public void solutionx1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] numberStrings = br.readLine().split("\\s");
        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]);
        }

        Arrays.sort(numbers);
        int count = 0;

        for (int targetIndex = 0; targetIndex < N; targetIndex++) {
            int pointer1;
            int pointer2;

            // 음수 양수 모두 있는 경우
            if (numbers[0] < 0 && numbers[N - 1] > 0) {
                pointer1 = 0;
                pointer2 = N - 1;
                while (pointer1 < targetIndex && pointer2 > targetIndex) {
                    if (numbers[pointer1] > 0 || numbers[pointer2] < 0) {
                        break;
                    } else if (numbers[targetIndex] == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        break;
                    } else if (numbers[targetIndex] > numbers[pointer1] + numbers[pointer2]) {
                        pointer1++;
                    } else {
                        pointer2--;
                    }
                }
            }

            // 음수만 있는 경우
            if (numbers[N - 1] < 0 && targetIndex != N - 1) {
                pointer1 = targetIndex + 1;
                pointer2 = N - 1;
                while (pointer1 != pointer2) {
                    if (numbers[targetIndex] == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        break;
                    } else if (numbers[targetIndex] > numbers[pointer1] + numbers[pointer2]) {
                        pointer1++;
                    } else {
                        pointer2--;
                    }
                }
            }

            // 양수만 있는 경우
            if (numbers[0] > 0 && targetIndex != 0) {
                pointer1 = 0;
                pointer2 = targetIndex - 1;
                while (pointer1 != pointer2) {
                    if (numbers[targetIndex] == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        break;
                    } else if (numbers[targetIndex] > numbers[pointer1] + numbers[pointer2]) {
                        pointer1++;
                    } else {
                        pointer2--;
                    }
                }
            }
        }

        System.out.println(count);

        br.close();
    }
    
    // 풀이 x2 - 수정했으나 틀림, 주어진 배열이 모두 양수거나 모두 음수인 경우 뿐만 아니라 일부 양수 일부 음수인 경우에 대해서도 보완하려고 했음
    public void solutionx2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] numberStrings = br.readLine().split("\\s");
        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]);
        }

        Arrays.sort(numbers);
        int count = 0;

        outer: for (int targetIndex = 0; targetIndex < N; targetIndex++) {
            int pointer1;
            int pointer2;

            // 음수 양수 모두 있는 경우
            if (numbers[0] < 0 && numbers[N - 1] > 0) {
                pointer1 = 0;
                pointer2 = N - 1;
                while (pointer1 < targetIndex && pointer2 > targetIndex) {
                    if (numbers[pointer1] > 0 || numbers[pointer2] < 0) {
                        break;
                    } else if (numbers[targetIndex] == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        continue outer; // 아래까지 탐색하지 않도록 outer로 빼줌
                    } else if (numbers[targetIndex] > numbers[pointer1] + numbers[pointer2]) {
                        pointer1++;
                    } else {
                        pointer2--;
                    }
                }
            }

            // target이 음수이며, 게산 가능한 음수 구간이 있는 경우
            if (numbers[targetIndex] < 0 && targetIndex < N - 2 && numbers[targetIndex + 2] < 0) {
                pointer1 = targetIndex + 1;
                pointer2 = targetIndex + 2;
                while (pointer1 != pointer2) {
                    if (numbers[targetIndex] == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        break;
                    } else if (pointer2 == N - 1 || numbers[pointer2 + 1] > 0) {
                        pointer1++;
                    } else {
                        pointer2++;
                    }
                }
            }

            // target이 양수이며, 계산 가능한 양수 구간이 있는 경우
            if (numbers[targetIndex] > 0 && targetIndex > 2 && numbers[targetIndex - 2] > 0) {
                pointer1 = targetIndex - 2;
                pointer2 = targetIndex - 1;
                while (pointer1 != pointer2) {
                    if (numbers[targetIndex] == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        break;
                    } else if (pointer1 == 0 || numbers[pointer1 - 1] < 0 ) {
                        pointer2--;
                    } else {
                        pointer1--;
                    }
                }
            }
        }

        System.out.println(count);

        br.close();
    }

    // 풀이 x3 - 풀이 x2 다시 수정
    public void solutionx3() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] numberStrings = br.readLine().split("\\s");
        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]);
        }

        Arrays.sort(numbers);
        int count = 0;

        outer: for (int targetIndex = 0; targetIndex < N; targetIndex++) {
            int target = numbers[targetIndex];
            int pointer1;
            int pointer2;

            // target 기준 양쪽 합
            if (numbers[0] < 0 && numbers[N - 1] > 0) {
                pointer1 = 0;
                pointer2 = N - 1;
                while (pointer1 < targetIndex && pointer2 > targetIndex) {
                    if (numbers[pointer1] > 0 || numbers[pointer2] < 0) {
                        break;
                    } else if (target == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        continue outer; // 아래까지 탐색해서 중복 계산하지 않도록 outer로 빼줌
                    } else if (target > numbers[pointer1] + numbers[pointer2]) {
                        pointer1++;
                    } else {
                        pointer2--;
                    }
                }
            }

            if (target < 0) { // target이 음수인 경우, target의 오른쪽만 따져봄
                if (targetIndex > N - 3) {
                    continue;
                }
                pointer1 = targetIndex + 1;
                pointer2 = N - 1;
                while (pointer1 != pointer2) {
                    if (target == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        break;
                    } else if (target > numbers[pointer1] + numbers[pointer2]) {
                        pointer1++;
                    } else {
                        pointer2--;
                    }
                }
            } else { // target이 양수인 경우, target의 왼쪽만 따져봄
                if (targetIndex < 2) {
                    continue;
                }
                pointer1 = 0;
                pointer2 = targetIndex - 1;
                while (pointer1 != pointer2) {
                    if (target == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        break;
                    } else if (target > numbers[pointer1] + numbers[pointer2]) {
                        pointer1++;
                    } else {
                        pointer2--;
                    }
                }
            }
        }

        System.out.println(count);

        br.close();
    }

    // 풀이 x4 - 풀이 x3 long으로 수정
    public void solutionx4() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] numberStrings = br.readLine().split("\\s");
        long[] numbers = new long[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(numberStrings[i]);
        }

        Arrays.sort(numbers);
        int count = 0;

        outer: for (int targetIndex = 0; targetIndex < N; targetIndex++) {
            long target = numbers[targetIndex];
            int pointer1;
            int pointer2;

            // target 기준 양쪽 합
            if (numbers[0] < 0 && numbers[N - 1] > 0) {
                pointer1 = 0;
                pointer2 = N - 1;
                while (pointer1 < targetIndex && pointer2 > targetIndex) {
                    if (target == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        continue outer; // 아래까지 탐색해서 중복 계산하지 않도록 outer로 빼줌
                    } else if (target > numbers[pointer1] + numbers[pointer2]) {
                        pointer1++;
                    } else {
                        pointer2--;
                    }
                }
            }

            if (target < 0) { // target이 음수인 경우, target의 오른쪽만 따져봄
                if (targetIndex > N - 3) {
                    continue;
                }
                pointer1 = targetIndex + 1;
                pointer2 = N - 1;
                while (pointer1 != pointer2) {
                    if (target == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        break;
                    } else if (target > numbers[pointer1] + numbers[pointer2]) {
                        pointer1++;
                    } else {
                        pointer2--;
                    }
                }
            } else { // target이 양수인 경우, target의 왼쪽만 따져봄
                if (targetIndex < 2) {
                    continue;
                }
                pointer1 = 0;
                pointer2 = targetIndex - 1;
                while (pointer1 != pointer2) {
                    if (target == numbers[pointer1] + numbers[pointer2]) {
                        count++;
                        break;
                    } else if (target > numbers[pointer1] + numbers[pointer2]) {
                        pointer1++;
                    } else {
                        pointer2--;
                    }
                }
            }
        }

        System.out.println(count);

        br.close();
    }
}
