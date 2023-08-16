package programmers.lv0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// lv0 문자열 정렬하기 (1)
public class Lessons120850 {
    /*
    *   *** 일단은 풀이 3 기준으로 볼 것
    *   - string.replaceAll(~) 정규 표현식 사용
    *
    *   - Stream.of(~)와 Arrays.stream(~)의 차이
    *   return Arrays.stream(my_string.replaceAll("[A-Z|a-z]", "").split("")).sorted().mapToInt(Integer::parseInt).toArray();
    *   => 이런 식으로 진행하면 안 됨Stream.of(my_string.toCharArray()).filter(ch -> Character.isDigit(ch))
    *   -- String도 Comparable이라서 sort할 수 있음 - compareTo(~) 메서드 있음
    * */

    // 풀이 1 - string.toCharArray(), Arrays.sort(~) 사용 - 약 0.40ms 정도
    public int[] solution1(String my_string) {
        char[] stringCharArray = my_string.toCharArray();
        int[] tmpArray = new int[my_string.length()];
        int countOfNumbers = 0;

        for (int i = 0; i < my_string.length(); i++) {
            if (Character.isDigit(stringCharArray[i])) {
                tmpArray[countOfNumbers++] = Character.getNumericValue(stringCharArray[i]);
            }
        }

        int[] numberCharArray = Arrays.copyOfRange(tmpArray, 0, countOfNumbers);
        Arrays.sort(numberCharArray);

        return numberCharArray;
    }

    // 풀이 2(혼자 해보려다가 다른 풀이 참고) - stream
    public int[] solution2(String my_string) {
        // 이렇게 하면 안 됨 Stream.of(my_string.toCharArray()).filter(ch -> Character.isDigit(ch))
        // Stream.of(~)와 Arrays.stream(~)의 차이
        return Arrays.stream(my_string.replaceAll("[A-Z|a-z]", "").split("")).sorted().mapToInt(Integer::parseInt).toArray();
        // 정규 표현식 사용
        // String도 Comparable이라서 sort할 수 있음 - compareTo(~) 메서드 있음
    }

    // 풀이 3(다른 풀이 참고) - 속도는 풀이 1과 비슷 - 좀 더 깔끔한 것 같다.
    public int[] solution3(String my_string) {
        my_string = my_string.replaceAll("[a-z]", ""); // 영어 소문자 또는 0부터 9까지의 숫자 조건

        int[] answer = new int[my_string.length()];

        for (int i = 0; i < my_string.length(); i++) {
            answer[i] = my_string.charAt(i) - '0';
        }

        Arrays.sort(answer);

        return answer;
    }

    // 풀이 4(다른 풀이 힌트 및 참고) - 속도는 약간 더 느린 듯
    public int[] solution4(String my_string) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < my_string.length(); i++) {
            char ch = my_string.charAt(i);

            if (Character.isDigit(ch)) {
                list.add(Character.getNumericValue(ch));
            }
        }

        Integer[] integerArray = list.toArray(new Integer[list.size()]);
        int[] intArray = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            intArray[i] = integerArray[i];
        }
        Arrays.sort(intArray);

        return intArray;
    }

    // 풀이 5?? 구상만 - 풀이 4 발전 시켜서 add 할 때 바로 정렬시키는 자료구조에 넣는 건 어떨까?
    public int[] solution5(String my_string) {
        return null;
    }
}
