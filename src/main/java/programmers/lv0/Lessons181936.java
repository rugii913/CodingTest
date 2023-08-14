package programmers.lv0;

// lv0 공배수
public class Lessons181936 {

    // 풀이 1 - 공배수인지 확인만 하면 되므로 굳이 최소공배수 알고리즘 같은 것을 사용할 필요가 없다.
    public int solution1(int number, int n, int m) {
        return (number % n == 0 && number % m == 0) ? 1 : 0;
    }
}
