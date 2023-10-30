package doIt.ch03DataStructure;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

// Do it 코딩테스트 ch3-문제 012 오큰수 구하기(문제 제목: 오큰수) // P17298
public class P17298 {
    /*
     * 각 풀이 비교
     * - 풀이 1: 문제 10(P11003)과 비슷한 느낌 풀이, 배열의 뒤에 있는 수 중 더 작은 수는 무시
     *   - 메모리 178,140 KB, 시간 1,176 ms
     * - 풀이 2(책): 스택에 배열의 값이 아닌 index를 저장, 
     *              -> (인덱스 하나씩 증가시키며 탐색해서 값들 비교) 스택에 쌓여 인덱스의 값과 비교, 쌓인 인덱스의 값이 더 작으면 pop() 작업, 더 크면 pop() 작업 중단하고 다음 인덱스로
     *              -> 마지막까지도 스택에 쌓여있는 인덱스들은 그 다음 값들에 더 큰 값이 없는 위치이므로 전부 pop 하면서 모두 -1을 할당해줌
     *                 (cf) 어떤 인덱스 index 2보다 더 먼저 스택에 들어온 인덱스 index 1은 arr[index1] >= arr[index2]임이 보장됨 <- arr[index2]와 비교했을 때에도 pop()이 적용되지 않았다는 뜻이므로
     *   - 메모리 169,628 KB, 시간 1,052 ms
     */
    /*
     * 시간 복잡도 관련
     * N 최대 10^6, 제한 시간 1초(1 * 10^8)
     * -> O(N^2) 사용 불가
     */

