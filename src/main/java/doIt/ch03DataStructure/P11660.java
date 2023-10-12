package doIt.ch03DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

// Do it 코딩테스트 ch3-문제 004 구간 합 구하기 2 // P11660
public class P11660 {
    // 첫번째 줄 입력: 2차원 배열의 크기(N), 구간 합 질의 개수(M)
    // 두번째 ~ N + 1번째 줄 입력: 배열 (x - 1)번째 원소들
    // N + 2번째 ~ N + M + 1번째 줄: 구간들

    /*
     * - 질의의 개수가 100,000이므로 질의마다 합을 구하면 안 되고, 구간 합 배열을 이용해야 한다.
     *   - 문제의 핵심: 구간 합 배열이 1차원에서 2차원으로 확장된 것으로 생각하여 구간 합 배열을 구성하는 방법을 정하는 것
     *   ==> 2차원 구간 합 배열 D[X][Y] 정의: D[X][Y] = 원본 배열의 (0, 0)부터 (X, Y)까지의 사각형 영역 안에 있는 수의 합
     *   ==> D[i][j]의 값을 채우는 구간 합 공식: D[i][j] = D[i][j - 1] + D[i - 1][j] - D[i - 1][j - 1] + A[i][j]
     *   ==> 질의 x1, y1, x2, y2에 대한 답을 구간 합으로 구하는 방법: D[x2][y2] - D[x1 - 1][y2] - D[x2][y1 - 1] + D[x1 - 1][y1 - 1]
     */

    /*
     * 각 풀이 비교
     * - 풀이 1: Scanner, string.split(~) 사용
     *   - 메모리 329,120 KB, 시간 2,992 ms
     * - 풀이 1-1: Scanner, StringTokenizer 사용
     *   - 메모리 331,140 KB, 시간 3,000 ms
     * - 풀이 1-2: Scanner, scanner.nextInt()로 바로 받는 방식 사용
     *   - 시간 초과
     * - 풀이 2: BufferedReader, InputStreamReader, StringTokenizer 사용
     *   - 메모리 124,380 KB, 시간 1,668 ms
     * - 풀이 2-1: BufferedReader, InputStreamReader, string.split(~) 사용
     *   - 메모리 150,752 KB, 시간 1,920 ms
     * - 풀이 2-2: BufferedReader, InputStreamReader, string.split(~) 사용 + 구간 합 입력 작업에서 stream 살짝 사용
     *   - 메모리 156,312 KB, 시간 1,844 ms
     * => 추측
     *  - (참고사항) StringTokenizer는 legacy이므로 사용 자제할 것을 권고함
     *    - 풀이 2와 풀이 2-1을 비교하면 regex 작업을 하는 string.split보다 StringTokenizer가 살짝 빠른 것 같지만,
     *    - 풀이 1과 풀이 1-1을 비교하면 그렇지도 않다.
     *    *** https://velog.io/@effirin/Java-StringTokenizer%EC%99%80-Split-%EB%A9%94%EC%84%9C%EB%93%9C-%EC%96%B8%EC%A0%9C-%EC%8D%A8%EC%95%BC%ED%95%A0%EA%B9%8C
     *    *** https://blog.naver.com/chogahui05/221474002967
     *    - 특히 구분해야하는 구분자가 여러 종류라면 StringTokenizer는 사용하지 말 것
     *  - Scanner보다는 BufferedReader, InputStreamReader를 사용할 것
     *    - 특히 scanner.nextInt()로 계속 입력을 바로 받는 방식은 좋지 않다.
     */

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

