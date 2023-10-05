package programmers.lv1;

// lv1 문자열 내 p와 y의 개수
public class Lessons12916 {

    // 풀이 1
    boolean solution1(String s) {
        int pCount = 0;
        int yCount = 0;

        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == 'p' || ch == 'P') {
                pCount++;
            } else if (ch == 'y' || ch == 'Y') {
                yCount++;
            }
        }

        return pCount == yCount;
    }

    // 풀이 1-1(다른 풀이 참고) - count 변수 개수를 하나로 처리 가능
    boolean solution1_1(String s) {
        s = s.toLowerCase();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == 'p')
                count++;
            else if (s.charAt(i) == 'y')
                count--;
        }

        return count == 0;
    }

    // 풀이 2(다른 풀이 참고) - stream
    boolean solution2(String s) {
        s = s.toUpperCase();

        return s.chars().filter(ch -> 'P' == ch).count() == s.chars().filter(ch -> 'Y' == ch).count();
    }

    // 풀이 3(다른 풀이 참고) - replaceAll regex 사용
    boolean solution3(String s) {
        // return s.replaceAll("[^yY]", "").length() - s.replaceAll("[^pP]", "").length() == 0 ? true : false; // (IDE 경고) 단순화할 수 있습니다.
        return s.replaceAll("[^yY]", "").length() - s.replaceAll("[^pP]", "").length() == 0;
    }
}
