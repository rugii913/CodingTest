package programmers.lv0;

// lv0 세균 증식
public class Lessons120910 {
    /*
    * 비트 연산 <<
    * (참고) https://coding-factory.tistory.com/521
    * */

    // 풀이 1
    public int solution1(int n, int t) {
        for (int i = 1; i <= t; i++) {
            n *= 2;
        }
        return n;
    }

    // 풀이 1-1
    public int solution1_1(int n, int t) {
        // return (int) (n * Math.pow(2, t));
        return n * (int) Math.pow(2, t);
    }

    // 풀이 2(다른 풀이 참고) - ** 비트 연산자 <<, >>, >>>
    // x << y: 정수 x의 각 비트를 y만큼 왼쪽으로 이동시킨다. (새로 생기는 빈자리는 0으로 채워짐) -> 2배가 됨
    public int solution2(int n, int t) {
        int answer = n << t;

        return answer;
    }
}
