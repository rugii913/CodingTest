package programmers.lv0;

import java.util.stream.IntStream;

// lv0 첫 번째로 나오는 음수
public class Lessons181896 {

    // 풀이 1
    public int solution1(int[] num_list) {
        for (int i = 0; i < num_list.length; i++) {
            if (num_list[i] < 0) {
                return i;
            }
        }
        return -1;
    }

    // 풀이 2(다른 풀이 참고) - stream
    // orElse(~)가 int로 돌려주기 때문에, findFirst() 반환 타입이 optionalInt인 것을 신경쓰지 않아도 된다.
    // -> orElse(~) 자체가 null에 해당하는 부분을 처리하는 것이기 때문
    public int solution2(int[] num_list) {
        // return IntStream.range(0, num_list.length).filter(i -> num_list[i] < 0).findFirst().getAsInt();
        return IntStream.range(0, num_list.length).filter(i -> num_list[i] < 0).findFirst().orElse(-1);
    }
}
