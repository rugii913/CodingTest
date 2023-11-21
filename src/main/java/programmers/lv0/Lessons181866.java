package programmers.lv0;

import java.util.*;
import java.util.stream.Stream;

// lv0 문자열 잘라서 정렬하기
public class Lessons181866 {

    // 풀이 1
    public String[] solution1(String myString) {
        String[] targetArray = myString.split("x");
        Arrays.sort(targetArray);
        // return targetArray; 할 경우 테스트 모두 실패 - 빈 문자열이 있는 경우 때문으로 보임

        return Stream.of(targetArray).filter(str -> !str.isBlank()).toArray(String[]::new);
    }

    // 풀이 1-1
    public String[] solution1_1(String myString) {
        return Arrays.stream(myString.split("x")).filter(x -> !x.isBlank()).sorted().toArray(String[]::new);
    }

    // 풀이 2 - 배열과 StringBuilder
    public String[] solution2(String myString) {
        StringBuilder sb = new StringBuilder();

        char[] charArray = myString.toCharArray();
        String[] tmpArray = new String[charArray.length];
        int count = 0;

        for (char c : charArray) {
            if (c == 'x') {
                if (sb.length() != 0) {
                    tmpArray[count++] = sb.toString();
                    sb.setLength(0);
                }
                continue;
            }
            sb.append(c);
        }

        if (sb.length() != 0) {
            tmpArray[count++] = sb.toString();
        }

        String[] result = Arrays.copyOf(tmpArray, count);
        Arrays.sort(result);
        return result;
    }

    // 풀이 3 - PriorityQueue
    public String[] solution3(String myString) {
        StringBuilder sb = new StringBuilder();

        char[] charArray = myString.toCharArray();
        // Queue<String> priorityQueue = new PriorityQueue<>(Comparator.naturalOrder());
        Queue<String> priorityQueue = new PriorityQueue<>(); // Comparator 안 넣으면 알아서 natural order로

        for (char c : charArray) {
            if (c == 'x') {
                if (sb.length() != 0) {
                    priorityQueue.add(sb.toString());
                    sb.setLength(0);
                }
                continue;
            }
            sb.append(c);
        }

        if (sb.length() != 0) {
            priorityQueue.add(sb.toString());
            sb.setLength(0);
        }

        String[] strings = new String[priorityQueue.size()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = priorityQueue.poll();
        }
        return strings;

        // 아래는 실패함
        // return priorityQueue.toArray(String[]::new);
        // return priorityQueue.stream().toArray(String[]::new);
    }

    // 풀이 4(다른 풀이 참고) - StringTokenizer는 중간에 끼는 빈 문자열을 제거해버린다.
    public String[] solution4(String myString) {
        StringTokenizer st = new StringTokenizer(myString, "x");
        int idx = 0, N = st.countTokens();
        String[] result = new String[N];
        for (int n = 0;n < N;n++)
            result[idx++] = st.nextToken();
        Arrays.sort(result);
        return result;
    }
}
