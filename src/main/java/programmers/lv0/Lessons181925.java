package programmers.lv0;

import java.util.Map;

// lv0 수 조작하기 2
public class Lessons181925 {

    // 풀이 1 // switch expression 0.49ms ~ 5.85ms // 일반 switch 0.73ms ~ 7.17ms
    public String solution1(int[] numLog) {
        char[] inputKey = new char[numLog.length -1];
        for (int i = 1; i < numLog.length; i++) {
            /*
            // switch expression // 0.49ms ~ 5.85ms
            inputKey[i - 1] = switch(numLog[i] - numLog[i -1]) {
                case 1 -> 'w';
                case -1 -> 's';
                case 10 -> 'd';
                case -10 -> 'a';
                default -> ' ';
            };
             */
            // 일반 switch // 0.73ms ~ 7.17ms
            switch(numLog[i] - numLog[i - 1]) {
                case 1:
                    inputKey[i - 1] = 'w';
                    break;
                case -1:
                    inputKey[i - 1] = 's';
                    break;
                case 10:
                    inputKey[i - 1] = 'd';
                    break;
                case -10:
                    inputKey[i - 1] = 'a';
                    break;
            }
        }
        return new String(inputKey);
    }

    // 풀이 1-1 if 분기 // 0.91ms ~ 11.30ms
    public String solution1_1(int[] numLog) {
        char[] inputKey = new char[numLog.length -1];

        for (int i = 1; i < numLog.length; i++) {
            if (numLog[i] - numLog[i - 1] == 1) {
                inputKey[i - 1] = 'w';
            } else if (numLog[i] - numLog[i - 1] == -1) {
                inputKey[i - 1] = 's';
            } else if (numLog[i] - numLog[i - 1] == 10) {
                inputKey[i - 1] = 'd';
            } else {
                inputKey[i - 1] = 'a';
            }
        }
        return new String(inputKey);
    }

    // 풀이 2 // 2.19ms ~ 15.40ms
    public String solution2(int[] numLog) {
        char[] inputKey = new char[numLog.length -1];
        Map<Integer, Character> intToKeyMap = Map.of(1, 'w', -1, 's', 10, 'd', -10, 'a');

        for (int i = 1; i < numLog.length; i++) {
            inputKey[i - 1] = intToKeyMap.get(numLog[i] - numLog[i - 1]);
        }

        return new String(inputKey);
    }
}
