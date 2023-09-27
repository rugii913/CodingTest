package programmers.lv0;

// lv0 문자열 겹쳐 쓰기
public class Lessons181943 {
    /*
     * - 풀이 1: getChars(~), String.valueOf(char[])
     * - 풀이 2-1: substring(~), concat(~)
     * - 풀이 3: String의 replace(~)와 StringBuilder의 replace(~)
     * - 풀이 4-2: String.valueOf(char[])보다 new String(char[])가 더 빠르다?
     */

    // 풀이 1 - string.getChars(~), String.valueOf(char[]) 사용 // 0.03 ~ 0.09ms
    public String solution1(String my_string, String overwrite_string, int s) {
        char[] charsOfAnswer = my_string.toCharArray();
        overwrite_string.getChars(0, overwrite_string.length(), charsOfAnswer, s);

        return String.valueOf(charsOfAnswer);
    }

    // 풀이 2(다른 풀이 참고) - substring(~) 사용 // 9.66 ~ 15.17ms
    public String solution2(String my_string, String overwrite_string, int s) {
        String before = my_string.substring(0, s);
        String after = my_string.substring(s + overwrite_string.length());
        
        return before + overwrite_string + after;
    }

    // 풀이 2-1(다른 풀이 수정) - substring(~) + string.concat() // 0.02 ~ 0.04ms 100배 이상 차이, 좀 더 빠르게 하고 싶다면 string.concat(~) 사용
    public String solution2_1(String my_string, String overwrite_string, int s) {
        String before = my_string.substring(0, s);
        String after = my_string.substring(s + overwrite_string.length());

        return (before.concat(overwrite_string)).concat(after);
    }

    // 풀이 3(다른 풀이 참고) // 0.03 ~ 0.15ms - 풀이 1보다 약간 느리다. 굳이 StringBuilder를 사용할 필요가 없다.
    public String solution3(String my_string, String overwrite_string, int s) {
        StringBuilder sb = new StringBuilder(my_string);
        sb.replace(s, s + overwrite_string.length(), overwrite_string);
        // cf. String의 replace(~)와 StringBuilder의 replace(~)는 사용 방법이 많이 다르다.
        // String의 replace(~)
        // - 1. replace(char oldChar, char newChar) 2. replace(CharSequence target, CharSequence replacement)
        // StringBuilder의 replace(~)
        // - replace(int start, int end, String str)

        return sb.toString();
    }

    // 풀이 4(다른 풀이 참고) // 0.02 ~ 0.12ms
    public String solution4(String my_string, String overwrite_string, int s) {
        char[] charArray1 = my_string.toCharArray();
        char[] charArray2 = overwrite_string.toCharArray();
        for (int i = 0; i < overwrite_string.length(); i++) {
            charArray1[i + s] = charArray2[i];
        }

        return String.valueOf(charArray1);
    }

    // 풀이 4-1(다른 풀이 수정) // 0.03 ~ 0.11ms
    public String solution4_1(String my_string, String overwrite_string, int s) {
        char[] myStringCharArray = my_string.toCharArray();
        char[] overwriteStringCharArray = overwrite_string.toCharArray();
        System.arraycopy(overwriteStringCharArray, 0, myStringCharArray, s, overwrite_string.length());

        return String.valueOf(myStringCharArray);
    }

    // 풀이 4-2(다른 풀이 수정) // 0.02 ~ 0.08ms - new String(char[])가 더 빠르다?
    public String solution4_2(String my_string, String overwrite_string, int s) {
        char[] myStringCharArray = my_string.toCharArray();
        char[] overwriteStringCharArray = overwrite_string.toCharArray();
        System.arraycopy(overwriteStringCharArray, 0, myStringCharArray, s, overwrite_string.length());

        return new String(myStringCharArray);
    }
}
