package programmers.lv0;

import java.util.stream.IntStream;

// lv0 팩토리얼
public class Lessons120848 {

    // 풀이 1 - +6점...?
    // 0 < n <= 3,628,800 조건이 있으므로 overflow는 신경쓰지 않아도 되는 상황임
    public int solution1(int n) {
        // for (int i = 1; i < n; i++) { 종료 조건을 이렇게 두면 n = 1, 2일 때 문제가 생김
        for (int i = 1; i <= n + 1; i++) {
            if (factorial(i) > n) {
                return i - 1;
            }
        }
        return -1; // 이 부분이 그렇게 예뻐보이지가 않는다.
    }
    private int factorial(int i) {
        if (i == 1 || i == 0) {
            return 1;
        } else {
            return i * factorial(i - 1);
        }
    }

    // 풀이 2 - 끝 부분이 안 예쁘다면 이렇게 처리할 수 있을 것 같다.
    public int solution2(int n) {
        int answer = -1;
        for (int i = 1; i <= n + 1; i++) {
            if (factorial(i) > n) {
                answer = i - 1;
                break;
            }
        }
        return answer;
    }

    // 풀이 3 - 굳이 factorial 함수를 쓸 필요도 없음
    public int solution3(int n) {
        int answer = 0;
        int product = 1;
        for (int i = 1; i <= n + 1; i++) {
            product *= i;
            if (product > n) {
                answer = i - 1;
                break;
            }
        }
        return answer;
    }

    // 풀이 4 - while 사용
    public int solution4(int n) {
        int answer = 1;
        int product = 1;
        while (true) {
            product *= answer;
            if (product > n) {
                answer--;
                break;
            } else {
                answer++;
            }
        }
        return answer;
    }

    // 풀이 4(다른 풀이 참고) - while 사용
    public int solution5(int n) {
        int i = 0;
        int product = 1;
        while (true) {
            if (product <= n) {
                product *= i + 1;
                i++;
            } else {
                break;
            }
        }
        return i - 1;
    }

    // 풀이 실패 - stream? n + 1까지 연산을 해야하기 때문에 답은 나오더라도 좋지 않음
    // n = 3,628,800, result = 10으로 테스트 해보면, 연산이 끝나지 않음
    public int solutionx(int n) {
        return IntStream.rangeClosed(1, n + 1)
                .filter(i -> IntStream.rangeClosed(1, i).reduce(1, (a, b) -> a * b) <= n).max().getAsInt();
    }
}
