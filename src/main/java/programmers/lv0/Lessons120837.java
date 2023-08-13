package programmers.lv0;

// lv0 개미 군단
public class Lessons120837 {
    /*
    * 가장 큰 수부터 채워 나가면 끝
    * */

    // 풀이 1 - 풀이 2 참고하면 굳이 곱하고 빼는 과정을 할 필요가 없음
    public int solution1(int hp) {
        int answer = hp / 5;
        hp -= hp / 5 * 5;

        answer += hp / 3;
        hp -= hp / 3 * 3;

        return answer += hp;
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(int hp) {
        int answer = hp / 5;
        hp %= 5;

        answer += hp / 3;
        hp %= 3;

        return answer + hp;
    }

    // 풀이 3(다른 풀이 참고) - 간결
    public int solution3(int hp) {
        return hp / 5 + (hp % 5 / 3) + hp % 5 % 3;
    }
}
