package programmers.lv0;

// lv0 문자열 밀기
public class Lessons120921 {

    // 풀이 1 - string.repeat(2) 사용 후 두 번째 문자열부터 한 칸씩 왼쪽으로 가면서 탐색 // 0.04 ~ 0.05ms
    public int solution1(String A, String B) {
            String AA = A.repeat(2); // AA = A가 반복되는 문자열

            int answer = -1;
            for (int i = 0; i < A.length(); i++) {
                // i = 0이면, beginIndex: A.length()부터 A.length개 문자로 이뤄진 substring -> A를 밀지 않은 형태
                // i = 1이면, beginIndex: A.length() - 1부터 A.length개 문자로 이뤄진 substring -> A를 오른쪽으로 한 번 민 형태
                // ...
                // i = A.length() - 1이면, beginIndex: 1부터 A.length개 문자로 이뤄진 substring -> A를 오른쪽으로 A.length() -1번 민 형태
                if (AA.substring(A.length() - i, A.length() * 2 - i).equals(B)) {
                    answer = i; // 위 설명에서 볼 수 있듯 몇 번 밀었는지가 i가 됨
                    break; // 최소 횟수를 return하라고 했으므로, 최소 i를 찾은 경우 바로 break;
                }
            }
            return answer;
    }

    // 풀이 1-1(다른 풀이 참고) // 0.03 ~ 0.08ms
    // - A를 오른쪽으로 민 B를 찾는 경우, A를 반복한 뒤 B를 찾는 게 아니라, B를 반복한 뒤 A를 찾는 형태로 구현하면 더 간편해진다.
    public int solution1_1(String A, String B) {
        String tempB = B.repeat(2);
        return tempB.indexOf(A);
    }

    // 풀이 2(다른 풀이 참고) - 진짜 오른쪽으로 한 칸 씩 밀어서 찾기 // String을 직접 사용해서 좀 느림 0.02 ~ 13.15ms
    public int solution2(String A, String B) {
        int answer = -1;
        int length = A.length();

        for (int i = 0; i < length; i++) {
            if (A.equals(B)) {
                answer = i;
                break;
            }
            A = A.charAt(length - 1) + A.substring(0, length - 1);
        }

        return answer;
    }

    // 풀이 2-1(다른 풀이 참고) // StringBuilder 사용 0.05 ~ 0.09ms
    public int solution2_1(String A, String B) {
        StringBuilder stringBuilder = new StringBuilder(A);

        if (A.equals(B)) {
            return 0;
        }
        for (int i = 1; i < A.length(); i++) {
            stringBuilder.insert(0, stringBuilder.charAt(stringBuilder.length() - 1));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            if (stringBuilder.toString().equals(B)) {
                return i;
            }
        }

        return -1;
    }

    // 풀이 2-2(2-1 수정) // StringBuilder 사용 0.04 ~ 0.09ms
    public int solution2_2(String A, String B) {
        int answer = 0;
        StringBuilder stringBuilder = new StringBuilder(A);
        int length = stringBuilder.length();

        while (!stringBuilder.toString().equals(B)) {
            stringBuilder.insert(0, stringBuilder.charAt(length - 1));
            stringBuilder.deleteCharAt(length); // 이 부분 조심, arg를 length - 1로 잘못 넘기면 안 됨
            answer++;

            if (answer > length) {
                answer = -1;
                break;
            }
        }

        return answer;
    }
}
