package programmers.lv1;

// lv1 가운데 글자 가져오기
public class Lessons12903 {
    // 가운데 인덱스 따지기
    // n개의 문자가 있을 때,
    // (n이 홀수, 즉 문자가 홀수 개인 경우)
    // - 1부터 n까지 n개라면, 가운데는 n / 2 + 1 --> ex. 3개라면 3 / 2 + 1 = 2 // 9개라면 9 / 2 + 1 = 5
    // - index 0부터 따진다면, 가운데는 n / 2
    // (n이 짝수, 즉 문자가 짝수 개인 경우)
    // - 1부터 n까지 n개라면, 가운데는 n / 2, n / 2 + 1 --> ex. 4개라면 2, 3 // 10개라면 5, 6
    // - index 0부터 따진다면, 가운데는 n / 2 - 1, n / 2
    // ==> 풀이 1-1과 같이 일반화
    // endIndex (n / 2) + 1은 고정
    // beginIndex는 n이 홀수일 때 n / 2, 짝수일 때 n / 2 - 1 ==> (n - 1) / 2로 일반화시킬 수 있다.
    // ====> 가운데 인덱스 따지기 일반화 결론
    // beginIndex (n - 1) / 2로 일반화
    // endIndex (n / 2) + 1

    // 풀이 1
    public String solution1(String s) {
        int length = s.length();
        return length % 2 == 0 ? s.substring(length / 2 - 1, length / 2 + 1) : s.substring(length / 2, length / 2 + 1);
    }

    // 풀이 1-1(다른 풀이 참고) - 위 설명 참고
    public String solution1_1(String s) {
        return s.substring((s.length() - 1) / 2, s.length() / 2 + 1);
    }
}