    // 풀이 1-1 - 효율성 비교용 string.split(~) 대신 StringTokenizer 사용
    public void solution1_1() {
        Scanner scanner = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(scanner.nextLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] sums = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(scanner.nextLine());
            for (int j = 1; j <= N; j++) {
                sums[i][j] = sums[i][j - 1] + sums[i - 1][j] - sums[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        for (int queryCount = 1; queryCount <= M; queryCount++) {
            st = new StringTokenizer(scanner.nextLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int answer = sums[x2][y2] - sums[x1 - 1][y2] - sums[x2][y1 - 1] + sums[x1 - 1][y1 - 1];

            System.out.println(answer);
        }

        scanner.close();
    }

    // 풀이 1_2
    public void solution1_2() {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[][] sums = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sums[i][j] = sums[i][j - 1] + sums[i - 1][j] - sums[i - 1][j - 1] + scanner.nextInt();
            }
        }

        for (int queryCount = 1; queryCount <= M; queryCount++) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            int answer = sums[x2][y2] - sums[x1 - 1][y2] - sums[x2][y1 - 1] + sums[x1 - 1][y1 - 1];

            System.out.println(answer);
        }

        scanner.close();
    }

    // 풀이 2(책) - 메모리 초과해서 원본 배열 없앰
    // 2차원 구간 합 배열 D[X][Y] 정의: D[X][Y] = 원본 배열의 (0, 0)부터 (X, Y)까지의 사각형 영역 안에 있는 수의 합
    // D[i][j]의 값을 채우는 구간 합 공식: D[i][j] = D[i][j - 1] + D[i - 1][j] - D[i - 1][j - 1] + A[i][j]
    // 질의 x1, y1, x2, y2에 대한 답을 구간 합으로 구하는 방법: D[x2][y2] - D[x1 - 1][y2] - D[x2][y1 - 1] + D[x1 - 1][y1 - 1]
    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        /*
        int[][] A = new int[N + 1][M + 1];
        for (int i = 1; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] D = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 구간 합 구하기
                D[i][j] = D[i][j - 1] + D[i - 1][j] - D[i - 1][j - 1] + A[i][j];
            }
        }
         */

        int[][] D = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                // 구간 합 구하기
                D[i][j] = D[i][j - 1] + D[i - 1][j] - D[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            // 구간 합 배열로 질의에 답변하기
            int result = D[x2][y2] - D[x1 - 1][y2] - D[x2][y1 - 1] + D[x1 - 1][y1 - 1];
            System.out.println(result);
        }

        br.close();
    }

    // 풀이 2-1 - 풀이 1보다 풀이 2가 메모리, 시간 측면에서 효율적으로 보여서 확인하는 작업
    // - StringTokenizer에서 string.split()으로 바꿈
    public void solution2_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        int[][] D = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                // 구간 합 구하기
                D[i][j] = D[i][j - 1] + D[i - 1][j] - D[i - 1][j - 1] + Integer.parseInt(inputs[j - 1]);
            }
        }

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int x1 = Integer.parseInt(inputs[0]);
            int y1 = Integer.parseInt(inputs[1]);
            int x2 = Integer.parseInt(inputs[2]);
            int y2 = Integer.parseInt(inputs[3]);
            // 구간 합 배열로 질의에 답변하기
            int result = D[x2][y2] - D[x1 - 1][y2] - D[x2][y1 - 1] + D[x1 - 1][y1 - 1];
            System.out.println(result);
        }

        br.close();
    }

    // 풀이 2-2 - 풀이 1보다 풀이 2가 메모리, 시간 측면에서 효율적으로 보여서 확인하는 작업
    // - 구간 합 입력하는 작업에서 stream 살짝 사용
    public void solution2_2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        int[][] D = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            int[] elements = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j <= N; j++) {
                // 구간 합 구하기
                D[i][j] = D[i][j - 1] + D[i - 1][j] - D[i - 1][j - 1] + elements[j - 1];
            }
        }

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int x1 = Integer.parseInt(inputs[0]);
            int y1 = Integer.parseInt(inputs[1]);
            int x2 = Integer.parseInt(inputs[2]);
            int y2 = Integer.parseInt(inputs[3]);
            // 구간 합 배열로 질의에 답변하기
            int result = D[x2][y2] - D[x1 - 1][y2] - D[x2][y1 - 1] + D[x1 - 1][y1 - 1];
            System.out.println(result);
        }

        br.close();
    }
}
