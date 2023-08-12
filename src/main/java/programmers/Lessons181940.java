package programmers;

// lv0 문자열 곱하기
public class Lessons181940 {
    /*
    * string.repeat(~) 메서드
    * */

    // 풀이 1
    public String solution1(String my_string, int k) {
        StringBuffer sb = new StringBuffer(my_string);
        for (int i = 0; i < k - 1; i++) {
            sb.append(my_string);
        }
        return sb.toString();
    }

    // 풀이 2 - 결과를 알기 때문에 capacity를 미리 잡아봤음, 1보다 미세하게 빠름
    // capacity 안 잡고 new StringBuffer();로 생성해도 그렇게 크게 차이가 없음
    public String solution2(String my_string, int k) {
        StringBuffer sb = new StringBuffer(my_string.length() * k);
        for (int i = 0; i < k; i++) {
            sb.append(my_string);
        }
        return sb.toString();
    }

    // 풀이 3(다른 풀이 참고) - string.repeat(~)가 2배 정도 빠른 듯하다.
    public String solution3(String my_string, int k) {
        return my_string.repeat(k);
    }
}
