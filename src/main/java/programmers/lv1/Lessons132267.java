package programmers.lv1;

// lv1 콜라 문제
public class Lessons132267 {

    // 풀이 1
    public int solution1(int a, int b, int n) {
        // a = 마트에 주어야 하는 병 수,
        // b = 빈 병 a개를 가져다 주면 마트가 주는 콜라 병 수
        // n = 처음 갖고 있는 병의 개수 -> 현재 갖고 있는 병의 개수로 사용할 것
        int count = 0;

        while (n >= a) {
            if (n - a >= 0) {
                n -= a;
                n += b;
                count += b;
            }
        }

        return count;
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(int a, int b, int n) {
        int answer = 0;

        while (n >= a) {
            answer += b * (n / a);
            n = b * (n / a) + n % a;
        }

        return answer;
    }

    // 풀이 3(다른 풀이 참고)
    public int solution3(int a, int b, int n) {
        return recursive3(a, b, n, 0);
    }

    public int recursive3(int a, int b, int n, int result) {
        if (n < a) {
            return result;
        }

        int change = (n / a) * b;

        n = (n % a) + change;

        return recursive3(a, b, n, result + change);
    }

    // 풀이 4(다른 풀이 참고)
    public int solution4(int a, int b, int n) {
        return (n > b ? n - b : 0) / (a - b) * b;
    }
}
