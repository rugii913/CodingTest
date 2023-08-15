package programmers.lv0;

import java.util.stream.IntStream;

// lv0 접두사인지 확인하기
public class Lessons181906 {
    
    /*
    * - 분기를 하나 더 만들어서 length() 관련으로 걸러야하는 substring을 쓰기보다는 startsWith(~) indexOf(~)를 사용하는 게 낫겠다.
    * */

    // 풀이 1
    public int solution1(String my_string, String is_prefix) {
        if (my_string.length() < is_prefix.length()) {
            return 0;
        } else {
            return my_string.substring(0, is_prefix.length()).equals(is_prefix) ? 1 : 0;
        }
    }

    // 풀이 2(다른 풀이 참고) - startsWith(~), indexOf(~)
    public int solution2(String my_string, String is_prefix) {
        return my_string.startsWith(is_prefix) ? 1 : 0;
        // return my_string.indexOf(is_prefix) == 0 ? 1 : 0;
    }

    // 풀이 3(다른 풀이 참고) - 스트림 사용
    public int solution3(String my_string, String is_prefix) {
        return IntStream.range(0, my_string.length())
                .mapToObj(i -> my_string.substring(0, i))
                .anyMatch(substr -> substr.equals(is_prefix)) ? 1 : 0;
    }
}
