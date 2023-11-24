package programmers.lv0;

// lv0 특정 문자열로 끝나는 가장 긴 부분 문자열 찾기
public class Lessons181872 {
    // 말이 좀 이상하다.
    // 맨 뒤에 있는 pat를 문자열을 찾아내면 된다.
    // Lessons181871에서 본 것과 마찬가지로 - lastIndexOf(~)도 찾는 대상이 char가 아니라 String이라는 점

    // 풀이 1
    public String solution1(String myString, String pat) {
        int lastPatIndex = myString.lastIndexOf(pat);
        return myString.substring(0, lastPatIndex + pat.length());
    }

    // 풀이 1-1(다른 풀이 참고) - 이렇게 해도 말이 된다.
    public String solution1_1(String myString, String pat) {
        int lastPatIndex = myString.lastIndexOf(pat);
        return myString.substring(0, lastPatIndex).concat(pat);
    }
}
