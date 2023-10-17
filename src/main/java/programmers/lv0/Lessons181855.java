package programmers.lv0;

import java.util.*;
import java.util.stream.Collectors;

// lv0 문자열 묶기
public class Lessons181855 {

    // 풀이 1 - map.getOrDefault(~) // 0.22ms ~ 29.38ms
    public int solution1(String[] strArr) {
        Map<Integer, Integer> map = new HashMap<>(); // map(문자열의 크기, 그룹의 크기) -> 훑으면서 그룹의 크기 1씩 늘림

        int currentStrLength; // 변수 공간을 절약하기 위해 for 밖에 선언해줌
        for (String str : strArr) {
            currentStrLength = str.length();
            map.put(currentStrLength, map.getOrDefault(currentStrLength, 0) + 1);
        }

        int max = 0;
        for (int count : map.values()) {
            max = Math.max(max, count);
        }

        return max;
    }

    // 풀이 1-1(다른 풀이 참고) - values() 사용 안 하고, for 한 번에 끝낼 수 있음 // 0.30ms ~ 27.88ms - 속도 차이는 크지 않음
    public int solution1_1(String[] strArr) {
        Map<Integer, Integer> map = new HashMap<>();

        int max = 0;
        for (String str : strArr) {
            int stringLength = str.length();
            int count = map.getOrDefault(stringLength, 0) + 1;
            map.put(stringLength, count);

            max = Math.max(max, count);
        }

        return max;
    }

    // 풀이 2 - 단순 배열 이용 - 문자열의 길이 자체는 30까지로 제한되어 있으므로 빠르게 가능할 듯 // 0.05ms ~ 5.99ms 역시 제한 조건 덕분에 빠르게 가능했음
    public int solution2(String[] strArr) {
        int[] countArray = new int[31];

        for (String str : strArr) {
            countArray[str.length()]++;
        }

        int max = 0;
        for (int count : countArray) {
            max = Math.max(max, count);
        }

        return max;
    }

    // 풀이 3(다른 풀이 참고) - stream // 7.25ms ~ 62.34ms
    public int solution3(String[] strArr) {
        return Arrays.stream(strArr).collect(Collectors.groupingBy(String::length)).values()
                .stream().max(Comparator.comparingInt(List::size)).orElse(Collections.emptyList()).size();
    }

    // 풀이 3-1(다른 풀이 참고 수정) - stream // 5.94ms ~ 30.65ms
    public int solution3_1(String[] strArr) {
        return Arrays.stream(strArr).map(String::length).collect(Collectors.groupingBy(Integer::valueOf)).values()
                .stream().mapToInt(List::size).max().orElse(0);
    }
}
