package programmers.lv0;

import java.util.stream.Collectors;

// lv0 l로 만들기
public class Lessons181834 {

    // 풀이 1 - IntStream.collect(~) - 나름 고민하면서 풀었는데 훨씬 더 간단한 풀이들이 많음...
    public String solution1(String myString) {
        return myString.chars().map(i -> i < 'l' ? 'l' : i)
                .collect(StringBuilder::new, ((stringBuilder, i) -> stringBuilder.append(Character.toChars(i))), StringBuilder::append).toString();
        // collect의 인자: Supplier<StringBuilder> supplier, ObjIntConsumer<StringBuilder> accumulator, BiConsumer<StringBuilder, StringBuilder> combiner
    }

    // 풀이 1-1(다른 풀이 참고) - IntStream으로 끝까지 끌고 가지 않고, 중간에 mapToObj 통해서 적절하게 Stream<String>으로 바꿈
    public String solution1_1(String myString) {
        return myString.chars().mapToObj(i -> Character.toString(Integer.max(i, 'l'))).collect(Collectors.joining());
    }

    // 풀이 2(다른 풀이 참고) - replaceAll(regex, replacement) - replace(~)는 regex를 사용하지 않기 때문에, replaceAll(~)을 사용해야함
    public String solution2(String myString) {
        return myString.replaceAll("[^l-z]", "l"); // ^ 제외하고 l부터 z까지
    }

    // 풀이 2-1(다른 풀이 참고 수정)
    public String solution2_1(String myString) {
        return myString.replaceAll("[a-l]", "l"); // a부터 l까지
    }

    // 풀이 3(다른 풀이 참고) - 일반적인 for로 생각해볼만한 풀이 1
    // (IDE 경고) 루프 내 문자열 연결 '+='
    // 검사 정보: 루프의 String 연결을 보고합니다.
    // 모든 String 연결은 전체 문자열을 복사하므로 StringBuilder.append() 또는 StringBuffer.append()에 대한 명시적 호출로 대체하는 편이 일반적으로 선호됩니다.
    public String solution3(String myString) {
        String answer = "";

        for (int i = 0; i < myString.length(); i++) {
            if (myString.charAt(i) < 'l') {
                answer += "l";
            } else {
                answer += myString.charAt(i) + "";
            }
        }
        return answer;
    }

    // 풀이 3-1 - 일반적인 for로 생각해볼만한 풀이 2
    public String solution3_1(String myString) {
        StringBuilder sb = new StringBuilder();

        for (char ch : myString.toCharArray()) {
            if (ch < 'l') {
                sb.append('l');
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
