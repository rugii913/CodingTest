package programmers.lv0;

// lv0 저주의 숫자 3
public class Lessons120871 {

    // 풀이 1 - 재귀 이용하기 위해 메서드 분리함 / 풀이 2 참고하면 꼭 재귀가 아니라 for문으로도 가능함
    public int solution1(int n) {
        return get3xNum(n);
    }
    private int get3xNum(int n) {
        if (n == 1) {
            return 1;
        }

        int prev3xNum = get3xNum(n - 1);
        int cur3xNum = prev3xNum + 1;
        while (cur3xNum % 3 == 0 || has3(cur3xNum)) { // 3의 배수이거나 3을 포함하면 cur3xNum 증가
            cur3xNum++;
        }
        return cur3xNum;
    }
    private boolean has3(int num) {
        while (num > 0) {
            if (num % 10 == 3) {
                return true;
            }
            num /= 10;
        }
        return false;
    }
    // 풀이 1-1(다른 풀이 참고)
    public int solution1_1(int n) {
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer++;

            if (answer % 3 == 0) {
                i--;
                continue;
            }

            int tempAnswer = answer;
            while (tempAnswer > 0) {
                if (tempAnswer % 10 == 3) { // tempAnswer(answer의 값)이 3의 배수를 갖고 있는 만큼 i--(반복 횟수 증가)
                    i--;
                    break;
                }
                tempAnswer /= 10;
            }
        }
        return answer;
    }

    // 풀이 2(다른 풀이 참고) - for문 사용, 조건에 맞지 않으면 i--로 한 번 더 answer++를 반복하게 하는 아이디어
    // String으로 변환하는 작업 필요
    public int solution2(int n) {
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer++;
            if (answer % 3 == 0 || String.valueOf(answer).contains("3")) {
                i--;
            }
        }
        return answer;
    }
    // 풀이 2-1(다른 풀이 참고) - if 대신 while 사용 -> i--가 아니라 answer을 직접 제어
    public int solution2_1(int n) {
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer++;
            while (answer % 3 == 0 || String.valueOf(answer).contains("3")) {
                answer++;
            }
        }
        return answer;
    }

    // 풀이 3(다른 풀이 참고) - i를 증가시키는 게 아닌 n을 증가시키는 아이디어
    // - 3을 포함한 숫자 혹은 3의 배수인 수 만큼 n을 뒤로 미룸
    // 일단 String으로 변환
    public int solution3(int n) {
        String str;
        for (int i = 1; i <= n; i++){
            str = "" + i;
            if (str.contains("3") || i % 3 == 0) {
                n++;
            }
        }
        return n;
    }

    // 풀이 4(다른 풀이 참고) - while문 중첩, n-- 이용
    // 숫자가 하나 증가할 때는 당연히 1 증가, 3을 포함하거나 3의 배수일 때마다도 1씩 증가하도록
    public int solution4(int n) {
        int result = 0;
        while (n-- > 0) {
            ++result;
            while (result % 3 == 0 || Integer.toString(result).contains("3")) {
                ++result;
            }
        }
        return result;
    }
}
