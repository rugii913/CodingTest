package programmers.lv0;

// lv0 특정한 문자를 대문자로 바꾸기
public class Lessons181873 {

    // 풀이 1
    public String solution1(String my_string, String alp) {
        return my_string.replace(alp, alp.toUpperCase());
    }

    // 풀이 2 - codePoints의 stream을 처리
    public String solution2(String my_string, String alp) {
        return my_string.chars().map(codePoint -> codePoint == alp.charAt(0) ? Character.toUpperCase(alp.charAt(0)) : codePoint)
                .collect(StringBuilder::new, (sb, integer) -> sb.append((char) integer), StringBuilder::append).toString();
    }

    // 풀이 3(다른 풀이 참고)
    public String solution3(String my_string, String alp) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < my_string.length(); i++) {
            char current = my_string.charAt(i);
            char target = alp.charAt(0);

            if (current == target) {
                sb.append(Character.toUpperCase(current));
            } else {
                sb.append(current);
            }
        }

        return sb.toString();
    }
}
