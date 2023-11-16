package programmers.lv1;

// lv1 크기가 작은 부분문자열
public class Lessons147355 {
    // 굳이 숫자로 파싱해서 비교할 필요가 없음, 문자열 자체를 lexicographically 비교하면 된다.

    // 풀이 1 - endIndex(exclusive) 따지기 유의 // 0.03ms ~ 3.12ms
    // - beginIndex는 endIndex - p.length()로 처리하면 충분
    // - while 종료는 endIndex > t.length()일 때 발생 - endIndex == t.length()까지 괜찮다.
    public int solution1(String t, String p) {
        int count = 0;

        int endIndex = p.length();
        while (endIndex <= t.length()) {
            if (t.substring(endIndex - p.length(), endIndex).compareTo(p) <= 0) {
                count++;
            }
            endIndex++;
        }

        return count;
    }

    // 풀이 1-1(다른 풀이 참고) 귀찮아서 대충 푼 듯
    public int solution1_1(String t, String p) {
        int answer = 0;
        try {
            for (int i = 0; i < t.length(); i++) {
                String num = t.substring(i, i + p.length());
                if (num.compareTo(p) < 1) {
                    answer++;
                }
            }
        } catch (Exception e) {
            System.out.println("end");
        }
        return answer;
    }

    // 풀이 1-2(위 풀이 수정)
    public int solution1_2(String t, String p) {
        int answer = 0;
        for (int i = 0; i < t.length() - p.length(); i++) {
            String num = t.substring(i, i + p.length());
            if (num.compareTo(p) <= 0) {
                answer++;
            }
        }
        return answer;
    }

    // 풀이 2(다른 풀이 참고) - startIndex 기준 따지기 + 숫자형으로 변환해서 생각 // 0.05ms ~ 5.97ms
    public int solution2(String t, String p) {
        int pLength = p.length();
        long pValue = Long.parseLong(p);
        int answer = 0;
        for (int i = 0; i <= t.length() - pLength; i++) {
            long tValue = Long.parseLong(t.substring(i, i + pLength));
            if (tValue <= pValue)
                answer++;
        }
        return answer;
    }

    // 풀이 2-1(다른 풀이 참고) 더 짧게
    public int solution2_1(String t, String p) {
        int answer = 0;
        long ip = Long.parseLong(p);

        for (int i = 0; i <= t.length() - p.length(); i++) {
            if (Long.parseLong(t.substring(i, i + p.length())) <= ip) {
                answer++;
            }
        }

        return answer;
    }
}
