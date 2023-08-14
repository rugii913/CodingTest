package programmers.lv0;

// lv0 제곱수 판별하기
public class Lessons120909 {
    /*
    * - 풀이 1(brute force)보다는 Math 라이브러리를 쓰는 게 빠르다.
    * - 단 2개의 분기만 있다면 삼항 연산자를 사용해도 깔끔하다.
    * */

    // 풀이 1 - brute force
    public int solution1(int n) {
        for (int i = 0; i < n; i++) {
            if (n == i * i) {
                return 1;
            }
        }
        return 2;
    }

    // 풀이 2
    public int solution2(int n) {
        if (((int) Math.pow(Math.round(Math.sqrt(n)), 2)) == n) {
            return 1;
        } else {
            return 2;
        }
    }

    // 풀이 3(다른 풀이 참고)
    public int solution3(int n) {
        if (n % Math.sqrt(n) == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    // 풀이 4(다른 풀이 참고) - 단 두 개의 분기만 있다면 삼항 연산자를 써도 깔끔하다.
    public int solution4(int n) {
        return Math.sqrt(n) % 1 == 0 ? 1 : 2;
    }
}
