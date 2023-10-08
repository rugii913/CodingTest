package programmers.lv0;
// lv0 접미사인지 확인하기
public class Lessons181908 {
    /*
     * string.substring()으로 인덱스를 따질 필요 없이, string.endsWith(~)로 확인 가능
     */

    // 풀이 1
    public int solution1(String my_string, String is_suffix) {
        int is_suffixStartIndex = my_string.length() - is_suffix.length();

        if (is_suffixStartIndex < 0) {
            return 0;
        }
        return my_string.substring(is_suffixStartIndex).equals(is_suffix) ? 1 : 0;
    }

    // 풀이 2(다른 풀이 참고) - string.endsWith(~)
    public int solution2(String my_string, String is_suffix) {
        /*
        if (my_string.endsWith(is_suffix)) {
            return 1;
        } else {
            return 0;
        }
         */
        return my_string.endsWith(is_suffix) ? 1 : 0;
    }

    // 풀이 3(다른 풀이 참고) - string.lastIndexOf(~)
    public int solution3(String my_string, String is_suffix) {
        if (!my_string.contains(is_suffix)) {
            return 0;
        }
        return my_string.lastIndexOf(is_suffix) == (my_string.length() - is_suffix.length()) ? 1 : 0;
    }

    // 풀이 4(다른 풀이 참고) - stringBuilder.reverse()
    public int solution4(String my_string, String is_suffix) {
        String strRev = new StringBuilder(my_string).reverse().toString();
        String sufRev = new StringBuilder(is_suffix).reverse().toString();
        return strRev.indexOf(sufRev) == 0 ? 1 : 0;
    }
}
