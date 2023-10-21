package doIt.ch03DataStructure;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

// Do it 코딩테스트 ch3-문제 010 최솟값 찾기(문제 제목: 최솟값 찾기) // P11003
public class P11003 {
    // 첫번째 줄 입력: 숫자 개수 N, 기준 인덱스 L (1 <= L <= N <= 5,000,000)
    // 두번째 줄 입력: N개의 수(A1, A2, ... AN) (-10^9 <= Ai <= 10^9)
    //
    // A(i-L+1) ~ Ai 중 최솟값을 Di라 할 때, 모든 Di를 출력(i <= 0인 Ai는 무시)
    // => ex. N = 5, L = 3이라고 하면 => i = 0일 때, D0은 A-2 ~ A0 중 최솟값 (그런데 A-2, A-1은 무시)
    //                              => i = 2일 때, D2은 A0 ~ A2 중 최솟값
    //                              => i = 3일 때, D3은 A1 ~ A3 중 최솟값 - *i = L일 때부터 버리기 시작
    //                              => i = 4일 때, D4는 A2 ~ A4 중 최솟값
    //                              => 인덱스 (i를 포함한) i 이하의 N개의 수 중 최솟값을 찾아내는 것이다.
    
    /*
    * 슬라이딩 윈도우
    * - Deque로 마치 정렬을 사용한 듯한 효과를 얻을 수 있다.
    *   - 포인트는 구간을 어떻게 잡더라도 슬라이딩 윈도우의 특성 상,
    *     i < j인 i, j 인덱스가 함께 있는 윈도우에서 numbers[i] > numbers[j]이면, numbers[i]가 최솟값이 될 일은 없으므로, 아예 deque의 관련 원소를 지워버리면 된다는 점
    *   - 나머지는 trivial하다, Deque의 맨 앞에 있는 node의 인덱스를 확인해서, 제거할 순서가 됐다면 제거해준다.
    * ? 범위에서 벗어난 값이 중간에 껴있는 경우는 없는가?
    *   - Deque를 사용하고 있지만 기본적으로 슬라이딩 윈도우 개념을 차용하여 차례차례 원소들을 넣고 있으므로, 앞에 있을수록 인덱스가 작다.
    * */

    // cf. 이 문제 시간 초과 관련
    // - Java 풀이는 거의 2200 ms를 넘기고, C++로도 거의 1500ms는 넘기는 듯하다.
    // - 3페이지까지 봤을 때는 Java에서 가장 빠른 풀이는 https://www.acmicpc.net/source/66085104인 듯한데, Java 느낌의 코드는 아니다.

    // 풀이 x1 - NavigableMap(TreeMap) 사용 - 시간 초과
    public void solutionx1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s");
        int N = Integer.parseInt(split[0]);
        int L = Integer.parseInt(split[1]);

