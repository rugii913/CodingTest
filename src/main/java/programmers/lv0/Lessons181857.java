package programmers.lv0;

import java.util.Arrays;

// lv0 배열의 길이를 2의 거듭제곱으로 만들기
public class Lessons181857 {

    // 풀이 1
    public int[] solution1(int[] arr) {
        int resultLength = 1;
        while (arr.length > resultLength) {
            resultLength *= 2;
        }

        int[] result = new int[resultLength];
        System.arraycopy(arr, 0, result, 0, arr.length);
        return result;
    }

    // 풀이 1-1(다른 풀이 참고) - Arrays.copyOf도 original보다 큰 부분은 0으로 채워 줌
    public int[] solution1_1(int[] arr) {
        int length = 1;
        while (length < arr.length) {
            length *= 2;
        }

        return Arrays.copyOf(arr, length); // Arrays.copyOf도 original보다 큰 부분은 0으로 채워 줌
    }

    // 풀이 1-2(다른 풀이 참고)
    public int[] solution1_2(int[] arr) {
        return Arrays.copyOf(arr, (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2))));
    }
}
