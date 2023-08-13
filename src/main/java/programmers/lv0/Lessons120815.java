package programmers.lv0;

// lv0 피자 나눠 먹기 (2)
public class Lessons120815 {
    /*
    * 1. 재사용할 필요가 없다면 풀이 1도 고려
    * 2. 풀이 3은 재사용도 가능할 것 같고, 다른 메서드를 호출할 필요도 없음 - 최대공약수 성질 이용
    * */

    // 풀이 1 - 6과의 최소공배수 찾고 6으로 나누기
    // n이 6의 배수인 경우 먼저 거르고, n이 3의 배수인 경우엔 * 2, 2의 배수인 경우 * 3
    // n이 2, 3, 6을 소인수로 갖고 있지 않은 경우 n * 6
    public int solution1(int n) {
        if (n % 6 == 0) {
            return n / 6;
        }
        if (n % 3 == 0) {
            return 2 * n / 6;
        } else if (n % 2 == 0) {
            return 3 * n / 6;
        }
        return n;
    }

    // 풀이 2 - 최소공배수와 관련
    public int solution2(int n) {
        int gcd = getGCD(6, n);
        return n / gcd;
    }

    private int getGCD(int a, int b) {
        if (b > a) {
            return getGCDRecursive(b, a);
        } else {
            return getGCDRecursive(a, b);
        }
    }

    private int getGCDRecursive(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return getGCDRecursive(b, a % b);
        }
    }

    // 풀이 3(다른 풀이 참고) - 최대 공약수 성질 생각, 파라미터만 잘 설정하면 재사용 가능할 듯
    public int solution3(int n) {
        int answer = 1;
        while (true) {
            if (6 * answer % n == 0) {
                return answer;
            }
            answer++;
        }
    }
}
