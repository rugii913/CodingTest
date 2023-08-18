package programmers.lv0;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

// lv0 약수 구하기
public class Lessons120897 {

    // 풀이 1 - 한참 헤맸음. 메서드를 사용할 때 size가 줄어드는 Collection에서는 for 사용 시 주의!
    public int[] solution1(int n) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                set.add(i);
                set.add(n / i);
            }
        }

        // return set.stream().mapToInt(Integer::intValue).toArray(); // 가능

        // 아래처럼 set.size()로 잡으면 pollFirst() 할 때마다 size()가 줄기 때문에 잘못된 결과가 나온다.
        /*
        int[] answer = new int[set.size()];
        for (int i = 0; i < set.size(); i++) {
            answer[i] = set.pollFirst();
        }

        return answer;
        */

        int count = set.size();
        int[] answer = new int[count];
        for (int i = 0; i < count; i++) {
            answer[i] = set.pollFirst();
        }

        return answer;
    }

    // 풀이 2 - 배열 사용 두 번 순환 - 생각보다 길지 않다.
    public int[] solution2(int n) {
        int count = 0;
        int[] tmpArray = new int[n];
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                tmpArray[count++] = i;
            }
        }

        int[] divisors = new int[count];
        for (int i = 0; i < count; i++) {
            divisors[i] = tmpArray[i];
        }
        return divisors;
    }

    // 풀이 3(다른 풀이 참고) - stream 간단 명료
    public int[] solution3(int n) {
        // return IntStream.rangeClosed(1, n).filter(i -> n % i == 0).toArray();
        return IntStream.rangeClosed(1, n).filter(i -> n % i == 0).toArray();
    }

    // 풀이 4(다른 풀이 참고) - List 사용
    public int[] solution4(int n) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                answer.add(i);
            }
        }
        return answer.stream().mapToInt(x -> x).toArray();
    }
}
