package programmers.lv0;

import java.util.stream.IntStream;

// lv0 배열의 길이에 따라 다른 연산하기
public class Lessons181854 {
    // 풀이 1-2가 간편

    // 풀이 1
    public int[] solution1(int[] arr, int n) {
        if (arr.length % 2 != 0) {
            for (int i = 0; i < arr.length; i += 2) {
                arr[i] += n;
            }
        } else {
            for (int i = 1; i < arr.length; i += 2) {
                arr[i] += n;
            }
        }
        return arr;
    }

    // 풀이 1-1(다른 풀이 참고)
    public int[] solution1_1(int[] arr, int n) {
        for (int i = arr.length % 2 == 0 ? 1 : 0; i < arr.length; i += 2) {
            arr[i] += n;
        }

        return arr;
    }

    // 풀이 1-2(다른 풀이 참고) - 배열의 길이에 따라서 알아서 홀짝이 걸러진다. - 길이가 짝수면 맨 마지막 인덱스가 홀수, 길이가 홀수면 맨 마지막 인덱스가 짝수
    public int[] solution1_2(int[] arr, int n) {
        for (int i = arr.length - 1; i >= 0; i -= 2) {
            arr[i] += n;
        }
        return arr;
    }

    // 풀이 2(다른 풀이 참고) - intStream
    public int[] solution2(int[] arr, int n) {
        return IntStream.range(0, arr.length).map(i -> arr[i] + (i % 2 == (arr.length % 2 == 0 ? 1 : 0) ? n : 0)).toArray();
    }

    // 풀이 2-1(다른 풀이 참고) - IntStream.iterate(~), 길이가 홀수면 짝수부터 시작, 짝수면 홀수부터 시작
    public int[] solution2_1(int[] arr, int n) {
        IntStream.iterate((arr.length + 1) % 2, i -> i < arr.length, i -> i + 2)
                .forEach(i -> arr[i] += n);
        return arr;
    }
}
