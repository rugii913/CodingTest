package doIt.ch03DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

// Do it 코딩테스트 ch3-문제 004 구간 합 구하기2 // P11660
public class P11660 {
    // 첫번째 줄 입력: 2차원 배열의 크기(N), 구간 합 질의 개수(M)
    // 두번째 ~ N + 1번째 줄 입력: 배열 (x - 1)번째 원소들
    // N + 2번째 ~ N + M + 1번째 줄: 구간들

    // 풀이 x1 - 문제 잘못 읽음 - 합을 구하는 영역이 생각과는 달랐음
    public void solutionx() {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[][] nSquareElementsSum = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                nSquareElementsSum[i][j] = j == 1
                        ? nSquareElementsSum[i - 1][N] + scanner.nextInt()
                        : nSquareElementsSum[i][j - 1] + scanner.nextInt();
            }

            nSquareElementsSum[i][0] = nSquareElementsSum[i - 1][N];
            // 구간 합을 편리하게 이용하기 위해 빈 공간 사용(nSquareElementsSum[x1][y1 - 1] 부분 때문) - 직전 원소 끌어와서 각 줄 0 index에 배치
        }

        System.out.println(Arrays.deepToString(nSquareElementsSum)); // 배열 확인용 출력해봄

        for (int queryCount = 1; queryCount <= M; queryCount++) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            System.out.println(nSquareElementsSum[x2][y2] - nSquareElementsSum[x1][y1 - 1]);
        }

        scanner.close();
    }

    // 풀이 x2 - 풀이 x1에서 문제 잘못 읽어서 다시 작성 - 합을 구하는 영역이 생각과는 달랐음 - 그런데 시간 초과
    public void solutionx2() {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[][] nthRowIntervalSums = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                nthRowIntervalSums[i][j] = nthRowIntervalSums[i][j - 1] + scanner.nextInt();
            }
        }

        for (int queryCount = 1; queryCount <= M; queryCount++) {
            int answer = 0;
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            for (int i = x1; i <= x2; i++) {
                answer += nthRowIntervalSums[i][y2] - nthRowIntervalSums[i][y1 - 1];
            }

            System.out.println(answer);
        }

        scanner.close();
    }

    // 풀이 x3 - 시간 초과로 다시 풀이 - 혹시나 해서 BufferedReader로 바꿨지만 역시 시간 초과
    // - split 쪽에서 비효율적임을 확인하고 최종 수정 제출 - 그래도 시간 초과
    public void solutionx3() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        int[][] nthRowIntervalSums = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            inputs = bufferedReader.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                nthRowIntervalSums[i][j] = nthRowIntervalSums[i][j - 1] + Integer.parseInt(inputs[j - 1]);
            }
        }

        for (int queryCount = 1; queryCount <= M; queryCount++) {
            int answer = 0;
            inputs = bufferedReader.readLine().split(" ");
            int x1 = Integer.parseInt(inputs[0]);
            int y1 = Integer.parseInt(inputs[1]);
            int x2 = Integer.parseInt(inputs[2]);
            int y2 = Integer.parseInt(inputs[3]);

            for (int i = x1; i <= x2; i++) {
                answer += nthRowIntervalSums[i][y2] - nthRowIntervalSums[i][y1 - 1];
            }

            System.out.println(answer);
        }

        bufferedReader.close();
    }

    // 풀이 x4 - 어떻게든 줄여보려고 했지만 그래도 실패
    public void solutionx4() {
        Scanner scanner = new Scanner(System.in);

        String[] inputs = scanner.nextLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        int[][] nthRowIntervalSums = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            inputs = scanner.nextLine().split(" ");
            for (int j = 1; j <= N; j++) {
                nthRowIntervalSums[i][j] = nthRowIntervalSums[i][j - 1] + Integer.parseInt(inputs[j - 1]);
            }
        }

        System.out.println(Arrays.deepToString(nthRowIntervalSums)); // 배열 확인용 출력해봄

        for (int queryCount = 1; queryCount <= M; queryCount++) {
            int answer = 0;
            inputs = scanner.nextLine().split(" ");

            for (int i = Integer.parseInt(inputs[0]); i <= Integer.parseInt(inputs[2]); i++) {
                answer += nthRowIntervalSums[i][Integer.parseInt(inputs[3])] - nthRowIntervalSums[i][Integer.parseInt(inputs[1]) - 1];
            }

            System.out.println(answer);
        }

        scanner.close();
    }

    // 풀이 1 - 책 살짝 보고 for문 줄일 방법 생각
    public void solution1() {
        Scanner scanner = new Scanner(System.in);

        String[] inputs = scanner.nextLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        int[][] sums = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            inputs = scanner.nextLine().split(" ");
            for (int j = 1; j <= N; j++) {
                sums[i][j] = sums[i][j - 1] + sums[i - 1][j] - sums[i - 1][j - 1] + Integer.parseInt(inputs[j - 1]);
            }
        }

        System.out.println(Arrays.deepToString(sums)); // 배열 확인용 출력해봄

        for (int queryCount = 1; queryCount <= M; queryCount++) {
            inputs = scanner.nextLine().split(" ");
            int x1 = Integer.parseInt(inputs[0]);
            int y1 = Integer.parseInt(inputs[1]);
            int x2 = Integer.parseInt(inputs[2]);
            int y2 = Integer.parseInt(inputs[3]);

            int answer = sums[x2][y2] - sums[x1 - 1][y2] - sums[x2][y1 - 1] + sums[x1 - 1][y1 - 1];

            System.out.println(answer);
        }

        scanner.close();
    }
}
