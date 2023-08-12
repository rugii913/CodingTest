package programmers.lv0;

// lv0 문자열로 변환
public class Lessons181845 {

    // 풀이 1 - Integer.toString(~)
    public String solution1(int n) {
        return Integer.toString(n);
    }

    // 풀이 2(다른 풀이 참고) - String.valueOf(~)
    public String solution2(int n) {
        return String.valueOf(n);
    }

    // 풀이 3(다른 풀이 참고) - String 덧셈 연산 꼼수 !성능은 아주 좋지 않음 
    public String solution3(int n) {
        return "" + n;
    }
}
