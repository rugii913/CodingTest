package programmers.lv0;

import java.util.Arrays;

// lv0 369게임
public class Lessons120891 {
    // 괜히 String이나 char로 다루는 것보다 % 연산으로 int로 다루는 게 낫다.

    // 풀이 1 +6점??
    public int solution1(int order) {
        int count = 0;

        while (order > 0) {
            int digit = order % 10;
            if (digit != 0 && digit % 3 == 0) {
                count++;
            }
            order /= 10;
        }

        return count;
    }

    // 풀이 2
    public int solution2(int order) {
        int count = 0;

        char[] chars = String.valueOf(order).toCharArray();
        for (char ch : chars) {
            if (ch == '3' || ch == '6' || ch == '9') {
                count++;
            }
        }

        return count;
    }

    // 풀이 2-1(다른 풀이 참고)
    public int solution2_1(int order) {
        int count = 0;

        String stringOrder = String.valueOf(order);
        for (int i = 0; i < stringOrder.length(); i++) {
            char ch = stringOrder.charAt(i);
            if (ch == '3' || ch == '6' || ch == '9') {
                count++;
            }
        }

        return count;
    }

    // 풀이 3(다른 풀이 참고) - stream
    public int solution3(int order) {
        return (int) Arrays.stream(String.valueOf(order).split(""))
                .map(Integer::parseInt).filter(i -> i % 3 == 0 && i != 0).count();
    }
}
