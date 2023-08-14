package programmers.lv0;

// lv0 부분 문자열인지 확인하기
public class Lessons181843 {
    /*
    * - string.indexOf(~) 메서드도 있다.
    * */

    // 풀이 1
    public int solution1(String my_string, String target) {
        return my_string.contains(target) ? 1 : 0;
    }

    // 풀이 2(다른 풀이 참고) - indexOf(~) 메서드도 가능하다.
    public int solution2(String my_string, String target) {
        return my_string.indexOf(target) > -1 ? 1 : 0;
    }
}
