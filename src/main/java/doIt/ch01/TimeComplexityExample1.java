package doIt.ch01;

public class TimeComplexityExample1 {
    public static void main(String[] args) { // 시간 복잡도 예제 - 시간 복잡도 유형 세 가지
        // 1~100 사이 값 랜덤 선택
        int findNumber = (int) (Math.random() * 100);
        // 선택된 값을 0부터 올라가며 탐색하여 일치하면 출력
        for (int i = 0; i < 100; i++) {
            if (i == findNumber) {
                System.out.println(i);
                break; // 일치하면 출력 후 break;
            }
        }
        // 빅-오메가(best case) 1번
        // 빅-세타(average case) N/2번
        // 빅-오(worst case) N번
    }

    /*
     * (시간 복잡도를 알고리즘 선택의 기준으로 사용)
     * - 연산 횟수는 1초에 1억 번 연산하는 것을 기준으로 생각
     * - 시간 복잡도는 최악일 때(데이터의 크기가 가장 클 때)를 기준으로 생각
     * - 데이터의 크기(N)을 단서로 삼아 사용해야하는 알고리즘을 추측해 볼 수도 있음
     * 
     * (연산 횟수 계산 방법)
     * - 연산 횟수 = 알고리즘 시간 복잡도 x 데이터의 크기
     *
     * (시간 복잡도 도출 기준) -> 비효율적인 로직을 개선하는 바탕으로 사용
     * - 상수는 시간 복잡도 계산에서 제외
     * - 가장 많이 중첩된 반복문의 수행 횟수가 시간 복잡도의 기준
     */
    public void discriminationPrinciple1() { // 연산 횟수가 N인 경우 O(N)
        int N = 100_000;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            System.out.println("연산횟수: " + cnt++);
        }
    }

    public void discriminationPrinciple2() { // 연산 횟수가 3N인 경우 O(N)
        int N = 100_000;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            System.out.println("연산횟수: " + cnt++);
        }
        for (int i = 0; i < N; i++) {
            System.out.println("연산횟수: " + cnt++);
        }
        for (int i = 0; i < N; i++) {
            System.out.println("연산횟수: " + cnt++);
        }
    }

    public void discriminationPrinciple3() { // 연산 횟수가 N^2인 경우 O(N^2)
        int N = 100_000;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.println("연산횟수: " + cnt++);
            }
        }
    }
}
