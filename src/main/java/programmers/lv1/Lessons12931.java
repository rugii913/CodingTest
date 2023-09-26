package programmers.lv1;

// lv1 자릿수 더하기
public class Lessons12931 {

    // 풀이 1
    public int solution1(int n) {
        int sum = 0;

        while (n > 0) { // 다른 사람 풀이 중 [while (true) + 조건 충족 시 break]는 디버깅, 값 추적할 때 피곤하다는 의견 있음
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(int n) {
        int answer = 0;
        String[] array = String.valueOf(n).split("");
        for (String s : array) {
            answer += Integer.parseInt(s);
        }
        return answer;
    }
}
