package programmers.lv0;

import java.util.Arrays;
import java.util.stream.Collectors;

// lv0 이어 붙인 수
public class Lessons181928 {
    /*
    * 풀이 2 반복문 내에서 자릿수 처리 참고
    * */

    // 풀이 1 - 자릿수 처리가 고민돼서 CharSequence 사용했었음
    public int solution1(int[] num_list) {
        StringBuilder odd = new StringBuilder();
        StringBuilder even = new StringBuilder();

        for (int num : num_list) {
            if (num % 2 != 0) {
                odd.append(num);
            } else {
                even.append(num);
            }
        }

        return Integer.parseInt(odd.toString()) + Integer.parseInt(even.toString());
    }

    // 풀이 2(다른 풀이 참고) - 자릿수 문제를 반복문 안에서 깔끔하게 처리했음
    public int solution2(int[] num_list) {
        int odd = 0;
        int even = 0;

        for (int num : num_list) {
            if (num % 2 != 0) {
                odd *= 10;
                odd += num;
            } else {
                even *= 10;
                even += num;
            }
        }

        return odd + even;
    }

    // 풀이 3(다른 풀이 참고)
    public int solution3(int[] num_list) {
        return Integer.parseInt(Arrays.stream(num_list).filter(value -> value % 2 != 0).mapToObj(String::valueOf).collect(Collectors.joining()))
                + Integer.parseInt(Arrays.stream(num_list).filter(value -> value % 2 == 0).mapToObj(String::valueOf).collect(Collectors.joining()));
    }
}
