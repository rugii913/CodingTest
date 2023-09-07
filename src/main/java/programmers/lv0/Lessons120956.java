package programmers.lv0;

// lv0 옹알이 (1)
public class Lessons120956 {

    // 풀이 1 - string.replace() 사용
    public int solution1(String[] babbling) {
        final String s1 = "aya";
        final String s2 = "ye";
        final String s3 = "woo";
        final String s4 = "ma";

        int count = 0;
        for (String bab : babbling) {
            String removedString = bab.replace(s1, " ")
                    .replace(s2, " ")
                    .replace(s3, " ")
                    .replace(s4, " ");
            if (removedString.isBlank()) {
                // replace(~, "") 및 isEmpty를 사용하면 테스트 실패 // Spring 등 Apache Commons Library를 사용할 수 있다면,
                // StringUtils 등을 사용해볼 수도 있을 것
                count++;
            }
        }

        return count;
    }

    // 풀이 2(다른 풀이 참고) - regex 사용
    public int solution2(String[] babbling) {
        int answer = 0;
        for (int i = 0; i < babbling.length; i++) {
            if (babbling[i].matches("^(aya(?!aya)|ye(?!ye)|woo(?!woo)|ma(?!ma))+$")) {
                answer++;
            }
        }
        return answer;
    }
}
