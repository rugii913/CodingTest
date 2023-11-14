package programmers.lv1;

import java.util.*;

// lv1 두 개 뽑아서 더하기
public class Lessons68644 {

    // 풀이 1
    public int[] solution1(int[] numbers) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        int[] answer = new int[set.size()];
        int index = 0;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            answer[index++] = iterator.next();
        }
        Arrays.sort(answer);

        return answer;
    }

    // 풀이 1-1 - enhanced for
    public int[] solution1_1(int[] numbers) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        int[] answer = new int[set.size()];
        int index = 0;
        for (Integer integer : set) {
            answer[index++] = integer;
        }
        Arrays.sort(answer);

        return answer;
    }

    // 풀이 1-2(다른 풀이 참고) - 이럴 거라면 NavigableSet을 쓰는 게 낫다.
    public int[] solution1_2(int[] numbers) {
        Set<Integer> set = new HashSet<>();

        for(int i=0; i<numbers.length; i++) {
            for(int j=i+1; j<numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    // 풀이 2 - Navigable Set
    public int[] solution2(int[] numbers) {
        NavigableSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        int[] answer = new int[set.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = Objects.requireNonNull(set.pollFirst());
        }
        return answer;
    }

    // 풀이 3(다른 풀이 참고) - ArrayList의 contains() - 이럴 거면 Set을 사용하는 편이 낫다.
    public int[] solution3(int[] numbers) {
        ArrayList<Integer> result = new ArrayList<>();
        int tmp = 0;

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                tmp = numbers[i] + numbers[j];
                if (!result.contains(tmp)) {
                    result.add(tmp);
                }
            }
        }

        Collections.sort(result);

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
