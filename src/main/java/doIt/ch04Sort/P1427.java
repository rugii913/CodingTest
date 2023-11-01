package doIt.ch04Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// Do it 코딩테스트 ch4-문제 017 내림차순으로 자릿수 정렬하기(문제 제목: 소트인사이드) // P1427
public class P1427 {
    /*
     * ㅁ 선택 정렬 selection sort: 대상 데이터에서 최대나 최소 데이터를 데이터가 나열된 순으로 찾아가며 선택하는 방식
     *   ㅇ 시간복잡도 O(n^2)
     *     - 구현이 복잡한데 효율적이지도 않으므로, 코딩 테스트에서는 많이 사용 x
     *     - 선택 정렬 자체를 묻는 문제는 잘 나오지 않지만, 작동 원리를 응용하는 문제는 나올 수도 있다.
     *   ㅇ 정렬 과정 개요
     *     - (오름차순) 최솟값, (내림차순) 최댓값을 찾고 남은 정렬 부분의 가장 앞에 있는 데이터와 swap
     *   ㅇ 정렬 과정
     *     (1) 정렬이 보장되지 않은 부분에서 최솟값 또는 최댓값을 찾는다.
     *     (2) 정렬이 보장되지 않은 부분의 가장 앞에 있는 데이터와 선택된 데이터를 swap 한다.
     *     (3) 가장 앞 데이터의 위치를 변경(index++) 정렬이 보장되지 않은 부분의 범위를 축소한다.
     *     (4) 전체 데이터 크기만큼 index가 커질 때까지 = 정렬이 보장되지 않은 영역이 없을 때까지 반복
     * */

    /*
    * 시간 복잡도 관련
    * - 커봐야 9개의 수를 정렬하는 것이므로 O(n^2)를 사용해도 아무 상관이 없음
    * - 선택 정렬을 사용해야만 하는 문제는 아님
    * */

    // 풀이 1
    public void solution1() {
        Scanner sc = new Scanner(System.in);

        String[] split = sc.next().split("");

        Integer[] digits = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            digits[i] = Integer.valueOf(split[i]);
        }

        Arrays.sort(digits, Comparator.reverseOrder());

        int result = 0;
        for (int j : digits) {
            result = result * 10 + j;
        }

        System.out.println(result);

        sc.close();
    }

    // 풀이 2(다른 풀이 참고 수정)
    public void solution2() {
        Scanner sc = new Scanner(System.in);

        char[] charArray = sc.next().toCharArray();
        Arrays.sort(charArray);

        StringBuilder sb = new StringBuilder();
        for (int i = charArray.length - 1; i >= 0; i--) {
            sb.append(charArray[i]);
        }

        System.out.println(sb);

        sc.close();
    }

    // 풀이 3(책) - selection sort 사용
    public void solution3() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int[] A = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            A[i] = Integer.parseInt(str.substring(i, i + 1));
        }

        for (int i = 0; i < str.length(); i++) {
            int Max = i;
            for (int j = i + 1; j < str.length(); j++) {
                if (A[j] > A[Max]) {
                    Max = j;
                }
            }

            if (A[i] < A[Max]) {
                int temp = A[i];
                A[i] = A[Max];
                A[Max] = temp;
            }
        }

        for (int i = 0; i < str.length(); i++) {
            System.out.print(A[i]);
        }
    }

    // 풀이 3(책 풀이 답답한 부분 수정)
    public void solution3_1() {
        Scanner sc = new Scanner(System.in);

        char[] charArray = sc.next().toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            int indexOfMax = i;
            for (int j = i + 1; j < charArray.length; j++) {
                if (charArray[j] > charArray[indexOfMax]) {
                    indexOfMax = j;
                }
            }

            if (i < indexOfMax) {
                char temp = charArray[i];
                charArray[i] = charArray[indexOfMax];
                charArray[indexOfMax] = temp;
            }
        }

        System.out.println(charArray);
        // cf. System.out.println(char[])는 System.out.println(Arrays.toString(charArray))와는 다름
        //     - 마치 System.out.println(new String(charArray))처럼 동작함
    }
}
