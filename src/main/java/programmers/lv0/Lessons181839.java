package programmers.lv0;

// lv0 주사위 게임1
public class Lessons181839 {

    // 풀이 1 - 눈에 안 들어와서 좋지 않은 코드
    public int solution1(int a, int b) {
        return (a % 2 != 0) && (b % 2 != 0) ? a * a + b * b
                : (a % 2 != 0) || (b % 2 != 0) ? 2 * (a + b) : Math.abs(a - b);
    }

    // 풀이 1-1
    public int solution1_1(int a, int b) {
        if (a % 2 == 0 && b % 2 == 0) {
            return Math.abs(a - b);
        } else if (a % 2 != 0 && b % 2 != 0) {
            return a * a + b * b;
        } else {
            return 2 * (a + b);
        }
    }
}
