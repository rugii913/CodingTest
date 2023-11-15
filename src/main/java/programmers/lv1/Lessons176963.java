package programmers.lv1;

import java.util.HashMap;
import java.util.Map;

// lv1 추억 점수
public class Lessons176963 {

    // 풀이 1
    public int[] solution1(String[] name, int[] yearning, String[][] photo) {
        Map<String, Integer> nameYearningMap = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            nameYearningMap.put(name[i], yearning[i]);
        }

        int[] answer = new int[photo.length];
        for (int i = 0; i < answer.length; i++) {
            for (String personName : photo[i]) {
                answer[i] += nameYearningMap.getOrDefault(personName, 0);
            }
        }

        return answer;
    }

    // 풀이 2(다른 풀이 참고) - 삼중 for -> N이 100 정도만 돼도, Map 같은 자료구조를 사용하는게 훨씬 이득
    public int[] solution2(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        for (int i = 0; i < photo.length; i++) {
            for (int j = 0; j < photo[i].length; j++) {
                for (int k = 0; k < name.length; k++) {
                    if (photo[i][j].equals(name[k])) answer[i] += yearning[k];
                }
            }
        }
        return answer;
    }
}
