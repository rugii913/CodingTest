package programmers.lv0;

import java.util.Arrays;
import java.util.stream.Collectors;

// lv0 7의 개수
public class Lessons120912 {

    // 풀이 1
    public int solution1(int[] array) {
        int count = 0 ;

        for (int intElement : array) {
            for (char ch : String.valueOf(intElement).toCharArray()) {
                if (ch == '7') {
                    count++;
                }
            }
        }

        return count;
    }

    // 풀이 2
    public int solution2(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int intElement : array) {
            // sb.append(String.valueOf(intElement)); // append는 int를 매개변수로 받을 수 있음
            sb.append(intElement);
        }

        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '7') {
                count++;
            }
        }
        return count;
    }

    // 풀이 3(다른 풀이 참고) - int로 다루기, 가장 빠를 수밖에
    public int solution3(int[] array) {
        int answer = 0;
        for (int intElement : array) {
            while (intElement != 0) {
                if (intElement % 10 == 7) {
                    answer++;
                }
                intElement /= 10;
            }
        }
        return answer;
    }

    // 풀이 4(다른 풀이 참고) - stream
    public int solution4(int[] array) {
        return (int) Arrays.stream(
                        Arrays.stream(array)
                                .mapToObj(String::valueOf)
                                .collect(Collectors.joining())
                                .split("")
                )
                .filter(s -> s.equals("7"))
                .count();
    }
}
