package programmers.lv0;

// lv0 문자열 정수의 합
public class Lessons181849 {
    /*
     * 1. Character.getNumericValue(~) static 메서드 사용법 - API doc 보면서 digit(~) 메서드 써야 하나? 한참 헤맸음
     * 2. string.chars(): IntStream을 반환하는 메서드도 있구나
     * */

    // 풀이 1 - string.toCharArray(), Character.getNumericValue(~) 사용
    public int solution1(String num_str) {
        int answer = 0;

        for (char digitChar : num_str.toCharArray()) {
            answer += Character.getNumericValue(digitChar);
        }

        return answer;
    }

    // 풀이 2 - 풀이 1보다 먼저 생각했던 방식인데 느릴 것 같아서 먼저 시도하지 않았음.
    // 돌려보니 실제로 더 느리다.
    public int solution2(String num_str) {
        int answer = 0;

        for (String digitString : num_str.split("")) {
            answer += Integer.parseInt(digitString);
        }

        return answer;
    }

    // 풀이 3(다른 풀이 참고)
    // - string.chars(): IntStream을 반환하는 메서드도 있구나
    // - 48로 박혀있는 게 마음에 안 들어서 Character.codePointAt("0", 0) 사용했음
    public int solution3(String num_str) {
        // return num_str.chars().map(c -> c - 48).sum();

        final int codePointOfZero = Character.codePointAt("0", 0);
        return num_str.chars().map(c -> c - codePointOfZero).sum();
    }
}
