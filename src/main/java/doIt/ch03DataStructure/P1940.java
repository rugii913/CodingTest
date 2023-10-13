package doIt.ch03DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Do it 코딩테스트 ch3-문제 007 주몽의 명령 // P1940
public class P1940 {
    // 첫번째 줄 입력: 재료의 개수(1 <= N <= 15,000)
    // 두번째 줄 입력: 갑옷이 완성되는 합 목표(1 <= M <= 10,000,000)
    // 세번째 줄 입력: N개의 재료들이 가진 고유한 번호들(1 <= u <= 100,000) - 재료 2개의 고유번호 합이 M이 되면, 갑옷 완성 가능

    /*
     * (투 포인터 이동 원칙) - 투 포인터 알고리즘을 구현할 때마다 이동 원칙은 조금씩 달라짐(P2018과 비교)
     * case 1. A[i] + A[j] > M // 번호의 합이 M보다 크므로 큰 번호 index를 내림
     *         j--;
     * case 2. A[i] + A[j] < M // 번호의 합이 M보다 작으므로 작은 번호 index를 올림
     *         i++;
     * case 3. A[i] + A[j] == M // 양쪽 포인터를 모두 이동시키고 count를 증가 - 고유한 번호라고 했으므로 중복 x, 양쪽 포인터 모두 움직여도 된다.
     *         i++;
     *         j--;
     *         count++;
     * - i와 j가 만날 때까지 위를 반복(while (i < j)), i와 j가 만나면 count를 출력
     */

    /*
     * 각 풀이 비교
     * - 풀이 1: 이중 for문 전부 탐색
     *   - 메모리 16,904 KB, 시간 388 ms
     * - 풀이 2: 투 포인터, StringTokenizer 사용
     *   - 메모리 16,424 KB, 시간 204 ms
     * - 풀이 2-1: 투 포인터, stream 사용, 바로 int[]로
     *   - 메모리 17,224 KB, 시간 212 ms
     */

    /*
     * 시간 복잡도 관련
     * N 최대 15,000, 제한 시간 2초(2 * 10^8)이므로
     * -> O(nlogn) 알고리즘 사용 가능 - 기본 정렬 사용 가능 (log_2(15000) * 15000 ~~ 208090 = 2 * 10^5)
     */

    // 풀이 1 - 2개만 합하면 되므로 모든 원소에 대해서 탐색
    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 사용하지 않지만, 입력 조건 때문에 남겨 놓음
        int M = Integer.parseInt(br.readLine());
        int[] itemNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int count = 0;
        for (int i = 0; i < itemNumbers.length; i++) {
            for (int j = i + 1; j < itemNumbers.length; j++) { // 초기값 int j = i로 주면 실패
                if (itemNumbers[i] + itemNumbers[j] == M) {
                    count++;
                }
            }
        }

        System.out.println(count);

        br.close();
    }

    // 풀이 2(책 구현 코드) - 투 포인터 - 투 포인터 알고리즘을 구현할 때마다 이동 원칙은 조금씩 달라짐(P2018과 비교)
    // - 정렬했기 때문에 가능한 풀이 - 만날 때까지 한 번만 훑으면 된다.
    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        int count = 0;
        int i = 0;
        int j = N - 1;

        while (i < j) { // 정해놓은 투 포인터 이동 원칙에 따라 포인터를 이동하며 처리
            if (A[i] + A[j] < M) {
                i++;
            } else if (A[i] + A[j] > M) {
                j--;
            } else {
                count++;
                i++;
                j--;
            }
        }

        System.out.println(count);

        br.close();
    }

    // 풀이 2-1(책 구현 코드 보기 전 슈도코드 확인 후 구현) - 투 포인터
    public void solution2_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 사용하지 않지만, 입력 조건 때문에 남겨 놓음
        int M = Integer.parseInt(br.readLine());
        int[] itemNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(itemNumbers);

        int i = 0;
        int j = itemNumbers.length - 1;
        int count = 0;

        while (i < j) {
            if (itemNumbers[i] + itemNumbers[j] < M) {
                i++;
            } else if (itemNumbers[i] + itemNumbers[j] > M) {
                j--;
            } else {
                count++;
                i++;
                j--;
            }
        }

        System.out.println(count);

        br.close();
    }
}
