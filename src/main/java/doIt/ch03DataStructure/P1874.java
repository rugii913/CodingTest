package doIt.ch03DataStructure;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

// Do it 코딩테스트 ch3-문제 011 스택으로 오름차순 수열 만들기(문제 제목: 스택 수열) // P1874
/*
* Stack
* - 삽입과 삭제 연산이 후입선출(LIFO) 방식인 자료구조 -> 삽입과 삭제가 한 쪽에서만 발생한다.
* - 위치
*   - top: 삽입과 삭제가 일어나는 위치
* - 연산
*   - push: top에 새 데이터를 삽입
*   - pop: top의 데이터를 확인하면서 삭제
*   - peek: top의 데이터를 확인만
* - 깊이 우선 탐색(DFS: Depth First Search), 백트래킹 종류에 효과적
*   - 후입선출 개념 자체가 재귀 함수 알고리즘 원리와 같다.
* - *** 스택의 후입선출이라는 성질이 시간 복잡도를 줄이거나 특정 문제 조건을 해결하는 실마리가 될 때가 있다. -> 문제 12(P17298)
* */
public class P1874 {
    /*
    * 많이 반성해야될 듯... - 스택 정도는 껌이지 생각했는데, 3시간을 붙잡고 있어도 구현을 제대로 못했고, 아이디어가 생각나서 구현한 코드는 실패했다.
    * - 잘 모르겠으면 자료구조를 그림으로 그려서 어떤 데이터가 어떻게 들어가고 나가는지 생각해보는 게 좋다.
    * - 수도 코드도 정리하는 데에 도움이 될 것
    * */
    // - 실패한 풀이들 어떻게 성공시킬 수 있을지 고민해보자.
    // 그와는 별개로 책의 질은 별로 좋지 않은 듯...

    // 풀이 x1 풀던 도중 포기
    public void solutionx1() throws IOException {
        String plus = "+\n";
        String minus = "-\n";
        int countPlus = 0;
        int countMinus = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int pushTarget = 1;
        int countPushed = 0;
        int countPopped = 0;
        for (int i = 0; i < n; i++) {
            int popped = Integer.parseInt(br.readLine());

            if (popped > pushTarget) {
                countPushed = popped - pushTarget;
                pushTarget = popped + 1;

                for (int j = 0; j < countPushed; j++) {
                    bw.write(plus);
                }

                countPlus += countPushed;
                countPopped++;
                countMinus++;
            } else {
                bw.write(minus);
                countPopped++;
                countMinus++;
            }
        }

        bw.flush();
        bw.close();
    }

