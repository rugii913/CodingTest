package doIt.ch03DataStructure;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

// Do it 코딩테스트 ch3-문제 011 스택으로 오름차순 수열 만들기(문제 제목: 스택 수열) // P1874
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

    // 풀이 1(책))
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
}
