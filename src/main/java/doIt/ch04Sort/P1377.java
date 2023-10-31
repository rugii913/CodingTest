package doIt.ch04Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

// Do it 코딩테스트 ch4-문제 016 버블 소트 프로그램 1(문제 제목: 버블 소트) // P1377
public class P1377 {
    /*
    * 정렬 전 인덱스와 정렬 후 인덱스를 비교
    * - 한 번 루프가 있을 때, 오른쪽으로는 정렬되지 않은 영역만큼 움직일 수 있지만, 왼쪽으로는 단 한 칸밖에 이동하지 못한다.
    * - break되지 않았다는 것은 적어도 swap이 한 번은 발생한 것이다.
    * - swap은 정렬이 보장되지 않은 영역에서만 발생한다.
    * - 실제로는 정렬되어 있지만, 정렬이 보장되어 있지 않은 영역은, 더 이상 swap이 발생하지 않는다.
    * - 정렬이 보장되어 있지 않은 영역에서 실제로 정렬되어 있지 않은 영역은 정렬될 때까지 swap이 발생한다.
    * --> 가장 큰 (정렬 전 index - 정렬 후 index) 값 = 왼쪽으로 이동한 횟수 = break 전까지의 loop 횟수
    * */

    // 풀이 x1
    // 안 쪽 루프가 실행된 횟수를 단순히 (targetArray[i] > targetArray[i + 1])인 곳의 개수를 세는 방식으로 구할 순 없음
    // --> ex. {10 1 3 5 2}가 주어질 경우 아래 결과는 3이 되는데, 실제 출력되어야 할 값은 4이다.
    // --> ex. {3 4 5 1} 아래 결과는 2인데, 실제 출력되어야 할 값은 4이다.
    public void solutionx1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] targetArray = new int[N + 1];
        for (int i = 1; i < targetArray.length; i++) {
            targetArray[i] = Integer.parseInt(br.readLine());
        }

        int count = 1;
        for (int i = 1; i < targetArray.length - 1; i++) {
            if (targetArray[i] > targetArray[i + 1]) {
                count++;
            }
        }

        System.out.println(count);

        br.close();
    }

    // 풀이 x2 - 가능하지 않을까 싶었지만 안 됨...
    /*
    *
    * 숫자 5개 - solx2 출력값 = 4 //  sol1 출력값 = 3
5
6
2
1
5
4
*
    * 숫자 8개 - 아래와 동일
8
6
2
1
2
3
5
4
7
*
    * 숫자 8개 - solx2 출력값 = 4 //  sol1 출력값 = 3
8
16
5
4
5
9
14
12
19
*
    * 숫자 10개 - solx2 출력값 = 5 //  sol1 출력값 = 4
10
16
5
4
4
5
9
14
5
12
19
*
    * 숫자 20개 - solx2 출력값 = 14 //  sol1 출력값 = 10
20
20
10
11
16
6
5
4
3
2
1
3
4
5
13
9
17
14
5
12
19
    * */
    public void solutionx2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] targetArray = new int[N + 1];
        for (int i = 1; i < targetArray.length; i++) {
            targetArray[i] = Integer.parseInt(br.readLine());
        }

        int count = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < targetArray.length; i++) {
            if (stack.empty()) {
                stack.push(targetArray[i]);
                continue;
            }

            if (stack.peek() <= targetArray[i]) {
                stack.push(targetArray[i]);
            } else {
                while (!stack.empty() && stack.peek() > targetArray[i]) {
                    stack.pop();
                    count++;
                }
                stack.push(targetArray[i]);
            }
        }

        System.out.println(count);

        br.close();
    }

    // 풀이 x2-1 - 재시도
    public void solutionx2_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] targetArray = new int[N + 1];
        for (int i = 1; i < targetArray.length; i++) {
            targetArray[i] = Integer.parseInt(br.readLine());
        }

        int count = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < targetArray.length; i++) {
            if (stack.empty()) {
                stack.push(targetArray[i]);
                continue;
            }

            if (stack.peek() < targetArray[i]) {
                stack.push(targetArray[i]);
            } else {
                while (!stack.empty() && stack.peek() <= targetArray[i]) {
                    stack.pop();
                }
                count++;
                stack.push(targetArray[i]);
            }
        }

        System.out.println(count + 1);

        br.close();
    }

    // 풀이 x3 - 문제 코드 그대로 사용하는 것은 당연히 시간 초과되도록 설계되어 있음
    public void solutionx3() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N + 1];
        for (int i = 1; i < A.length; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        boolean changed;
        for (int i = 1; i <= N + 1; i++) { // i = 몇 번째 loop에서 break 됐는지
            changed = false;
            for (int j = 1; j <= N - i; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                    changed = true;
                }
            }

            if (!changed) {
                System.out.println(i);
                break;
            }
        }

        br.close();
    }

    // 풀이 1(책) - 포기하고 책 풀이 확인
    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ValueAndOriginalIndex[] A = new ValueAndOriginalIndex[N];
        for (int i = 0; i < N; i++) {
            A[i] = new ValueAndOriginalIndex(Integer.parseInt(br.readLine()), i);
        }
        Arrays.sort(A);

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, A[i].index - i);
        }

        System.out.println(max + 1);

        br.close();
    }

    static class ValueAndOriginalIndex implements Comparable<ValueAndOriginalIndex> {
        int value;
        int index;

        public ValueAndOriginalIndex(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(ValueAndOriginalIndex o) {
            return this.value - o.value;
        }
    }
}