        String[] numberStrings = br.readLine().split("\\s");
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]);
        }

        int[] D = new int[N];
        NavigableMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < L; i++) {
            // 추가
            map.put(numbers[i], map.getOrDefault(numbers[i], 0) + 1);
            // 최솟값
            D[i] = map.firstKey();
        }

        for (int i = L; i < N; i++) {
            // 제거
            int removeTarget = numbers[i - L];
            Integer removeTargetCountValue = map.get(removeTarget);

            if (removeTargetCountValue == 1) {
                map.remove(removeTarget);
            } else {
                map.put(removeTarget, removeTargetCountValue - 1);
            }

            // 추가
            map.put(numbers[i], map.getOrDefault(numbers[i], 0) + 1);
            // 최솟값
            D[i] = map.firstKey();
        }

        for (int DElement : D) {
            System.out.print(DElement);
            System.out.print(" ");
        }

        br.close();
    }

    // 풀이 x1-1 - 위 풀이에서 for문 한 번 줄이기 시도 - 시간 초과
    public void solutionx1_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s");
        int N = Integer.parseInt(split[0]);
        int L = Integer.parseInt(split[1]);

        String[] numberStrings = br.readLine().split("\\s");
        int[] numbers = new int[N];
        int[] D = new int[N];
        NavigableMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < L; i++) {
            // 추가
            int insertTarget = Integer.parseInt(numberStrings[i]);
            numbers[i] = insertTarget;
            map.put(insertTarget, map.getOrDefault(insertTarget, 0) + 1);
            // 최솟값
            D[i] = map.firstKey();
        }

        for (int i = L; i < N; i++) {
            // 제거
            int removeTarget = numbers[i - L];
            Integer removeTargetCountValue = map.get(removeTarget);

            if (removeTargetCountValue == 1) {
                map.remove(removeTarget);
            } else {
                map.put(removeTarget, removeTargetCountValue - 1);
            }

            // 추가
            int insertTarget = Integer.parseInt(numberStrings[i]);
            numbers[i] = insertTarget;
            map.put(insertTarget, map.getOrDefault(insertTarget, 0) + 1);
            // 최솟값
            D[i] = map.firstKey();
        }

        for (int DElement : D) {
            System.out.print(DElement);
            System.out.print(" ");
        }

        br.close();
    }

    // 풀이 x2 포기 - LinkedList를 어떻게 이용할 수 있지 않을까 고민하다가 어쨌든 탐색, 삽입 시간은 오래걸릴 듯하여 포기함, 그런데...
    public void solutionx2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s");
        int N = Integer.parseInt(split[0]);
        int L = Integer.parseInt(split[1]);

        String[] numberStrings = br.readLine().split("\\s");
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]);
        }

        int[] D = new int[N];
        // ...

        br.close();
    }

    // 책 설명 보고 구현 해보려다가 포기 후 책 구현 코드 확인
    // 풀이 1(책) - Deque 사용
    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력을 버퍼에 넣고 한 번에 출력하기 위해 BufferedWriter 사용

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) { // 새로운 값이 들어올 때마다 정렬 대신 현재 수보다 큰 값을 Deque에서 제거하는 방식으로 시간 복잡도를 줄임
            int now = Integer.parseInt(st.nextToken());

            while (!deque.isEmpty() && deque.getLast().value > now) {
                deque.removeLast();
            }
            deque.addLast(new Node(i, now));

            if (deque.getFirst().index <= i - L) { // 범위에서 벗어난 값은 Deque에서 제거 // 범위에서 벗어난 값이 중간에 껴있는 경우는 없는가? -> 맨 위 주석 참고
                deque.removeFirst();
            }
            bw.write(deque.getFirst().value + " ");
        }

        bw.flush();
        br.close();
    }


    static class Node {
        public int index;
        public int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    // 풀이 1_1(위 풀이 수정) - 마지막 buffer write 부분만
    public void solution1_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력을 버퍼에 넣고 한 번에 출력하기 위해 BufferedWriter 사용

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) { // 새로운 값이 들어올 때마다 정렬 대신 현재 수보다 큰 값을 Deque에서 제거하는 방식으로 시간 복잡도를 줄임
            int now = Integer.parseInt(st.nextToken());

            while (!deque.isEmpty() && deque.getLast().value > now) {
                deque.removeLast();
            }
            deque.addLast(new Node(i, now));

            if (deque.getFirst().index <= i - L) { // 범위에서 벗어난 값은 Deque에서 제거
                deque.removeFirst();
            }
            bw.write(String.valueOf(deque.getFirst().value).concat(" "));
        }

        bw.flush();
        br.close();
    }

    // 풀이 1_2x(위 풀이 수정) - StringTokenizer가 아닌 split으로 바꾸자 시간 초과됨
    // split의 문제라기보다는 제한시간이 2.6초인데 2.4초 정도로 통과하고 있는 상황이기 때문에
    // N이 몹시 큰 배열 for 한 번 더 돌면서 추가 작업이 진행되는 것만으로도 시간 초과가 발생하는 듯하다.
    public void solution1_2x() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] split = br.readLine().split("\\s");
        int N = Integer.parseInt(split[0]);
        int L = Integer.parseInt(split[1]);

        int[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        Deque<Node> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int now = numbers[i];

            while (!deque.isEmpty() && deque.getLast().value > now) {
                deque.removeLast();
            }
            deque.addLast(new Node(i, now));

            if (deque.getFirst().index <= i - L) {
                deque.removeFirst();
            }
            bw.write(String.valueOf(deque.getFirst().value));
            bw.write(" ");
        }

        bw.flush();
        br.close();
    }

    // 풀이 1_3x(위 풀이 수정) - 스트림으로 어떻게 해보려고 했지만 안 됨
    public void solution1_3x() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] split = br.readLine().split("\\s");
        int N = Integer.parseInt(split[0]);
        int L = Integer.parseInt(split[1]);


        Deque<Node> deque = new LinkedList<>();

        AtomicLong atomicLongIndex = new AtomicLong(0);
        Arrays.stream(br.readLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .forEach(i -> {
                            while (!deque.isEmpty() && deque.getLast().value > i) {
                                deque.removeLast();
                            }
                            deque.addLast(new Node(atomicLongIndex.intValue(), i));

                            if (deque.getFirst().index <= atomicLongIndex.getAndIncrement() - L) {
                                deque.removeFirst();
                            }

                            try {
                                bw.write(String.valueOf(deque.getFirst().value).concat(" "));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

        bw.flush();
        br.close();
    }

    // 풀이 1_4(풀이 1-1 수정) - StringBuilder 사용, BufferedWriter는 아예 없음
    // - 가장 빠른 듯하긴 한데 큰 의미는 없는 것 같다...
    public void solution1_4() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> deque = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());

            while (!deque.isEmpty() && deque.getLast().value > now) {
                deque.removeLast();
            }
            deque.addLast(new Node(i, now));

            if (deque.getFirst().index <= i - L) {
                deque.removeFirst();
            }

            sb.append(deque.getFirst().value).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    // 풀이 2(풀이 1-1 수정) - Node가 아니라 int[] 사용 - 책 풀이랑 시간 차이 거의 없음(작동할 때마다 편차는 있겠지만 일단은 이게 더 느림)
    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<int[]> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());

            while (!deque.isEmpty() && deque.getLast()[1] > now) {
                deque.removeLast();
            }
            deque.addLast(new int[]{i, now});

            if (deque.getFirst()[0] <= i - L) {
                deque.removeFirst();
            }
            bw.write(String.valueOf(deque.getFirst()[1]).concat(" "));
        }

        bw.flush();
        br.close();
    }
}
