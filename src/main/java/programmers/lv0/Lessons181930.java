package programmers.lv0;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// lv0 주사위 게임 2
public class Lessons181930 {
    // Set을 이용한 풀이 2가 가장 깔끔
    // 규칙성 찾기

    // 풀이 1 // 0.01ms ~ 0.04ms
    public int solution1(int a, int b, int c) {
        if (a == b && b == c) {
            return (3 * a) * (3 * a * a) * (3 * a * a * a);
        } else if (a != b && b != c && c != a) {
            return a + b + c;
        } else {
            return (a + b + c) * (a * a + b * b + c * c);
        }
    }

    // 풀이 2(다른 풀이 참고) - Set을 이용해볼 수도 있다.
    public int solution2(int a, int b, int c) {
        Set<Integer> numbers = Stream.of(a, b, c).collect(Collectors.toSet());

        return (a + b + c) *
                (numbers.size() < 3 ? a * a + b * b + c * c : 1) *
                (numbers.size() < 2 ? a * a * a + b * b * b + c * c * c : 1);
    }

    // 풀이 2-1 (다른 풀이 수정)
    public int solution2_1(int a, int b, int c) {
        Set<Integer> numbers = new HashSet<>();
        numbers.add(a);
        numbers.add(b);
        numbers.add(c);
        // cf. Set<Integer> numbers = Set.of(a, b, c); 는 잘 작동하지 않는데 이유가 뭘까?

        return (a + b + c) *
                (numbers.size() < 3 ? a * a + b * b + c * c : 1) *
                (numbers.size() < 2 ? a * a * a + b * b * b + c * c * c : 1);
    }

    // 풀이 3 (다른 풀이 수정)
    public int solution3(int a, int b, int c) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(a, map.getOrDefault(a, 0) + 1);
        map.put(b, map.getOrDefault(a, 0) + 1);
        map.put(c, map.getOrDefault(a, 0) + 1);

        if (map.size() == 1) {
            answer = (int) ((3 * a) * (3 * Math.pow(a, 2)) * (3 * Math.pow(a, 3)));
        } else if (map.size() == 2) {
            answer = (int) ((a + b + c) * (Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2)));
        } else if (map.size() == 3) {
            answer = (a + b + c);
        }
        return answer;
    }

    // 풀이 4(다른 풀이 참고)
    public int solution4(int a, int b, int c) {
        int answer = 1;

        int maxOrder = 1;
        if (a == b || a == c || b == c) {
            maxOrder++;
        }

        if (a == b && b == c) {
            maxOrder++;
        }

        for (int i = 1; i <= maxOrder; i++) {
            answer *= (pow(a, i) + pow(b, i) + pow(c, i));
        }

        return answer;
    }

    private int pow(int base, int exponent) {
        if (exponent == 0) return 1;
        return base * pow(base, exponent - 1);
    }
}
