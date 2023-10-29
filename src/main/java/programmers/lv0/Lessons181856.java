package programmers.lv0;

import java.util.stream.IntStream;

// lv0 배열 비교하기
public class Lessons181856 {
    // Integer.compare(~)

    // 풀이 1
    public int solution1(int[] arr1, int[] arr2) {
        int order;
        if (arr1.length > arr2.length) {
            order = 1;
        } else if (arr1.length < arr2.length) {
            order = -1;
        } else {
            /* IDE 권장으로 변경
            int arr1Sum = getSum(arr1);
            int arr2Sum = getSum(arr2);

            if (arr1Sum > arr2Sum) {
                order = 1;
            } else if (arr1Sum < arr2Sum) {
                order = -1;
            } else {
                order = 0;
            }
            */
            order = Integer.compare(getSum(arr1), getSum(arr2));
        }
        return order;
    }

    private int getSum(int[] intArray) {
        int sum = 0;
        for (int number : intArray) {
            sum += number;
        }
        return sum;
    }

    // 풀이 1-1(다른 풀이 참고)
    public int solution1_1(int[] arr1, int[] arr2) {
        if (arr1.length < arr2.length) {
            return -1;
        } else if (arr1.length > arr2.length) {
            return 1;
        }

        int arr1Sum = 0, arr2Sum = 0;
        for (int i = 0; i < arr1.length; i++) {
            arr1Sum += arr1[i];
            arr2Sum += arr2[i];
        }
        return Integer.compare(arr1Sum, arr2Sum);
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(int[] arr1, int[] arr2) {
        int answer = Integer.compare(arr1.length, arr2.length);

        if (answer == 0) {
            answer = Integer.compare(IntStream.of(arr1).sum(), IntStream.of(arr2).sum());
        }

        return answer;
    }
}
