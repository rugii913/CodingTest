package programmers.lv1;

import java.util.Arrays;

// lv1 평균 구하기
public class Lessons12944 {

    // 풀이 1
    public double solution1(int[] arr) {
        return Arrays.stream(arr).asDoubleStream().average().orElseThrow();
    }

    // 풀이 1-1(다른 풀이 참고)
    // - 굳이 풀이 1처럼 처음부터 DoubleStream으로 받아올 필요가 없음 - average() 자체가 OptionalDouble 반환
    // **(docs 중) API 참고 사항: The preferred alternative to this method is orElseThrow().
    // IDE에서도 isPresent() 검사가 없다고 노란 경고 띄워줌
    public double solution1_1(int[] arr) {
        return Arrays.stream(arr).average().getAsDouble();
    }

    // 풀이 2 - 속도는 훨씬 빠르다. 소요시간 1/100 수준
    public double solution2(int[] arr) {
        int sum = 0;
        int length = arr.length;

        for (int element : arr) {
            sum += element;
        }

        return (double) sum / length;
    }
}
