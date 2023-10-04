package programmers.lv0;

// lv0 코드 처리하기
public class Lessons181932 {
    // - 문제의 의도 파악: 풀이 1-1 => (i % 2 == mode) { ~ }
    // - 그 외에도 중첩 반복문을 이상하게 꼬이도록 만들 여지가 있는 지문들. !인간의 언어를 어떻게 효율적으로 코딩할지 생각해볼 것.

    // 풀이 1 // 0.03ms ~ 8.88ms
    public String solution1(String code) {
        int mode = 0;
        StringBuilder retSb = new StringBuilder();

        char[] codeCharArray = code.toCharArray();
        for (int index = 0; index < codeCharArray.length; index++) {
            if (codeCharArray[index] == '1') {
                mode = mode == 0 ? 1 : 0;
                continue;
            }

            if (mode == 0 && index % 2 == 0) {
                retSb.append(codeCharArray[index]);
            } else if (mode == 1 && index % 2 != 0) {
                retSb.append(codeCharArray[index]);
            }
        }

        return retSb.length() == 0 ? "EMPTY" : retSb.toString();
    }

    // 풀이 1-1(다른 풀이 참고) - 굳이 charArray로 바꾸지 않음
    public String solution1_1(String code) {
        StringBuilder answer = new StringBuilder();
        int mode = 0;

        for (int i = 0; i < code.length(); i++) {
            char current = code.charAt(i);
            if (current == '1') {
                mode = mode == 0 ? 1 : 0;
                continue;
            }

            if (i % 2 == mode) { // -> ********* 이 부분이 원래 문제에서 의도한 바인 듯하다.
                answer.append(current);
            }
        }
        return answer.length() == 0 ? "EMPTY" : answer.toString();
    }
}
