package programmers.lv0;

import java.util.Arrays;
import java.util.stream.IntStream;

// lv0 뒤에서 5등 위로
public class Lessons181852 {
    /*
    * - intStream.sort()는 오름차순
    * - 굳이 stream을 사용한다면 intStream.skip(~) 메서드
    * */

    // 풀이 1 - 삽입 정렬 스타일
    public int[] solution1(int[] num_list) {
        for (int i = 0; i < num_list.length - 1; i++) {
            for (int j = i; j >= 0; j--) {
                if (num_list[j + 1] < num_list[j]) {
                    int tmp = num_list[j + 1];
                    num_list[j + 1] = num_list[j];
                    num_list[j] = tmp;
                }
            }
        }
        return Arrays.copyOfRange(num_list, 5, num_list.length);
    }

    // 풀이 01 - stream 생각해본 것
    public int[] solution01(int[] num_list) {
        return IntStream.range(5, num_list.length)
                .map(index -> Arrays.stream(num_list).sorted().toArray()[index])
                .toArray();
    }

    // 풀이 02(다른 풀이 참고) - intStream.skip(~)
    public int[] solution02(int[] num_list) {
        return Arrays.stream(num_list).sorted().skip(5).toArray();
    }
}
