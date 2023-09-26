package programmers.lv0;

// lv0 특수문자 출력하기
public class Lessons181948 {

    // 풀이 1 - escape sequence
    public void solution1() {
        System.out.print("!@#$%^&*(\\'\"<>?:;");
    }

    // 풀이 1-1 - 참고 format string syntax Formatter.java 중 설명
    public void solution1_1() {
        System.out.printf("!@#$%c^&*(%c'%c<>?:;", '%', '\\', '"');
    }
}
