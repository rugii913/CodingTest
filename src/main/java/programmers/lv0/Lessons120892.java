package programmers.lv0;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

// lv0 암호 해독
public class Lessons120892 {

    // 풀이 1
    public String solution1(String cipher, int code) {
        int cipherLength = cipher.length();
        StringBuilder sb = new StringBuilder(cipherLength / code);

        for (int i = code; i <= cipherLength; i += code) {
            sb.append(cipher.charAt(i - 1));
        }

        return sb.toString();
    }

    // 풀이 2(다른 풀이 참고) - stream
    public String solution2(String cipher, int code) {
        return IntStream.range(0, cipher.length())
                .filter(value -> value % code == code - 1)
                .mapToObj(c -> String.valueOf(cipher.charAt(c)))
                .collect(Collectors.joining());
    }
}
