package programmers.lv0;

// lv0 이진수 더하기
public class Lessons120885 {

    // 풀이 1
    public String solution1(String bin1, String bin2) {
        int intAnswer = Integer.parseInt(bin1, 2) + Integer.parseInt(bin2, 2);
        return Integer.toBinaryString(intAnswer);
    }
}
