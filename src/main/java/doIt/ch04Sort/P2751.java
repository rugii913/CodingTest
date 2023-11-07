package doIt.ch04Sort;

import java.io.*;
import java.util.StringTokenizer;

// Do it 코딩테스트 ch4-문제 020 수 정렬하기 2(문제 제목: 수 정렬하기 2) // P2751
public class P2751 {
    // 첫번째 줄 입력: 데이터 개수 N, K번째 수 (1 <= N <= 10^6)
    // 2줄 ~ N + 1줄 입력: N개의 수 (-10^6 <= ? <= 10^6)

    /*
     * ㅁ 병합 정렬 merge sort: 분할 정복(divide and conquer) 방식을 사용해 데이터를 분할하고 분할한 집합을 정렬하며 합침
     *   ㅇ 시간복잡도 O(nlogn)
     *   ㅇ 정렬 과정 개요
     *     - 가장 작은 데이터 집합으로 분할 -> 병합하면서 정렬 -> ... -> 병합하면서 정렬
     *   ㅇ 두 영역을 병합하는 원리: 코테 정렬 관련 문제에서 자주 등장함
     *     - 두 영역을 병합하기 위해 투 포인터 개념을 사용
     *     - 영역 1의 포인터와 영역 2의 포인터의 값을 비교하여 작은 값을 결과 배열에 추가하고 포인터를 영역 내에서 오른쪽으로 한 칸 이동시킴
     *   ㅇ 정렬 과정: ... 생략 ...
     * */

    /*
     * 시간 복잡도 관련
     * N 최대 10^6, 제한 시간 2초(2 * 10^8)이므로
     * -> O(n^2) 알고리즘 사용 불가
     */

    public static int[] A, tmp;
    public static long result;
    // 풀이 1(책)
    public void solution1() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(1, N); // 병합 정렬 수행
        for (int i = 1; i <= N; i++) {
            bw.write(A[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private void mergeSort(int startIndex, int endIndex) {
        if (endIndex - startIndex < 1) {
            return;
        }

        int midIndex = startIndex + (endIndex - startIndex) / 2;

        // 재귀 함수 형태로 구현
        mergeSort(startIndex, midIndex);
        mergeSort(midIndex + 1, endIndex);
        for (int i = startIndex; i <= endIndex; i++) {
            tmp[i] = A[i]; // startIndex부터 endIndex의 값까지 tmp[]에 한 번 복사 - 이 tmp 값을 비교하고, 정렬된 값을 A에 넣을 예정
        }
        int k = startIndex;
        int index1 = startIndex;
        int index2 = midIndex + 1;
        while (index1 <= midIndex && index2 <= endIndex) { // 두 영역을 병합하는 로직
            // 양쪽 영역의 index가 가리키는 값을 비교해 더 작은 수를 선택해 배열에 저장
            if (tmp[index1] > tmp[index2]) {
                A[k] = tmp[index2];
                k++;
                index2++;
            } else {
                A[k] = tmp[index1];
                k++;
                index1++;
            }
        }
        
        // 한 쪽 그룹이 모두 선택된 후 남아 있는 값 정리하기
        while (index1 <= midIndex) {
            A[k] = tmp[index1];
            k++;
            index1++;
        }
        while (index2 <= endIndex) {
            A[k] = tmp[index2];
            k++;
            index2++;
        }
    }
}
