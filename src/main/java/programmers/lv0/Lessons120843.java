package programmers.lv0;

import java.util.LinkedList;
import java.util.Queue;

// lv0 공 던지기
public class Lessons120843 {
    /*
    * - circular linked list 구현하려고 하다가 중단
    *
    * - LinkedList<E> implements Deque<E> extends Queue<E>라서 LinkedList로 Queue를 바로 구현할 수 있다!
    *   => Queue 사용한 풀이 2 다른 풀이 참고 숙지할 것
    * */

    // 실패 - 정확성 95.7, 2개는 왜 실패하는 걸까...
    //    1 + 2 = 3
    //    1 + 4 = 5 -> 5 % 4 = 1
    //    1 + 6 = 7 -> 7 % 4 = 3
    //    1 + 8 = 9 -> 9 % 4 = 2
    public int solutionx(int[] numbers, int k) {
        int length = numbers.length;
        return (1 + 2 * (k - 1)) % length;
    }

    // 풀이 1 - 별로 다른 게 없는데 이건 왜 성공하는 걸까... // 8점...?
    public int solution1(int[] numbers, int k) {
        return numbers[2 * (k - 1) % numbers.length];
    }

    // 풀이 2(다른 풀이 참고) - Queue 사용
    public int solution2(int[] numbers, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for (int num : numbers) {
            queue.add(num);
        }
        /*
        int cnt = 1;
        while(cnt !=k){
            queue.add(queue.poll());
            queue.add(queue.poll());
            cnt++;
        }
        */
        for (int i = 1; i < k; i ++) { // 앞에서 뽑고 뒤로 추가 * 2
            queue.add(queue.poll());
            queue.add(queue.poll());
        }
        return queue.poll();
    }

    // 풀이 x - CircularList 구현해보려다가 중단
    public int solutionxx(int[] numbers, int k) {
        return 1;
    }
    class PseudoCircularLinkedList<T> {
        Node<T> head;

        public PseudoCircularLinkedList(T[] a) {
            head = new Node<>();
        }

        public void add(T item) {
        }
    }
    class Node<T> {
        Node next;
        T item;

        public Node() {
        }

        public Node(Node next, T item) {
            this.next = next;
            this.item = item;
        }
    }
}
