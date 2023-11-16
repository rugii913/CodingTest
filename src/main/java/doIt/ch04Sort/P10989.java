package doIt.ch04Sort;

import java.io.*;

// Do it 코딩테스트 ch4-문제 022 수 정렬하기 3(문제 제목: 수 정렬하기 3) // P10989
public class P10989 {
    // 첫번째 줄 입력: 수의 개수 N(1 <= N <= 10^7)
    // 2 ~ N + 1줄 입력: N개의 정수 (1 <= A[i] <= 10^4)

    /*
    * - 기수 정렬은 시간 복잡도가 가장 짧은 정렬 - O(kn), 정렬 필요 데이터 개수가 너무 많으면 기수 정렬 알고리즘 생각해볼 것
    *  -> 입력 수의 개수는 매우 크지만, 수의 크기(각 수의 자릿수 개수)는 작을 때 -> 기수 정렬 사용 가능
    * - 일반적인 기수 정렬은 우선순위 큐를 사용해 간단하게 구현할 수 있기만, 시간 복잡도를 느리게 하는 요소가 있으므로,
    *  -> 이 구현에서는 구간 합을 이용하는 방법으로 구현했음
    *  -> bucket 배열의 의미, bucket 배열로 현재 자리를 기준으로 정렬하는 방법을 이해할 것
    * */
    
    /*
     * ㅁ 기수 정렬 radix sort: 데이터의 자릿수를 바탕으로 비교해 데이터를 정렬
     *   ㅇ 시간복잡도 O(kn) - k는 데이터의 자릿수
     *   ㅇ 정렬 과정 개요
     *     - (값을 비교하지 않고) 값을 놓고 비교할 자릿수를 정한 다음 해당 자릿수만 비교
     *   ㅇ 정렬 과정:
     *     (1) 대상 데이터와 10개의 큐(FIFO 형태)를 준비
     *     (2) 1의 자릿수를 기준으로 데이터 저장
     *     (3) 1의 자릿수를 기준으로 데이터 정렬
     *     (4) (1의 자리에서 정렬된 순서를 유지하며) 10의 자릿수를 기준으로 데이터 저장
     *     (5) 10의 자릿수를 기준으로 데이터 정렬
     *     (6) ... 자릿수를 올리며 저장 -> 정렬 반복) ... *아래 자릿수에서 정렬된 순서를 유지하는 부분이 중요
     * */

    /*
     * 시간 복잡도 관련
     * N 최대 10^7, 제한 시간 3초(3 * 10^8)
     * -> O(n^2) 및 O(nlogn) 알고리즘 사용 불가 // 울프람 알파 10^7 * log_2(10^7) 계산 시 약 2.3 * 10^8 - nlogn으로 한 번 훑기만 해도 이미 2초 잡아먹음
     * cf. 기수 정렬 사용한 solution1_1로 제출했을 때에도, 2876ms라 아슬아슬함  
     */

    // 풀이 1(책)
    public static int[] A; // 책에서 쓸 데 없이 필드로 선언한 static 변수 - 메서드 local 변수로 선언해도 상관 없음
    public static long result; // 책에서 쓸 데 없이 만든 변수

    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        radix_sort(A, 5);
        for (int i = 0; i < N; i++) {
            bw.write(A[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private void radix_sort(int[] A, int max_size) {
        int[] output = new int[A.length]; // 임시 정렬을 위한 배열
        int jarisu = 1; // 현재 작업 중인 자릿수
        int count = 0;
        while (count != max_size) { // 최대 자릿수만큼 반복하기 - 이 문제에서는 5로 정해져 있다. -> count 0, 1, 2, 3, 4 -> while 안에서 자릿수는 1, 10, 100, 1000, 10000로 10배 씩
            int[] bucket = new int[10]; // 각 자릿수의 값 0 ~ 9에 몇 개의 데이터가 해당되는지 저장 -> 이후 합배열 형태로 바꿈
            
            for (int i = 0; i < A.length; i++) {
                bucket[(A[i] / jarisu) % 10]++; // (A[i] / jarisu) % 10 -> 이게 A[i]에 대해 현재 작업 중인 자릿수의 값
                // ex. 입력이 153, 64이고, while문 중 10의 자릿수에 대해 작업 중이라면 bucket[5]++, bucket[6]++
            }

            for (int i = 1; i < 10; i++) { // bucket을 합 배열 형태로 변경 -> 특정 원소가 들어갈 보조 배열 output의 (index + 1)이 된다. => *** 이 부분의 로직을 이해할 필요가 있음 ***
                bucket[i] += bucket[i - 1];
                // ex. 입력이 153, 64이고, while문 중 10의 자릿수에 대해 작업 중이라면 bucket[5] == 1, bucket[6] == 2
            }

            for (int i = A.length - 1; i >= 0; i--) { // 현재 자릿수를 기준으로 정렬하기 *** 순서를 보존하기 위해 A의 맨 끝에서부터 훑음 ***
                output[bucket[(A[i] / jarisu % 10)] - 1] = A[i];
                bucket[(A[i] / jarisu) % 10]--;
            }

            for (int i = 0; i < A.length; i++) { // 다음 자릿수에 대해서도 같은 작업을 하기 위해 A 배열에 output 데이터 저장
                A[i] = output[i];
            }

            jarisu = jarisu * 10; // 자릿수 증가시키기
            count++;
        }
    }

    // 풀이 1-1(위 풀이 답답한 부분들 변경)
    public void solution1_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] targetArray = new int[N];
        for (int i = 0; i < N; i++) {
            targetArray[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        int[] output = new int[targetArray.length]; // 임시 정렬을 위한 배열
        int position = 0;
        int weight;
        final int maxPosition = 5;

        while (position <= maxPosition) { // 최대 자릿수만큼 반복하기 - 이 문제에서는 5로 정해져 있다. -> count 0, 1, 2, 3, 4 -> while 안에서 자릿수는 1, 10, 100, 1000, 10000로 10배 씩
            int[] bucket = new int[10]; // 각 자릿수의 값 0 ~ 9에 몇 개의 데이터가 해당되는지 저장 -> 이후 합배열 형태로 바꿈
            weight = (int) Math.pow(10, position);

            for (int number : targetArray) {
                bucket[(number / weight) % 10]++; // (number / weight) % 10 -> 이게 A[i]에 대해 현재 작업 중인 자릿수의 값
                // ex. 입력이 153, 64이고, while문 중 10의 자릿수에 대해 작업 중이라면 bucket[5]++, bucket[6]++
            }

            for (int i = 1; i < 10; i++) { // bucket을 합 배열 형태로 변경 -> 특정 원소가 들어갈 보조 배열 output의 (index + 1)이 된다. => *** 이 부분의 로직을 이해할 필요가 있음 ***
                bucket[i] += bucket[i - 1];
                // ex. 입력이 153, 64이고, while문 중 10의 자릿수에 대해 작업 중이라면 bucket[5] == 1, bucket[6] == 2
            }

            for (int i = targetArray.length - 1; i >= 0; i--) { // 현재 자릿수를 기준으로 정렬하기 *** 순서를 보존하기 위해 A의 맨 끝에서부터 훑음 ***
                output[bucket[(targetArray[i] / weight % 10)] - 1] = targetArray[i];
                bucket[(targetArray[i] / weight) % 10]--;
            }

            // 다음 자릿수에 대해서도 같은 작업을 하기 위해 A 배열에 output 데이터 저장
            System.arraycopy(output, 0, targetArray, 0, targetArray.length);

            position++;
        }


        for (int i = 0; i < N; i++) {
            bw.write(targetArray[i] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
