package doIt.ch03DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// Do it 코딩테스트 ch3-문제 014 절댓값 힙 구현하기(문제 제목: 절댓값 힙) // P11286
/*
* Priority Queue
* - 값이 들어간 순서와 상관 없이 우선순위가 높은 데이터가 front에 위치함
* - 일반적으로 힙(Heap)을 이용해 구현함 - 힙은 트리의 한 종류 -> 6장
* */
public class P11286 {
    // 첫번째 줄 입력: 연산의 개수 N(1 <= N <= 100,000)
    // 2 ~ n+1번째 입력: 연산들 - 0이 아니면 배열에 가장 작은 값을 출력하고 그 값을 배열에서 제거, 0이면 가장 작은 수를 출력(배열이 비어있는 경우 0을 출력)

    /*
    * java.util.PriorityQueue 그대로 사용하되, 정렬 기준(comparator) 넘겨서 새로 정의해서 사용하기
    * */

    /*
    * 시간 복잡도 관련
    * N 최대 10^5, 제한 시간 2초(2 * 10^8) => O(nlogn) 사용 가능 => 데이터 삽입될 때마다 정렬 필요한 우선순위 큐도 사용 가능
    * */

    // 풀이 1
    public void solution1() throws IOException {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>(
                (o1, o2) -> {
                    if (Math.abs(o1) < Math.abs(o2)) {
                        return -1;
                    } else if (Math.abs(o1) > Math.abs(o2)) {
                        return 1;
                    } else {
                        return o1.compareTo(o2);
                    }
                }
        );

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            long number = Long.parseLong(br.readLine());

            if (number == 0) {
                sb.append(priorityQueue.isEmpty() ? 0 : priorityQueue.poll()).append("\n");
            } else {
                priorityQueue.add(number);
            }
        }

        System.out.println(sb);

        br.close();
    }

    // 풀이 2(책) - Math.abs(o1) != Math.abs(o2)일 때, firstAbs - secondAbs 방식으로 처리 참고
    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int firstAbs = Math.abs(o1);
            int secondAbs = Math.abs(o2);
            if (firstAbs == secondAbs) {
                return o1 > o2 ? 1 : -1; // 절댓갑이 같으면 음수 우선 정렬(음수가 왼쪽)
                // 위 (o1, o2) 부분에 IDE 경고 신경 쓰이면 아래 코드로 바꿀 수 있다.
                // return Integer.compare(o1, o2);
            } else {
                return firstAbs - secondAbs; // 절댓값을 기준으로 정렬
            }
        });

        for (int i = 0; i < N; i++) {
            int request = Integer.parseInt(br.readLine());
            if (request == 0) {
                if (queue.isEmpty()) {
                    System.out.println("0");
                } else {
                    System.out.println(queue.poll());
                }
            } else {
                queue.add(request);
            }
        }

        br.close();
    }
}
