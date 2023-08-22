package programmers.lv0;

// lv0 최댓값 만들기 (2)
public class Lessons120862 {

    // 풀이 1 +7점??
    public int solution1(int[] numbers) {
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                // 같은 원소끼리 곱하지 않도록 j는 i + 1부터 시작해야함. 이렇게 해도 모든 원소 간의 곱을 탐색할 수 있음
                answer = Math.max(answer, numbers[i] * numbers[j]);
            }
        }
        return answer;
    }

    // 풀이 2 - 삽입 정렬 후 최댓값이 될 수 있는 후보 간 비교  * 삽입 정렬 코드 다시 확인해볼 것
    // 다른 테스트케이스는 비슷한데, 테스트 케이스 4번에서 풀이 1보다 빠르다.
    public int solution2(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (numbers[j - 1] > numbers[j]) {
                    int tmp = numbers[j - 1];
                    numbers[j - 1] = numbers[j];
                    numbers[j] = tmp;
                }
            }
        }
        // 당연히 Arrays.sort(numbers)로 가능하지만 느리다.

        int nominee1 = numbers[0] * numbers[1];
        int nominee2 = numbers[numbers.length - 2] * numbers[numbers.length - 1];

        return Math.max(nominee1, nominee2);
    }
}
