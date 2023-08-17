package programmers.lv0;

import java.util.TreeSet;

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
}
