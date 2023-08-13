package programmers.lv0;

// lv0 분수의 덧셈
public class Lessons120808 {
    /*
    * 최대공약수
    * A = Ga, B = Gb (cf.) (A >= B)
    * A = Bq1 + r1 // (cf.) r1 < B, r1 = B인 경우는 있을 수 없음 - 나머지가 0)
    * => r1 = A - Bq1 = G(a - bq1) // (cf.) r1 < B => G(a - bq1) < Gb => a - bq1 < b
    * // (cf.) a - bq1와 b는 서로소, 만약 a - bq1와 b가 서로소가 아니라면 최대공약수가 G가 아님을 증명할 수 있음
    * B = Gb, r1 = G(a - bq1) // (cf.) 바로 위에서 a - bq1 < b임을 보았음
    *
    * // B와 r1에 대해서도 같은 일을 함, 적어도 G = 1인 경우가 있으므로 문제 없음
    * B = r1q2 + r2
    * => r2 = B - r1q2 = G(b - a + bq1)
    *
    * ...
    * 만약 ri = 0이라면
    * ri-2 = ri-1qi + ri가 ri-2 = ri-1qi라는 것이고, 이 때의 qi가 공통이며 가장 큰 약수가 된다.
    * */

    /*
    * 최소공배수
    * A = Ga, B = Gb
    * L = (직관적으로) Gab = AB / G
    * */

    // 풀이 1(검색 참고) - 유클리드 호제법 검색 참고 맨 처음 풀이(2023년 2월 26일)
    public int[] solution1(int numer1, int denom1, int numer2, int denom2) {
        int a = numer1 * denom2 + numer2 * denom1;
        int b = denom1 * denom2;
        int c = a >= b ? gcd(a, b) : gcd(b, a);
        int d = a / c;
        int e = b / c;
        int[] answer = {d, e};
        return answer;
    }

    public static int gcd(int a, int b) {
        if(b==0) return a;
        return gcd(b, a%b);
    }

    // 풀이 2 - 유클리드 호제법 다시 보고 다시 풀이(2023년 8월 13일)
    public int[] solution2(int numer1, int denom1, int numer2, int denom2) {
        int GCDOfDenoms = getGCD(denom1, denom2);
        int quotient1 = denom1 / GCDOfDenoms;
        int quotient2 = denom2 / GCDOfDenoms;

        int numer3 = numer1 * quotient2 + numer2 * quotient1;
        int denom3 = GCDOfDenoms * quotient1 * quotient2;
        int GCDOfNumer3AndDenom3 = getGCD(numer3, denom3);
        int[] fraction = {numer3 / GCDOfNumer3AndDenom3, denom3 / GCDOfNumer3AndDenom3};
        return fraction;
    }

    private int getGCD(int a, int b) {
        if (a > b) {
            return getGCDRecursive(a, b);
        } else {
            return getGCDRecursive(b, a);
        }
    }

    private int getGCDRecursive(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return getGCDRecursive(b, a % b);
        }
        /* // 아래가 좀 더 이해하기 편한 것 같기도 하지만, 연산이 한 번 더 들어간다.
        if (num1 % num2 == 0) {
            return num2;
        }
        return GCD(num2, num1 % num2);
        */
    }

    // 풀이 3 - 풀이 1, 풀이 2 같이 보니 풀이 2처럼 풀 필요가 없어서 다시 풀이(2023년 8월 13일)
    // integer 범위가 걱정되는 상황이 아니라면 굳이 풀이 2처럼 갈 필요는 없을 듯하다.
    public int[] solution3(int numer1, int denom1, int numer2, int denom2) {
        int numer3 = numer1 * denom2 + numer2 * denom1;
        int denom3 = denom1 * denom2;
        int gcd = getGCD(numer3, denom3);
        int[] fraction = {numer3 / gcd, denom3 / gcd};
        return fraction;
    }

    // 풀이 4(다른 풀이 참고) - 분모에서 1씩 한 땀 한 땀 빼면서 나눠서 나머지가 0인 수를 찾아내기 - 당연히 성능은 좋지 않다.
    public int[] solution4(int numer1, int denom1, int numer2, int denom2) {
        int numer3 = numer1 * denom2 + numer2 * denom1;
        int denom3 = denom1 * denom2;

        for (int i = denom3; i >= 1; i--) {
            if (denom3 % i == 0 && numer3 % i == 0) {
                numer3 /= i;
                denom3 /= i;
            }
        }

        int[] fraction = {numer3, denom3};
        return fraction;
    }
}
