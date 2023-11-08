package doIt.ch04Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Do it 코딩테스트 ch4-문제 021 버블 소트 프로그램 2(문제 제목: 버블 소트) // P1517
public class P1517 {
    // 첫번째 줄 입력: 데이터 개수 N, K번째 수 (1 <= N <= 5 * 10^5)
    // 두번째 줄 입력: N개의 정수 (-10^9 <= A[i] <= 10^9)
    // 출력: 버블 소트를 수행할 때 swap이 총 몇 번 발생하는가?
    
    /*
    * - 병합 정렬에서 각 영역을 병합하며 정렬하는 과정에서 원소들이 앞으로 이동한 거리만큼을 모두 더하면
    *   - 버블 정렬에서 모든 swap의 합과 같음
    * - 두 영역을 병합할 때, 뒤쪽 영역의 원소들만 더 앞의 위치로 갈 수 있음을 생각할 것
    *   - 각 영역 내부는 이미 정렬되어 있으므로 앞쪽 영역의 원소는 뒤로 갈 일만 남아있다.
    * */
    
    /*
     * 시간 복잡도 관련
     * N 최대 5 * 10^5, 제한 시간 2초(1 * 10^8) -> O(n^2) 알고리즘 사용 불가
     */

    // 풀이 x
    // - 이런 로직에서는 경우 원소의 인덱스가 바뀌지 않았으나 실제로는 swap이 발생한 경우가 누락됨
    // - ex. {3, 2, 1}을 정렬할 때, 2와 관련된 swap
    public void solutionx() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] numberStrings = br.readLine().split("\\s");
        NumberAndIndex[] numbersAndIndices = new NumberAndIndex[N];
        for (int i = 0; i < N; i++) {
            numbersAndIndices[i] = new NumberAndIndex(i, Integer.parseInt(numberStrings[i]));
        }

        Arrays.sort(numbersAndIndices);

        int swapCount = 0, difference;
        for (int index = 0; index < numbersAndIndices.length; index++) {
            difference = numbersAndIndices[index].originalIndex - index;
            if (difference > 0) {
                swapCount += difference;
            }
        }

        System.out.println(swapCount);

        br.close();
    }

    private static class NumberAndIndex implements Comparable<NumberAndIndex> {
        int originalIndex;
        int value;

        public NumberAndIndex(int originalIndex, int value) {
            this.originalIndex = originalIndex;
            this.value = value;
        }

        @Override
        public int compareTo(NumberAndIndex o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public static int[] A, tmp;
    public static long result;
    // 풀이 1(책) - P2751 병합 정렬 로직과 거의 같고, 앞으로 움직인 거리 합(= swap 횟수 합) count 로직 추가
    // cf. 두 영역을 병합할 때, 뒤쪽 영역의 원소들만 더 앞의 위치로 갈 수 있음을 생각할 것 - 각 영역 내부는 이미 정렬되어 있으므로 앞쪽 영역의 원소는 뒤로 갈 일만 남아있다.
    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        tmp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        result = 0;
        mergeSort(1, N);
        System.out.println(result);

        br.close();
    }

    public void mergeSort(int startIndex, int endIndex) {
        if (endIndex - startIndex < 1) {
            return;
        }

        int midIndex = startIndex + (endIndex - startIndex) / 2;
        mergeSort(startIndex, midIndex);
        mergeSort(midIndex + 1, endIndex);
        for (int i = startIndex; i <= endIndex; i++) {
            tmp[i] = A[i];
        }

        int k = startIndex;
        int index1 = startIndex;
        int index2 = midIndex + 1;

        while (index1 <= midIndex && index2 <= endIndex) {
            if (tmp[index1] > tmp[index2]) {
                A[k] = tmp[index2];
                result = result + index2 - k; // 뒤쪽 영역의 값이 더 작은 경우, (index2 - k)만큼 앞으로 swap이 필요한 것으로 봄 -> 해당 값을 더함
                k++;
                index2++;
            } else {
                A[k] = tmp[index1];
                k++;
                index1++;
            }
        }

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
