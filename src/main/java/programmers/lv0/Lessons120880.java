package programmers.lv0;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

// lv0 특이한 정렬
public class Lessons120880 {
    /*
    * 1. 풀이 3 Comparator 구현
    * 2. 풀이 1 vs. 2-1, 2-2 공간과 시간 관점에서 비교해보기
    * 3. 풀이 1 vs. 풀이 1-1 마찬가지로 공간과 시간 관점에서 비교해보기
    * */

    // 풀이 1 - lv0 등수 매기기 풀이 4 보조변수 활용 아이디어 참고 - 정렬 코드 없이 이중 반복문으로 끝
    // 0.02ms ~ 0.21ms
    public int[] solution1(int[] numlist, int n) {
        int length = numlist.length;

        int[] distances = new int[length];
        for (int i = 0; i < length; i++) {
            distances[i] = Math.abs(n - numlist[i]);
        }
        
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            int rank = 0; // 보조변수: distances[i]의 순위를 저장할 것
            for (int j = 0; j < length; j++) { // cf. 자기 자신은 아래 조건문들에 해당하는 조건이 없으므로 rank++ 없이 지나치게 됨
                if (distances[i] > distances[j]) {
                    rank++;
                } else if (distances[i] == distances[j] && numlist[i] < numlist[j]) {
                    rank++;
                }
            }
            answer[rank] = numlist[i];
        }

        return answer;
    }
    // 풀이 1-1 - distances 배열은 필수는 아님 -> 전체 반복문 하나 줄여냄 // 대신 answer 배열은 반드시 사용할 수밖에 없음
    // 0.03ms ~ 0.71ms - 풀이 1보다 오히려 느려짐 - 이중 반복문 안에 Math.abs 계산하는 부분이 끼어들어서 그런 듯하다.
    public int[] solution1_1(int[] numlist, int n) {
        int length = numlist.length;

        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            int rank = 0; // 보조변수: 순위를 저장할 것
            for (int j = 0; j < length; j++) { // cf. 자기 자신은 아래 조건문들에 해당하는 조건이 없으므로 rank++ 없이 지나치게 됨
                if (Math.abs(numlist[i] - n) > Math.abs(numlist[j] - n)) {
                    rank++;
                } else if (Math.abs(numlist[i] - n) == Math.abs(numlist[j] - n) && numlist[i] < numlist[j]) {
                    rank++;
                }
            }
            answer[rank] = numlist[i];
        }

        return answer;
    }

    // 풀이 2 - 처음 생각했던 풀이
    // 내림차순 정렬 후 가까운 순서대로 정렬 - 보조배열 distances와 answer를 안 써도 되는 장점 - 대신 이중 for문이 두 번
    // 0.02ms ~ 0.43ms
    public int[] solution2(int[] numlist, int n) {
        int length = numlist.length;

        // 내림차순 정렬
        for (int i = 1; i < length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (numlist[j] < numlist[j + 1]) {
                    int tmp = numlist[j];
                    numlist[j] = numlist[j + 1];
                    numlist[j + 1] = tmp;
                } else {
                    break;
                }
            }
        }
        // n과 가까운 순서대로 정렬
        for (int i = 1; i < length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (Math.abs(numlist[j] - n) > Math.abs(numlist[j + 1] - n)) {
                    int tmp = numlist[j];
                    numlist[j] = numlist[j + 1];
                    numlist[j + 1] = tmp;
                } else {
                    break;
                }
            }
        }

        return numlist;
    }
    // 풀이 2-1 - 이중 for문 한 번으로 줄이고 대신 for 안에 if를 두 개 넣을 수 있음
    // 0.02ms ~ 0.23ms
    public int[] solution2_1(int[] numlist, int n) {
        int length = numlist.length;

        // n과 가까운 순서대로 정렬 + 거리가 같으면 내림차순 정렬
        for (int i = 1; i < length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (Math.abs(numlist[j] - n) > Math.abs(numlist[j + 1] - n) || ((Math.abs(numlist[j] - n) == Math.abs(numlist[j + 1] - n) && numlist[j] < numlist[j + 1]))) {
                    int tmp = numlist[j];
                    numlist[j] = numlist[j + 1];
                    numlist[j + 1] = tmp;
                } else {
                    break;
                }
            }
        }

        return numlist;
    }

    // 풀이 3 - TreeSet 정렬 이용 - Comparator 구현
    public int[] solution3(int[] numlist, int n) {
        NavigableSet<Integer> set = new TreeSet<>((o1, o2) ->
                Math.abs(o1 - n) < Math.abs(o2 - n) || (Math.abs(o1 - n) == Math.abs(o2 - n) && o1 > o2) // 단축평가 때문에 앞 조건 만족하면 뒤 조건 안 보고 들어감
                ? -1 : 1);

        for (int i : numlist) {
            set.add(i);
        }

        int[] answer = new int[numlist.length];

        int index = 0;
        while (!set.isEmpty()) {
            answer[index++] = set.pollFirst();
        }

        return answer;
    }
}
