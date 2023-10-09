package programmers.lv0;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

// lv0 qr code
public class Lessons181903 {
    /*
     * 풀이 2 - 인덱스 생각하기 피곤함, 풀이 1과 큰 속도 차이도 없는 듯
     */

    // 풀이 1 - StringBuilder 사용 직관적인 풀이 // 0.03 ms ~ 0.13ms
    public String solution1(int q, int r, String code) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            if (i % q == r) {
                sb.append(code.charAt(i));
            }
        }

        return sb.toString();
    }

    // 풀이 1-1 - 풀이 1에서 toCharArray() 사용 // 0.03 ms ~ 0.25ms
    // - 속도도 풀이 1이 더 빨라서 굳이 charAt()이 아니라 charArray로 바꿔서 풀 필요가 없다.
    public String solution1_1(int q, int r, String code) {
        StringBuilder sb = new StringBuilder();
        char[] codeToCharArray = code.toCharArray();

        for (int i = 0; i < code.length(); i++) {
            if (i % q == r) {
                sb.append(codeToCharArray[i]);
            }
        }

        return sb.toString();
    }

    // 풀이 1-2(다른 풀이 참고) - StringBuilder 사용, 적절하게 초기화 하여 if 없이 // 0.03 ms ~ 0.13ms
    public String solution1_2(int q, int r, String code) {
        StringBuilder sb = new StringBuilder();
        for (int i = r; i < code.length(); i += q) {
            sb.append(code.charAt(i));
        }

        return sb.toString();
    }

    // 풀이 1-3(다른 풀이 참고) - String + 연산 //
    public String solution1_3(int q, int r, String code) {
        String answer = "";

        for (int i = 0; i < code.length(); i++) {
            if (i % q == r) {
                answer += code.charAt(i);
            }
        }

        return answer;
    }

    // 풀이 2 - 배열로만(StringBuilder 없이) - 미리 length를 계산할 때 index를 생각해야하는 피곤함이 있음 // 0.02ms ~ 0.08ms
    public String solution2(int q, int r, String code) {
        int codeLength = code.length();
        int answerLength = codeLength % q > r ? codeLength / q + 1 : codeLength / q;
        // 처음 작성한 코드는 codeLength % q > r이 아니라 codeLength % q >= r로 판별했으나, 예외 발생함
        // int answerLength = codeLength % q >= r ? codeLength / q + 1 : codeLength / q;
        // -> java.lang.StringIndexOutOfBoundsException: String index out of range
        // 예를 들어 codeLength = 10, q = 3, r = 1일 때 >= 비교 시, answerLength = 10 / 3 + 1 = 4가 됨,
        // 하지만 이 경우, 나머지가 1인 인덱스를 찾으면 1, 4, 7임, codeLength가 10인 경우 가장 큰 인덱스는 9이기 때문
        char[] answerChars = new char[answerLength];

        for (int i = 0; i < answerLength; i++) {
            answerChars[i] = code.charAt(q * i + r);
        }

        return new String(answerChars);
    }

    // 풀이 3(다른 풀이 참고) - stream
    public String solution3(int q, int r, String code) {
        return IntStream.range(0, code.length()).filter(i -> i % q == r)
                .mapToObj(i -> String.valueOf(code.charAt(i))).collect(Collectors.joining());
    }
}