    // 풀이 x2 실패 - 82%에서 틀림
    public void solutionx2() throws IOException {
        String plus = "+\n";
        String minus = "-\n";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int lastPushed = 0;
        int lastPopped = 0;
        boolean isImpossible = false;
        for (int i = 0; i < n; i++) {
            int popped = Integer.parseInt(br.readLine());

            if (popped > lastPopped && popped < lastPushed) {
                isImpossible = true;
                // sb = new StringBuilder("NO\n");
                // break;
            }

            if (popped > lastPushed) {
                int pushedCount = popped - lastPushed;
                sb.append(plus.repeat(pushedCount));
                lastPushed = popped;

                sb.append(minus);
                lastPopped = popped;
            } else {
                sb.append(minus);
                lastPopped = popped;
            }
        }

        if (isImpossible) {
            sb = new StringBuilder("NO\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);

        br.close();
    }

    // 풀이 x3 - 중복되는 부분을 좀 더 제거했으나 여전히 82%에서 실패
    public void solutionx3() throws IOException {
        String plus = "+\n";
        String minus = "-\n";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int lastPushed = 0;
        int lastPopped = 0;
        boolean isImpossible = false;
        for (int i = 0; i < n; i++) {
            int popped = Integer.parseInt(br.readLine());

            if (popped > lastPushed) {
                int pushedCount = popped - lastPushed;
                sb.append(plus.repeat(pushedCount));
                lastPushed = popped;
            } else if (popped > lastPopped) { // popped <= lastPushed로 이미 걸러진 상태이고, 같은 수가 두 번 나오지는 않으므로 popped < lastPushed이다.
                isImpossible = true;
            }
            sb.append(minus);
            lastPopped = popped;
        }

        if (isImpossible) {
            sb = new StringBuilder("NO\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);

        br.close();
    }

    // 풀이 1(책)
    public void solution1() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
        StringBuffer bf = new StringBuffer();
        int num = 1; // 오름차순 수 stack에 push할 target
        boolean result = true;
        for (int i = 0; i < A.length; i++) {
            int su = A[i]; // 현재 수열의 수
            if (su >= num) { // 현재 수열 값 su >= 오름차순 자연수 num인 경우 => num이 su보다 커질 때까지 push(num++)
                while (su >= num) {
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            } else { // 현재 수열 값 su < 오름차순 자연수 num인 경우 => pop()으로 stack의 원소를 꺼냄 => 이 꺼낸 원소가 수열의 su보다 크면 수열을 출력할 수 없다.
                int n = stack.pop();
                if (n > su) { // if (n != su) { 로 바꿔도 같다.
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    bf.append("-\n");
                }
            }
        }
        if (result) {
            System.out.println(bf);
        }
    }

    // 풀이 1-1 - 풀이 1 책 구현 코드 확인 후 다시 구현 // 소요시간 풀이 1보다 많이 줄어듦
    public void solution1_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int nextPushTarget = 1;
        for (int number : sequence) {
            while (nextPushTarget <= number) {
                stack.push(nextPushTarget++);
                sb.append("+\n");
            }

            Integer popped = stack.pop();
            if (popped != number) {
                sb = new StringBuilder("NO");
                break;
            }
            sb.append("-\n");
        }

        System.out.println(sb);
        br.close();
    }

    // 풀이 2 - 책 구현 코드와 그 전까지 실패한 방식을 적절하게 섞으려고 했다. 근데 풀어 1-1과 큰 차이는 없는 듯하다.
    public void solution2() throws IOException {
        String plus = "+\n";
        String minus = "-\n";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int lastPushed = 0;
        for (int i = 0; i < n; i++) {
            int sequenceNumber = Integer.parseInt(br.readLine());

            /*
            // 아래 while로 개량
            if (sequenceNumber > lastPushed) {
                for (int j = lastPushed + 1; j <= sequenceNumber; j++) {
                    stack.push(j);
                    lastPushed++;
                    sb.append("+\n");
                }
            }
             */

            while (lastPushed < sequenceNumber) {
                stack.push(++lastPushed);
                sb.append(plus);
            }

            if (sequenceNumber != stack.peek()) {
                sb = new StringBuilder("NO\n");
                break;
            }

            stack.pop();
            sb.append(minus);
        }

        System.out.print(sb);

        br.close();
    }

    // 풀이 2x1 - 스택 직접 사용, 책 구현 코드와 그 전까지 실패한 방식을 적절하게 섞으려고 한 다른 시도 - 여전히 실패함
    // - lastPushed와 lastPopped를 사용하는 로직에 문제가 있는 것 같다.
    public void solution2x1() throws IOException {
        String plus = "+\n";
        String minus = "-\n";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int lastPushed = 0;
        int lastPopped = 0;
        boolean isImpossible = false;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int sequenceNumber = Integer.parseInt(br.readLine());
            if (sequenceNumber > lastPushed) {
                for (int j = lastPushed + 1; j <= sequenceNumber; j++) {
                    stack.push(j);
                    sb.append(plus);
                }
                lastPushed = sequenceNumber;
            } else if (sequenceNumber > lastPopped) { // popped <= lastPushed로 이미 걸러진 상태이고, 같은 수가 두 번 나오지는 않으므로 popped < lastPushed이다.
                isImpossible = true;
            }
            sb.append(minus);
            lastPopped = sequenceNumber;
        }

        if (isImpossible) {
            sb = new StringBuilder("NO\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);

        br.close();
    }
}
