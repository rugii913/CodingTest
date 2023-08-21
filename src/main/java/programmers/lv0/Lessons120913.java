package programmers.lv0;

// lv0 잘라서 배열로 저장하기
public class Lessons120913 {
    /*
    * ** 풀이 1 double 결과가 필요한 경우의 연산 유의
    * */

    // 풀이 1 - Math.min(~) 사용
    // ** int numOfSubstrings = (int) Math.ceil(originalLength / n)처럼 작성하지 않도록 유의
    public String[] solution1(String my_str, int n) {
        int originalLength = my_str.length();
        // int numOfSubstrings = (int) Math.ceil(originalLength / n);
        // 위처럼 두면 originalLength / n에서 이미 내림한 int가 나와서 제대로된 결과가 나오지 않음
        int numOfSubstrings = (int) Math.ceil((double) originalLength / n);

        String[] answer = new String[numOfSubstrings];

        for (int i = 0; i < numOfSubstrings; i++) {
            answer[i] = my_str.substring(i * n, Math.min((i + 1) * n, originalLength));
        }
        return answer;
    }
}
