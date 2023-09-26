package programmers.lv1;

// lv1 짝수와 홀수
public class Lessons12937 {

    // 풀이 1
    private static final String EVEN_STRING = "Even";
    private static final String Odd_STRING = "Odd";
    public String solution1(int num) {
        if (num % 2 == 0) {
            return EVEN_STRING;
        } else {
            return Odd_STRING;
        }
    }
    
    // 풀이 2(다른 풀이 참고) - 삼항 연산자
    public String solution2(int num) {
        return (num % 2 == 0) ? "Even" : "Odd";
        // return num % 2 == 0 ? "Even" : "Odd"; // 소괄호 없애도 가능
    }
}
