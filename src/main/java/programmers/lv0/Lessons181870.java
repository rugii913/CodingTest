package programmers.lv0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// lv0 ad 제거하기
public class Lessons181870 {

    // 풀이 1 // 0.02ms ~ 0.81ms
    public String[] solution1(String[] strArr) {
        int countContainingAd = 0;
        boolean[] isContainingAd = new boolean[strArr.length];

        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].contains("ad")) {
                isContainingAd[i] = true;
                countContainingAd++;
            }
        }

        String[] strNotContainingAd = new String[strArr.length - countContainingAd];
        int strNotContainingAdIndex = 0;
        for (int i = 0; i < isContainingAd.length; i++) {
            if (!isContainingAd[i]) {
                strNotContainingAd[strNotContainingAdIndex++] = strArr[i];
            }
        }

        return strNotContainingAd;
    }

    // 풀이 2 - stream // 1.83ms ~ 5.29ms
    // toArray(IntFunction)
    // IntFunction<A[]> generator – a function which produces a new array of the desired type and the provided length
    public String[] solution2(String[] strArr) {
        return Arrays.stream(strArr).filter(str -> !str.contains("ad")).toArray(String[]::new);
    }

    // 풀이 2-1(다른 풀이 참고) - stream, toArray(IntFunction)을 모르면 이런 식으로 한 단계를 더 거칠 수밖에 없다. // 1.36ms ~ 8.58ms
    // list.toArray(T[])
    public String[] solution2_1(String[] strArr) {
        List<String> list = Arrays.stream(strArr).filter(m -> !m.contains("ad")).collect(Collectors.toList());
        String[] answer = new String[list.size()];
        return list.toArray(answer);
    }

    // 풀이 3 - ArrayList 사용 // 0.60ms ~ 1.58ms
    // *** 배열을 컬렉션으로 바꾸는 방법 더 확인할 것
    public String[] solution3(String[] strArr) {
        List<String> strList = new ArrayList<>(Arrays.asList(strArr));
        for (int i = strList.size() - 1; i >= 0; i--) {
            if (strList.get(i).contains("ad")) {
                strList.remove(i);
            }
        }

        return strList.toArray(String[]::new);
    }

    // 풀이 x - ArrayList 사용 // 0.60ms ~ 1.58ms
    // java.util.ConcurrentModificationException 발생
    public String[] solutionx(String[] strArr) {
        List<String> strList = new ArrayList<>(Arrays.asList(strArr));

        // java.util.ConcurrentModificationException 발생
        strList.iterator().forEachRemaining(str -> {
                    if (str.contains("ad")) {
                        strList.remove(str);
                    }
                }
        );

        // 이렇게 해도 java.util.ConcurrentModificationException 발생
        strList.iterator().forEachRemaining(str -> {
                    if (str.contains("ad")) {
                        strList.remove(str);
                    }
                }
        );

        return strList.toArray(String[]::new);
    }

    // 풀이 3-1(다른 풀이 참고) - ArrayList 사용 - 한 단계 더 거치지만, 하드코딩은 덜 하다는 점
    // *** 배열을 컬렉션으로 바꾸는 방법 더 확인할 것
    public String[] solution3_1(String[] strArr) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (String str : strArr)
            if (!str.contains("ad"))
                arrayList.add(str);

        int idx = 0;
        String[] answer = new String[arrayList.size()];
        for (String str : arrayList)
            answer[idx++] = str;
        return answer;
    }
}
