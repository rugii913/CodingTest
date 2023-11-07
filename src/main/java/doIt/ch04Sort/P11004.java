package doIt.ch04Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Do it 코딩테스트 ch4-문제 019 K번째 수 구하기(문제 제목: K번째 수) // P11004
public class P11004 {
    // 첫번째 줄 입력: 데이터 개수 N, K번째 수 (1 <= N <= 5,000,000, 1 <= K <= N)
    // 두번째줄 입력: N개의 수 (-10^9 <= Ai <= 10^9)

    /*
     * ㅁ 퀵 정렬 quick sort: pivot을 정하고 해당 값보다 작은 데이터와 큰 데이터로 분류하는 것을 반복
     *   ㅇ 시간복잡도 O(nlogn)
     *      - pivot(기준값)을 어떻게 선정하는지가 시간 복잡도에 많은 영향을 미침
     *      - 비교적 준수한 시간복잡도
     *   ㅇ 정렬 과정 개요
     *     - pivot을 중심으로 데이터를 2개의 집합으로 나누면서 정렬
     *     - 재귀적으로 divide and conquer - https://gmlwjd9405.github.io/2018/05/10/algorithm-quick-sort
     *   ㅇ 정렬 과정
     *     (1) 데이터를 분할하는 pivot을 설정
     *     (2) pivot을 기준으로 다음 과정을 거쳐 데이터를 2개의 집합을 분리
     *       (a) start가 가리키는 데이터가 pivot이 가리키는 데이터보다 작으면 start를 오른쪽으로 1칸 이동
     *       (b) end가 가리키는 데이터가 pivot이 가리키는 데이터보다 크면 end를 왼쪽으로 1칸 이동
     *       (c) start가 가리키는 데이터가 pivot이 가리키는 데이터보다 크고 && end가 가리키는 데이터가 pivot이 가리키는 데이터보다 작으면
     *           - start, end가 가리키는 데이터를 swap하고, start는 오른쪽, end는 왼쪽으로 1칸씩 이동
     *       (d) start와 end가 만날 때까지 a ~ c를 반복
     *       (e) start와 end가 만난 지점에서 가리키는 데이터와 pivot이 가리키는 데이터를 비교
     *           - pivot이 가리키는 데이터가 더 크면, 만난 지점의 오른쪽에 pivot이 가리키는 데이터를 삽입
     *           - pivot이 가리키는 데이터가 더 작으면, 만난 지점의 왼쪽에 pivot이 가리키는 데이터를 삽입
     *     (3) 분리 집합에서 각각 다시 pivot을 정한다.
     *     (4) 분리 집합이 1개 이하가 될 때까지 1 ~ 3을 반복
     * */

    /*
     * 시간 복잡도 관련
     * N 최대 5 * 10^6, 제한 시간 2초(2 * 10^8)이므로
     * -> O(n^2) 알고리즘 사용 불가, 꼭 퀵 정렬을 사용할 필요는 없고 O(nlogn)인 다른 정렬 알고리즘 사용 가능할 수도
     * -> 단 이 문제에서는 시간 제한이 빡빡하게 주어져서 전부 정렬한 뒤 K번째 수를 가져오는 방식보다는, 정렬하지 않아도 되는 영역은 생략.
     */

    // 풀이 1(책)
    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(A, 0, N - 1, K - 1); // cf. 문제 조건이 "앞에서부터 K번째 수"여서 index 0을 첫번째 수로 따져야 함
        System.out.println(A[K - 1]);
    }

    private void quickSort(int[] A_targetArray, int S_startIndex, int E_endIndex, int K_targetIndex) {
        if (S_startIndex < E_endIndex) {
            // partition(~)메서드를 호출하여, 구분을 원하는 영역에 특정 값을 pivot value로 하여, pivot value보다 작은 값 | 큰 값으로 구분한다.
            // - 그리고 그 기준점이 된 pivot value의 index를 반환받아 저장한다.
            int pivot = partition(A_targetArray, S_startIndex, E_endIndex);

            if (pivot == K_targetIndex) {
                // targetIndex가 pivot index와 같으면 종료
                return;
            } else if (K_targetIndex < pivot) {
                // targetIndex가 pivot보다 작으면, pivot 인덱스의 왼쪽 그룹에 대해 정렬 수행
                quickSort(A_targetArray, S_startIndex, pivot - 1, K_targetIndex);
            } else {
                // targetIndex가 pivot보다 크면, pivot 인덱스의 오른쪽 그룹에 대해 정렬 수행
                quickSort(A_targetArray, pivot + 1, E_endIndex, K_targetIndex);
            }
        }
    }

    private int partition(int[] A_targetArray, int S_startIndex, int E_endIndex) {
        if (S_startIndex + 1 == E_endIndex) { // 데이터가 두 개 뿐인 경우 바로 비교해서 정렬, 반환값은 영역의 마지막 인덱스
            if (A_targetArray[S_startIndex] > A_targetArray[E_endIndex]) {
                swap(A_targetArray, S_startIndex, E_endIndex);
            }
            return E_endIndex;
        }

        // 데이터가 두 개보다 많은 경우
        int M = (S_startIndex + E_endIndex) / 2; // 기준이 될 인덱스 M을 정함 - 영역 내의 요소가 홀수 개라면 정 가운데, 짝수 개라면 인덱스가 더 작은 쪽으로 하나 치우참
        swap(A_targetArray, S_startIndex, M); // 기준 인덱스 M 요소의 값을 영역의 맨 앞으로 이동하기
        int pivotValue = A_targetArray[S_startIndex]; // 영역의 맨 앞의 값(원래 인덱스 M 요소의 값이었던 것)을 pivot(기준값)으로 정의
        int i = S_startIndex + 1, j = E_endIndex; // 영역의 맨 앞의 바로 뒤부터 끝까지 정의함

        while (i <= j) {
            while (j >= S_startIndex + 1 && pivotValue < A_targetArray[j]) { // pivot보다 작은 수가 나올 때까지 j--
                j--;
            }
            while (i <= E_endIndex && pivotValue > A_targetArray[i]) { // pivot보다 큰 수가 나올 때까지 i++
                i++;
            }
            if (i <= j) { // i <= j인지 다시 확인 후 맞으면 swap - index i에 있는데 pivot보다 큰 요소와 index j에 있는데 pivot보다 작은 요소를 swap
                swap(A_targetArray, i++, j--);
            }
        }

        // pivot value을 나눠진 두 그룹의 경계 index j에 저장
        // - 위 while 작업이 끝나면, 대상 영역 중 index j보다 더 큰 인덱스에 있는 요소의 값은 pivot보다 크기 때문
        // - 최종 탈출할 때 j에 있는 값은 pivot보다 작을 것이므로 앞 pivot과 위치를 바꿀 수 있다.
        // => 반환되는 값은 pivot value가 있는 index
        A_targetArray[S_startIndex] = A_targetArray[j];
        A_targetArray[j] = pivotValue;
        return j;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