    // 풀이 x1 - 시간 복잡도를 고려하지 않고 이중 for 사용 - 당연히 시간초과
    public void solutionx1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] numberStrings = br.readLine().split("\\s");

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(numberStrings[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            boolean isAppended = false;
            for (int j = i; j < N; j++) {
                if (A[i] < A[j]) {
                    sb.append(A[j]);
                    sb.append(" ");
                    isAppended = true;
                    break;
                }
            }
            if (!isAppended) {
                sb.append(-1);
                sb.append(" ");
            }
        }

        System.out.println(sb);
        br.close();
    }

    // 풀이 x2 - 뒤에 있는 것 중 더 작은 수는 무시 - 시간 초과
    public void solutionx2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] numberStrings = br.readLine().split("\\s");

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(numberStrings[i]);
        }

        int[] NGE = new int[N];
        Stack<Integer> stack = new Stack<>();

        NGE[N - 1] = -1;
        stack.push(A[N - 1]);

        int top;
        for (int i = N - 2; i >= 0; i--) {
            int number = A[i];
            while (!stack.empty()) { // 반복문 두 번 꼴이긴 하지만, 탐색 대상을 줄인 것...-이라고 생각했는데 break;를 안 붙여서 그냥 두 번 반복하는 거였다... -> 풀이 1로 수정
                top = stack.pop();
                if (number < top) {
                    NGE[i] = top;
                    stack.push(top);
                    stack.push(number);
                }
            }

            if (stack.empty()) {
                NGE[i] = -1;
                stack.push(number);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(NGE[i]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    // 풀이 x3 - stream으로 바꾸고 NGE 배열 없이 바로 sb 쓰려고 했지만 실패 - 쓸데없이 insert 쓰고, " "을 + 연산으로 붙이고 해서 효율이 더 안 좋은 듯...
    public void solutionx3() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        sb.append(-1);
        stack.push(A[N - 1]);

        int top;
        for (int i = N - 2; i >= 0; i--) {
            int number = A[i];
            while (!stack.empty()) { // 반복문 두 번 꼴이긴 하지만, 탐색 대상을 줄인 것
                top = stack.pop();
                if (number < top) {
                    sb.insert(0, top + " ");
                    stack.push(top);
                    stack.push(number);
                }
            }

            if (stack.empty()) {
                sb.insert(0, -1 + " ");
                stack.push(number);
            }
        }

        System.out.println(sb);
        br.close();
    }

    // 풀이 1 - 뒤에 있는 것 중 더 작은 수는 무시, 3시간 정도 걸린 듯... - P11003 최솟값 찾기 슬라이딩 윈도우와 비슷한 느낌으로 풀이
    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] numberStrings = br.readLine().split("\\s");

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(numberStrings[i]);
        }

        int[] answer = new int[N];
        Stack<Integer> stack = new Stack<>();

        /*
        answer[N - 1] = -1;
        stack.push(A[N - 1]);
        // 이 부분은 아래에 이미 포함되어 있다. 넣으려면 i = N - 2부터 시작하는 것으로 바꾸기
         */

        int top;
        for (int i = N - 1; i >= 0; i--) {
            int number = A[i];
            while (!stack.empty()) { // 반복문 두 번 꼴이긴 하지만, 탐색 대상을 줄인 것 - 풀이 x2에서 실수한 부분 수정, 중간에 break로 빠져나올 수 있도록 함
                top = stack.pop();
                if (number < top) {
                    answer[i] = top;
                    stack.push(top);
                    stack.push(number);
                    break;
                }
            }

            if (stack.empty()) {
                answer[i] = -1;
                stack.push(number);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    // 풀이 1_1 - 풀이 1 개량(입력 받을 때 stream 사용) - 더 느림
    public void solution1_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

        int[] answer = new int[N];
        Stack<Integer> stack = new Stack<>();

        int top;
        for (int i = N - 1; i >= 0; i--) {
            int number = A[i];
            while (!stack.empty()) {
                top = stack.pop();
                if (number < top) {
                    answer[i] = top;
                    stack.push(top);
                    stack.push(number);
                    break;
                }
            }

            if (stack.empty()) {
                answer[i] = -1;
                stack.push(number);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    // 풀이 1_2 - 풀이 1 개량(Arrays.toString(~) 사용) - 더 느림...
    public void solution1_2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

        int[] answer = new int[N];
        Stack<Integer> stack = new Stack<>();

        int top;
        for (int i = N - 1; i >= 0; i--) {
            int number = A[i];
            while (!stack.empty()) {
                top = stack.pop();
                if (number < top) {
                    answer[i] = top;
                    stack.push(top);
                    stack.push(number);
                    break;
                }
            }

            if (stack.empty()) {
                answer[i] = -1;
                stack.push(number);
            }
        }

        String tmp = Arrays.toString(answer).replace(",", "");
        System.out.println(tmp.substring(1, tmp.length() - 1));

        br.close();
    }

    // 풀이 2(책) - 내 풀이보다 조금 더 빠르긴 하다. - 핵심은 클래스 윗 부분에 정리
    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n]; // 수열 배열 생성
        int[] ans = new int[n]; // 정답 배열 생성
        
        String[] str = br.readLine().split(" "); // ? 왠 일로 StringTokenizer가 아니라 split을 사용함
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(str[i]);
        }

        Stack<Integer> stack = new Stack<>(); // 인덱스가 들어가는 stack
        // stack.push(0); // 처음에는 항상 스택이 비어 있으므로 최초 값을 push해서 초기화 // 여기서 초기화 안 해도 아래 for에서 i = 0부터 돌리면 초기화 함
        for (int i = 0; i < n; i++) {
            // 스택이 비어 있지 않고 현재 수열이 스택 top 인덱스가 가리키는 수열보다 클 경우
            while (!stack.isEmpty() && A[stack.peek()] < A[i]) {
                ans[stack.pop()] = A[i]; // 정답 배열에 오큰수를 현재 수열로 저장하기
            }
            stack.push(i); // 신규 데이터 push
        }

        while (!stack.empty()) {
            // 스택이 빌 때까지 스택에 쌓은 index에 -1 넣기
            ans[stack.pop()] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            bw.write(ans[i] + " ");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
