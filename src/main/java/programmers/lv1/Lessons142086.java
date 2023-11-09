package programmers.lv1;

import java.util.HashMap;
import java.util.Map;

// lv1 가장 가까운 같은 글자
public class Lessons142086 {

    // 풀이 1 - map 사용 // 0.11ms ~ 3.37ms N이 최대 10,000이라서 배열 반복하는 것이 그리 유리하지 않을 거라 생각했음
    public int[] solution1(String s) {
        int[] answer = new int[s.length()];

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char charAtI = s.charAt(i);

            int latestPreviousIndex = map.getOrDefault(charAtI, -1);
            answer[i] = latestPreviousIndex == -1 ? -1 : i - latestPreviousIndex;
            map.put(charAtI, i);
        }

        return answer;
    }

    // 풀이 1-1(다른 풀이 참고) - map 사용
    public int[] solution1_1(String s) {
        int[] answer = new int[s.length()];

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            answer[i] = i - map.getOrDefault(ch, i + 1);
            map.put(ch, i);
        }

        return answer;
    }

    // 풀이 1-2(다른 풀이 참고) - substring() 사용 // 0.03ms ~ 24.71ms
    public int[] solution1_2(String s) {
        int[] answer = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            String substring = s.substring(0, i);
            if (substring.indexOf(s.charAt(i)) == - 1) {
                answer[i] = -1;
            } else {
                answer[i] = i - substring.lastIndexOf(s.charAt(i));
            }
        }

        return answer;
    }
    
    // 풀이 2 - 배열에서 반복 // 0.02ms ~ 3.72ms
    public int[] solution2(String s) {
        int[] answer = new int[s.length()];
        char[] charArray = s.toCharArray();

        char target;
        targetLoop: for (int i = 0; i < charArray.length; i++) {
            target = charArray[i];

            for (int j = i - 1; j >= 0; j--) {
                if (charArray[j] == target) {
                    answer[i] = i - j;
                    continue targetLoop;
                }
            }
            answer[i] = -1;
        }

        return answer;
    }
}
