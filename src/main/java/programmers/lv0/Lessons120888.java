package programmers.lv0;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

// lv0 중복된 문자 제거
public class Lessons120888 {
    /*
    * 풀이 3-1 LinkedHashSet 사용, String.join(~) 메서드
    * */

    // 풀이 1 - StringBuilder와 String의 메서드들 사용 - sb.indexOf(~),deleteCharAt(~), string.substring(~)
    //       - 원래의 String으로 sb를 시작해서 제거해나가는 방식
    //       - 풀리긴 하는데 조잡
    //       - 속도는 괜찮음
    public String solution1(String my_string) {
        StringBuilder sb = new StringBuilder(my_string);
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.indexOf(my_string.substring(i, i + 1)) != i) {
                sb.deleteCharAt(i);
            }
        }
        return sb.toString();
    }

    // 풀이 1_1(다른 풀이 참고 변형) - 풀이 1과 유사 - 더 깔끔
    //                           - 빈 sb에 추가해나가는 방식
    public String solution1_1(String my_string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < my_string.length(); i++) {
            if (i == my_string.indexOf(my_string.charAt(i)))
                sb.append(my_string.charAt(i));
        }
        return sb.toString();
    }

    // 풀이 2 - 단순하게 배열과 StringBuilder를 쓴다면
    public String solution2(String my_string) {
        char[] chars = my_string.toCharArray();
        StringBuilder sb = new StringBuilder();

        /*
        // 방법 1 - 일단 추가하고, 중복 발견하면 삭제
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            for (int j = 0; j < i; j++) {
                if (chars[i] == chars[j]) {
                    sb.deleteCharAt(sb.length() - 1);
                    break;
                }
            }
        }
         */
        // 방법 2 - 중복 감지하는 변수, 중복 false면 추가
        for (int i = 0; i < chars.length; i++) {
            boolean duplication = false;
            for (int j = 0; j < i; j++) {
                if (chars[i] == chars[j]) {
                    duplication = true;
                    break;
                }
            }
            if (!duplication) {
                sb.append(chars[i]);
            }
        }

        return sb.toString();
    }

    // 풀이 3 - Set이 중복을 없애주는 것을 이용
    public String solution3(String my_string) {
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = my_string.toCharArray();

        for (char ch1 : chars) {
            set.add(ch1);
        }
        for (int i = 0; i < chars.length; i++) {
            char ch2 = chars[i];
            if (set.contains(ch2)) { // 형 변환 없어도 가능
                sb.append(ch2);
                set.remove(ch2);
            }
        }

        return sb.toString();
    }

    // 풀이 3-1
    // - 풀이 3과 유사하게 Set 사용, 더 간결
    // - Set 중 LinkedHashSet 사용 (참고) Set 컬렉션 비교 https://velog.io/@gillog/HashSet
    //   *** LinkedHashSet은 들어온 순서 저장
    public String solution3_1(String my_string) {
        String[] answer = my_string.split("");
        Set<String> set = new LinkedHashSet<>(Arrays.asList(answer));
        // LinkedHashSet 생성자의 매개변수로 다른 컬렉션 받을 수 있음
        //   - String[] 배열을 - 순서를 보장하고, 배열에서 빠르게 Arrays 클래스 메서드 사용할 수 있는 ArrayList로 바꿈
        //   - LinkedHashSet으로 바꿈
        return String.join("", set);
        // String의 join(CharSequence delimiter, Iterable<? extends CharSequence> elements) 메서드 이용
        //   - delimiter는 구분 문자, Collection extends Iterable
    }

    // 풀이 4(다른 풀이 참고) - stream
    // stream.distinct()를 이용해서 중복 제거
    // distinct()가 어떤 메커니즘인지 알아볼 필요가 있겠지만,
    // 내용이 같아도 주소나 해시코드 때문에 다른 객체로 판별할 가능성이 있다면 위험하지 않을까 싶음.
    public String solution4(String my_string) {
        return my_string.chars().mapToObj(Character::toString).distinct().collect(Collectors.joining());
        // return Arrays.stream(myString.split("")).distinct().collect(Collectors.joining());
    }
}
