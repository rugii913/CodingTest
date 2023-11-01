package doIt.ch04Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

// Do it 코딩테스트 ch4-문제 018 ATM 인출 시간 계산하기(문제 제목: ATM) // P11399
public class P11399 {
    // 첫번째 줄 입력: 사람의 수 N (1 <= N <= 1,000)
    // 두번째줄 입력: 각 사람이 돈을 인출하는 데 걸리는 시간 Pi (1 <= Pi <= 1,000)

    // 일종의 그리디 방식? - 시간이 가장 적게 걸리는 사람이 먼저 작업할 수 있도록 순서를 정하기
    //   -> 결론적으로는 그냥 평범한 오름차순 정렬 - 꼭 삽입 정렬을 사용할 필요 없음
    //   cf. CPU 스케줄링 알고리즘 - waiting time에 유리한 SJF 알고리즘
    //   -> 약간 삽입 정렬과 관계된 방식으로 - 이미 정렬이 보장된 영역에 어떤 데이터 하나를 삽입하는데, 그 삽입된 데이터가 전체 구간 합에 끼칠 영향을 생각해볼 수 있을 것 같다.

    /*
     * ㅁ 삽입 정렬 insertion sort: 이미 정렬된 데이터 범위에 정렬되지 않은 데이터를 적절한 위치에 삽입시켜 정렬하는 방식
     *   ㅇ 시간복잡도 O(n^2)
     *      - 느린 편이지만 구현하기 쉽다.
     *   ㅇ 정렬 과정 개요
     *     - 현재 데이터를 정렬이 보장된 범위 내의 적절한 위치에 삽입
     *   ㅇ 정렬 과정
     *     (1) 현재 index에 있는 데이터 값을 선택
     *     (2) 현재 선택한 데이터가 정렬이 보장된 데이터 영역에 삽입될 위치를 탐색 ***여기서 이진 탐색(binary search)와 같은 탐색 알고리즘을 사용하면 시간 복잡도를 줄일 수 있다.
     *     (3) 삽입 위치부터 index에 있는 위치까지 shift 연산을 수행
     *     (4) 삽입 위치에 현재 선택한 데이터를 삽입하고, 다음 index에 있는 데이터 값을 선택(index++)
     *     (5) 전체 데이터의 크기만큼 index가 커질 때까지 = 더 선택할 데이터가 없을 때까지 = 정렬이 보장되지 않은 영역이 없을 때까지 반복
     * */

    // 풀이 1
    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] timesString = br.readLine().split(" ");
        int[] times = new int[N];
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(timesString[i]);
        }

        Arrays.sort(times);

        int[] waitingTime = new int[N]; // waiting time은 prefix sum
        waitingTime[0] = times[0];
        int waitingTimeSum = times[0];
        for (int i = 1; i < N; i++) {
            waitingTime[i] = times[i] + waitingTime[i - 1];
            waitingTimeSum += waitingTime[i];
        }

        System.out.println(waitingTimeSum);

        br.close();
    }

    // 풀이 1x - stream 시도 했는데 어떻게 전개해나갈지 모르겠다.
    public void solution1x() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] timesString = br.readLine().split(" ");

        System.out.println(Arrays.stream(timesString).mapToInt(Integer::parseInt).sorted().reduce(0, (prev, current) -> prev * 2 + current));

        br.close();
    }

    // 풀이 2(책)
    public void solution2() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        int[] S = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        for (int i = 1; i < N; i++) { // 삽입 정렬
            int insertPoint = i;
            int insertValue = A[i];

            for (int j = i - 1; j >= 0; j--) {
                if (A[j] < A[i]) { // 정렬해야될 필요가 없으면 그 자리에 그냥 두고 break; insertPoint에 insertValue 넣고 다음 i로
                    insertPoint = j++;
                    break;
                }
                if (j == 0) {
                    insertPoint = 0;
                }
            }
            for (int j = i; j > insertPoint; j--) { // shift 연산 - 별로 좋지 않은 듯
                A[j] = A[j - 1];
            }
            A[insertPoint] = insertValue;
        }

        S[0] = A[0]; // 합 배열 만들기
        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + A[i];
        }

        int sum = 0; // 합 배열 총합 구하기
        for (int i = 0; i < N; i++) {
            sum = sum + S[i];
        }

        System.out.println(sum);
        sc.close();
    }

    // 풀이 2-1 - 풀이 2 답답해서 삽입 정렬 부분만 다시 구현
    public void solution2_1() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        int[] S = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        for (int i = 1; i < N; i++) { // 삽입 정렬
            for (int j = i; j > 0 ; j--) {
                if (A[j - 1] > A[j]) {
                    int tmp = A[j - 1];
                    A[j - 1] = A[j];
                    A[j] = tmp;
                } else {
                    break;
                }
            }
        }

        S[0] = A[0];
        int sum = A[0];
        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + A[i];
            sum += S[i];
        }

        System.out.println(sum);
        sc.close();
    }
}
