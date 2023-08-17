package programmers.lv0;

import java.util.Arrays;

// lv0 조건에 맞게 수열 변환하기 3
public class Lessons181835 {
    /*
    * - enhanced for에서 배열의 값을 변경하지 못하는 점에 유의
    *   -> private int[] addForAll(int[] intArray, int k) {
    *       for (int i : intArray) {
    *            i += k;
    *        }
    *        return intArray;
    *        }
    *       이런 식으로 사용할 수가 없다.
    * */

    // 풀이 1 - 메서드 분리
    public int[] solution1(int[] arr, int k) {
        return k % 2 != 0 ? multiplyForAll(arr, k) : addForAll(arr, k);
    }
    private int[] addForAll(int[] intArray, int k) {
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] += k;
        }
        return intArray;
    }
    private int[] multiplyForAll(int[] intArray, int k) {
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] *= k;
        }
        return intArray;
    }

    // 풀이 2(다른 풀이 참고) - stream
    public int[] solution2(int[] arr, int k) {
        return Arrays.stream(arr).map(operand -> k % 2 == 0 ? operand + k : operand * k).toArray();
    }
}
