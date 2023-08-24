package programmers.lv0;

import java.util.Arrays;
import java.util.stream.Collectors;

// lv0 외계어 사전 - 문제 혹은 테스트에 오류 있음, 동일 알파벳 두 번 이상 사용한 경우도 모두 통과됨
public class Lessons120869 {

    // 풀이 1
    public int solution1(String[] spell, String[] dic) {
        int answer = 2; // 기본값은 존재하지 않는 것으로

        for (String word : dic) {
            if (isComposedOf(word, spell)) {
                answer = 1;
                break;
            }
        }

        return answer;
    }
    private boolean isComposedOf(String word, String[] spell) {
        // 한 번씩만 사용한다는 조건 - 다른 복잡한 방법보다는, 크기가 같음을 이용해서 먼저 거름
        if (word.length() != spell.length) {
            return false;
        }

        for (int i = 0; i < spell.length; i++) {
            if (!word.contains(spell[i])) {
                return false; // spell을 포함하지 않으면 이르게 false return
            }
        }

        return true;
    }

    // 풀이 2(다른 풀이 참고) - stream - list로 만들었기 때문에 개수도 파악하고 있는 것으로 보인다.
    public int solution2(String[] spell, String[] dic) {
        return Arrays.stream(dic)
                .map(s -> s.chars().sorted().mapToObj(i -> String.valueOf((char) i)).collect(Collectors.joining()))
                .collect(Collectors.toList()).contains(Arrays.stream(spell).sorted().collect(Collectors.joining()))
                ? 1 : 2;
    }

    // 풀이 3(다른 풀이 참고) - flag 사용
    // 전체적인 진행은 풀이 1과 크게 다르지 않은데, 이렇게 for if 쌓이는 꼴이 보기 싫어서 풀이 1에서는 메서드 분리했음
    public int solution3(String[] spell, String[] dic) {
        for (int i = 0; i < dic.length; i++) {
            if (dic[i].length() == spell.length) { // 길이 비교
                boolean flag = true;
                for (int j = 0; j < spell.length; j++) {
                    if (!dic[i].contains(spell[j])) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    return 1;
                }
            }
        }
        return 2;
    }
}
