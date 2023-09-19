package programmers.lv0;

// lv0 종이 자르기 - M * N 크기의 종이를 1 * 1 크기 종이들로 자르기 위한 최소 가위질 횟수(종이 겹쳐서 자르기 불가능)
public class Lessons120922 {

    // 풀이 1
    // (1) (Math.max(M, N) - 1): M, N 중 더 긴 쪽 찾아서 (긴 쪽 길이 - 1) = (Math.max(M, N) - 1)번 자르기
    // (2) Math.max(M, N) * (Math.min(M, N) - 1): 현재 종이는 (Math.max(M, N))개 있는 상태
    //  - 각 종이를 (작은 쪽 길이 - 1) = (Math.min(M, N) - 1)번 자르기
    //  - 종이가 (Math.max(M, N))개 있으므로, (Math.max(M, N))번 반복
    public int solution1(int M, int N) {
        return (Math.max(M, N) - 1) + (Math.min(M, N) - 1) * Math.max(M, N);
        // 풀이 2까지 확인, 긴 쪽 짧은 쪽 따질 필요도 없음
        // return (Math.min(M, N) - 1) + (Math.max(M, N) - 1) * Math.min(M, N);
    }

    // 풀이 2
    public int solution2(int M, int N) {
        // return M * N - 1; // 항들을 합치면 왼쪽처럼 표현할 수 있으나, 사람이 의미를 부여하기 어려운 식
        return M - 1 + (N - 1) * M;
    }
}
