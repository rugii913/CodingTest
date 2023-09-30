package programmers.lv0;

import java.util.Arrays;

// lv0 조건에 맞게 수열 변환하기 1
public class Lessons181882 {

    // 풀이 1
    public int[] solution1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 50) {
                if (arr[i] % 2 == 0) {
                    arr[i] /= 2;
                }
            } else {
                if (arr[i] % 2 != 0) {
                    arr[i] *= 2;
                }
            }
        }
        return arr;
    }

    // 풀이 1-1
    public int[] solution1_1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 50 && arr[i] % 2 == 0) {
                arr[i] /= 2;
            } else if (arr[i] < 50 && arr[i] % 2 != 0) {
                arr[i] *= 2;
            }
        }
        return arr;
    }

    // 풀이 1-2
    public int[] solution1_2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int element = arr[i];

            if (arr[i] >= 50) {
                arr[i] = element % 2 == 0 ? element / 2 : element;
            } else {
                arr[i] = element % 2 == 0 ? element : element * 2;
            }
        }
        return arr;
    }

    // 풀이 2
    public int[] solution2(int[] arr) {
        return Arrays.stream(arr)
                .map(element -> element >= 50 ?
                        (element % 2 == 0 ? element / 2 : element)
                        : (element % 2 == 0 ? element : element * 2)
                )
                .toArray();
    }
}
