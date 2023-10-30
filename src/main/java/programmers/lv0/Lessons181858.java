package programmers.lv0;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// lv0 무작위로 K개의 수 뽑기
public class Lessons181858 {
    // 풀이 6 볼 것

    // arr.length번 만큼 Deque로 만든 뒤 맨 앞 뽑아, 중복 여부 Set으로 넣어서 확인하고, 다시 Deque 사용해서 맨 뒤로 추가하고, ...
    // - 이렇게 하려고 했으나, 어차피 앞에서 뽑고, 뒤로 추가하는 방식을 딱 arr.legnth번 수행한다면 순서는 유지됨
    // - 굳이 복잡하게 진행할 필요 없이, 순서 보장하면서 중복을 제거하면 된다.
    // => 배열로도 어렵지 않게 구현 가능

    // 풀이 1 - stream에서 distinct 사용 // 2.65ms ~ 10.29ms
    public int[] solution1(int[] arr, int k) {
        int[] distinctArray = Arrays.stream(arr).distinct().toArray();

        int[] kArray = new int[k];
        if (distinctArray.length < k) {
            Arrays.fill(kArray, -1);
            System.arraycopy(distinctArray, 0, kArray, 0, distinctArray.length);
        } else {
            System.arraycopy(distinctArray, 0, kArray, 0, k);
        }

        return kArray;
    }

    // 풀이 1-1(다른 풀이 참고) - intStream 두 개 합치기 // 5.25ms ~ 10.83ms
    public int[] solution1_1(int[] arr, int k) {
        return IntStream.concat(Arrays.stream(arr).distinct(), IntStream.range(0, k).map(i -> -1)).limit(k).toArray();
    }

    // 풀이 1-2(위 1-1 수정) // 4.99ms ~ 26.80ms // generate(() -> -1)).limit(k)이 range(0, k).map(i -> -1)보다 느린가 보다...
    public int[] solution1_2(int[] arr, int k) {
        return IntStream.concat(Arrays.stream(arr).distinct(), IntStream.generate(() -> -1)).limit(k).toArray();
    }

    // 풀이 1-3(다른 풀이 참고) // 5.20ms ~ 2935.45ms
    public int[] solution1_3(int[] arr, int k) {
        return IntStream.range(0, k).map(i -> i >= Arrays.stream(arr).boxed().collect(Collectors.toSet()).size() ? -1 : Arrays.stream(arr).distinct().toArray()[i]).toArray();
    }

    // 풀이 2 - 배열만 사용해서 구현 가능 // 0.01ms ~ 479.03ms - 문제는 이중 for문 때문에 수가 커지면 몹시 느려짐
    public int[] solution2(int[] arr, int k) {
        int distinctNumbersCount = 0;
        int[] distinctNumbersTmp = new int[arr.length];

        int countOfCurrentNumber;
        for (int i = 0; i < arr.length; i++) {
            countOfCurrentNumber = 0;
            for (int j = i; j >= 0; j--) {
                if (arr[i] == arr[j]) {
                    countOfCurrentNumber++;
                }
            }

            if (countOfCurrentNumber == 1) {
                distinctNumbersTmp[distinctNumbersCount++] = arr[i];
            }
        }

        int[] distinctNumbers = new int[k];
        if (distinctNumbersCount < k) {
            System.arraycopy(distinctNumbersTmp, 0, distinctNumbers, 0, distinctNumbersCount);
            for (int i = distinctNumbersCount; i < k; i++) {
                distinctNumbers[i] = -1;
            }
        } else {
            System.arraycopy(distinctNumbersTmp, 0, distinctNumbers, 0, k);
        }

        return distinctNumbers;
    }

