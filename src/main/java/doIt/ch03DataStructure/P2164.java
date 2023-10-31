package doIt.ch03DataStructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Do it 코딩테스트 ch3-문제 013 카드 게임(문제 제목: 카드2) // P2164
/*
 * Queue
 * - 삽입과 삭제 연산이 선입선출(FIFO) 방식인 자료구조 -> 삽입과 삭제가 양방향에서 발생
 * - 위치
 *   - rear: 큐의 가장 끝 데이터를 가리킴 - add가 일어나는 곳
 *   - front: 큐의 가장 앞 데이터를 가리킴 - poll, peek이 일어나는 곳
 * - 연산
 *   - add: rear 위치에 새로운 데이터를 삽입
 *   - poll: front 위치의 데이터를 확인하면서 삭제
 *   - peek: front 위치의 데이터를 확인만
 * - 너비 우선 탐색(BFS: Breadth First Search)에 효과적
 * - (cf. 우선순위 큐 priority queue 문제 014(P11286) 및 6장 관련)
 * */
public class P2164 {
    // 큐의 선입선출 성질 사용

    // 풀이 1 - IntStream으로 1 ~ n 링크드리스트 만들기
    public void solution1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> queue = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toCollection(LinkedList::new));

        while (queue.size() >= 2) {
            queue.poll();
            /*
            Integer tmp = queue.poll();
            queue.add(tmp);
            // 그냥 아래처럼 변수 인라인화(Ctrl + Alt + N)
            */
            queue.add(queue.poll());
        }

        System.out.println(queue.poll());

        sc.close();
    }

    // 풀이 2(책)
    public void solution2() {
        Scanner sc = new Scanner(System.in);
        
        Queue<Integer> queue = new LinkedList<>();
        int N = sc.nextInt();
        
        for (int i = 1; i < N; i++) { // 카드를 큐에 저장하기
            queue.add(i);
        }
        
        while (queue.size() > 1) { // 카드가 1장 남을 때까지 반복
            queue.poll(); // 맨 위의 카드 버리기
            queue.add(queue.poll()); // 맨 위의 카드를 가장 아래 카드 밑으로 이동
        }
        
        System.out.println(queue.poll()); // 마지막 남은 카드 출력
        sc.close();
    }
}
