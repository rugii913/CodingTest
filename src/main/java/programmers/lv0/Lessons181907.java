package programmers.lv0;

// lv0 문자열 앞의 n글자 - 문자열 뒤의 n글자(Lessons181910)가 더 생각하기 피곤
public class Lessons181907 {

    // 풀이 1 - string.substring(~)
    public String solution1(String my_string, int n) {
        return my_string.substring(0, n);
    }
}