    // 풀이 2-1 - 풀이 3 이후 구현, 무식하게 전부 순회할 필요 없이, 이미 갖고 있는 배열을 Set 비슷하게 활용할 수 있었음
    // 0.02ms ~ 71.92ms - 풀이 2보다는 훨씬 개선됨 - 어쨌든 배열을 순회해야 하므로, distinctNumbersCount가 커질 수록 오래 걸리는 듯하다. Hash를 이길 순 없음...
    public int[] solution2_1(int[] arr, int k) {
        int distinctNumbersCount = 0;
        int[] distinctNumbersTmp = new int[arr.length];

        boolean isDuplicated;
        for (int number : arr) {
            isDuplicated = false;
            for (int j = 0; j < distinctNumbersCount; j++) {
                if (number == distinctNumbersTmp[j]) {
                    isDuplicated = true;
                    break;
                }
            }

            if (!isDuplicated) {
                distinctNumbersTmp[distinctNumbersCount++] = number;
            }
        }

        int[] distinctNumbers = new int[k];
        if (distinctNumbersCount < k) {
            System.arraycopy(distinctNumbersTmp, 0, distinctNumbers, 0, distinctNumbersCount);
            for (int i = distinctNumbersCount; i < k; i++) {
                distinctNumbers[i] = -1;
            }
        } else {
            System.arraycopy(distinctNumbersTmp, 0, distinctNumbers, 0, k);
        }

        return distinctNumbers;
    }

    // 풀이 3 - 풀이 2 중 중복 체크 부분만 Set 사용 // 0.05ms ~ 7.90ms - 굳이 이런 식으로 할 필요가 없다. 풀이 6 참고
    public int[] solution3(int[] arr, int k) {
        Set<Integer> duplicancyCheckSet = new HashSet<>();
        int distinctNumbersCount = 0;
        int[] distinctNumbersTmp = new int[arr.length];

        for (int number : arr) {
            if (!duplicancyCheckSet.contains(number)) {
                duplicancyCheckSet.add(number);
                distinctNumbersTmp[distinctNumbersCount++] = number;
            }
        }

        int[] distinctNumbers = new int[k];
        if (distinctNumbersCount < k) {
            System.arraycopy(distinctNumbersTmp, 0, distinctNumbers, 0, distinctNumbersCount);
            for (int i = distinctNumbersCount; i < k; i++) {
                distinctNumbers[i] = -1;
            }
        } else {
            System.arraycopy(distinctNumbersTmp, 0, distinctNumbers, 0, k);
        }

        return distinctNumbers;
    }

    // 풀이 4 - LinkedHashSet으로 순서 보존하기 // 0.58ms ~ 4.40ms
    public int[] solution4(int[] arr, int k) {
        Set<Integer> intSet = new LinkedHashSet<>();
        int arrIndex = 0;
        while (intSet.size() < k && arrIndex < arr.length) {
            intSet.add(arr[arrIndex++]);
        }

        Integer[] integerArray = intSet.toArray(Integer[]::new);

        int[] answer = new int[k];
        Arrays.fill(answer, -1);
        for (int i = 0; i < integerArray.length; i++) {
            answer[i] = integerArray[i];
        }
        return answer;
    }

    // 풀이 5(다른 풀이 참고 수정) - ArrayList contains 사용 // 0.04ms ~ 191.14ms - ArrayList에서 contains(~)을 사용하기 때문에 대상 숫자가 많아지면 느려질 수밖에 없다.
    public int[] solution5(int[] arr, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i : arr) {
            if (!arrayList.contains(i)) {
                arrayList.add(i);
            }
        }

        int[] answer = new int[k];
        Arrays.fill(answer, -1);
        for (int i = 0; i < k && i < arrayList.size(); i++)
            answer[i] = arrayList.get(i);
        return answer;
    }

    // 풀이 6(다른 풀이 참고 수정) - Set 사용 간단한 풀이 // 0.06ms ~ 2.28ms - 가장 나은 듯
    public int[] solution6(int[] arr, int k) {
        int[] answer = new int[k];
        Arrays.fill(answer, -1);

        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int j : arr) {
            if (!set.contains(j)) {
                answer[count++] = j;
                set.add(j);
            }
            if (count == answer.length) {
                break;
            }
        }
        return answer;
    }
}
