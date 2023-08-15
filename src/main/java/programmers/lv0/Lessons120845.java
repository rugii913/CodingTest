package programmers.lv0;

import java.util.stream.IntStream;

// lv0 주사위의 개수
public class Lessons120845 {

    // 풀이 1
    public int solution1(int[] box, int n) {
        return (box[0] / n) * (box[1] / n) * (box[2] / n);
    }

    // 풀이 2
    public int solution2(int[] box, int n) {
        int answer = 1;
        for (int edge : box) {
            answer *= edge / n;
        }
        return answer;
    }

    // 풀이 3(intStream.reduce(~) 관련 블로그 참고)
    public int solution3(int[] box, int n) {
        return IntStream.range(0, box.length).reduce(1, (a, b) -> a * (box[b] / n));
        // 람다식 쪽 디버그해보면 알겠지만 아래처럼 풀 수가 없다.
        // return IntStream.range(0, box.length).reduce((a, b) -> (box[a] / n) * (box[b] / n)).getAsInt();
    }
}
