package programmers.lv1;

import java.util.stream.LongStream;

// lv1 x만큼 간격이 있는 n개의 숫자
public class Lessons12954 {

    // 풀이 1 - LongStream 사용 - rangeClosed(~), map(~) // 2.89ms ~ 14.89ms
    public long[] solution1(int x, int n) {
        return LongStream.rangeClosed(1, n).map(sequentialLong -> x * sequentialLong).toArray();
    }

    // 풀이 1-1 - LongStream 사용 - iterate(~), limit(~) // 3.20ms ~ 10.03ms
    public long[] solution1_1(int x, int n) {
        return LongStream.iterate(x, prevValue -> prevValue + x).limit(n).toArray();
    }

    // 풀이 2 - while // 0.01ms ~ 0.07ms
    public long[] solution2(int x, int n) {
        long[] answer = new long[n];

        answer[0] = x;
        int index = 1;
        while (index < n) {
            answer[index] = answer[index - 1] + x;
            index++;
        }

        return answer;
    }

    // 풀이 2-1 - for // 0.01ms ~ 0.06ms
    public long[] solution2_1(int x, int n) {
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            answer[i] = (long) x * (i + 1);
        }

        return answer;
    }
}
