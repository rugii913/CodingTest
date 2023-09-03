package doIt.ch01;

public class timeComplexityExample1 {
    public static void main(String[] args) {
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
}
