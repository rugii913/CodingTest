package programmers.lv0;

// 원하는 문자열 찾기 - ignore case
public class Lessons181878 {

    // 풀이 1 - 대소문자 구분 x를 위해 모두 소문자로 바꿈
    public int solution1(String myString, String pat) {
        return myString.toLowerCase().contains(pat.toLowerCase()) ? 1 : 0;
    }
}
