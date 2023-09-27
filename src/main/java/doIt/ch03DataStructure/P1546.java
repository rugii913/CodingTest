package doIt.ch03DataStructure;

import java.util.Scanner;

// Do it 코딩테스트 ch3-문제 002 평균 구하기 // P1546
public class P1546 {
    /*
     * - 반복문 줄일 수 있으면 줄이기
     * - (double) (score * 100) 대신 score * 100.0 사용
     */

    // 풀이 1
    public void solution1() {
        Scanner sc = new Scanner(System.in);
        int numberOfCourses = sc.nextInt();

        int[] scores = new int[numberOfCourses];
        for (int i = 0; i < numberOfCourses; i++) {
            scores[i] = sc.nextInt(); // 입력이 40 80 60 이런 식으로 한 줄로 들어와도 잘 동작한다.
        }

        int maxScore = 0;
        for (int score : scores) {
            maxScore = Math.max(maxScore, score);
        }

        // cf. 조작되기 전의 sum을 아래가 아닌 위 반복문에서 미리 저장해서, 반복문을 한 번 줄일 수 있음
        double sumOfManipulatedScore = 0;
        for (int score : scores) {
            sumOfManipulatedScore += (double) (score * 100) / maxScore;
            // 여기서 굳이 (double)로 casting할 필요가 없이 score * 100.0을 할 수 있었음
        }

        double averageManipulated = sumOfManipulatedScore / numberOfCourses;

        System.out.println(averageManipulated);

        sc.close();
    }

    // 풀이 2(책)
    public void solution2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        long sum = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > max) max = a[i];
            sum = sum + a[i];
        }

        // 한 과목과 관련된 수식을 총합한 후 관련된 수식으로 변환해 로직이 간단해짐
        System.out.println(sum * 100.0 / max / n);
    }
}
