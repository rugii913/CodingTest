package programmers.lv0;

import java.util.ArrayList;
import java.util.List;

// lv0 소인수 분해
// 120846 isPrime 함수 바꿔야 함...
/*
*
*
*
*
* */
public class Lessons120852 {

    // 풀이 1 - 8점...?
    // 1. 약수를 찾는다. 2 ~ n brute force
    // 2. 약수 중 합성수가 아닌 것을 찾는다. Lessons120846 참고
    public int[] solution1(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
            }
        }

        /*
        int length = list.size(); 
        for (int j = 0; j < length; j++) {
            int divisor = (int) list.get(j);
            if (isPrime(divisor)) {
                list.remove(j);
            }
        }
        */

        /*
        // 위에서 오류나서 아래처럼 작성했는데도 오류... remove(object)로 내부적으로는 인덱스 방식으로 작동하는 건가..?
        for (Integer divisor : list) { // Integer를 (int)로 캐스팅 명시하지 않고 가능하긴 함...
            if (!isPrime(divisor.intValue())) {
                list.remove(divisor);
            }
        }
        */

        // 앞부터 삭제하는 건 인덱스에 문제가 생김, 삭제 작업은 뒤부터 진행해야 한다.
        int length = list.size();
        for (int j = length - 1; j >= 0; j--) {
            // int divisor = (int) list.get(j); 명시적으로 (int) 캐스팅할 필요 없음
            int divisor = list.get(j);
            if (!isPrime(divisor)) {
                list.remove(j);
            }
        }

        int[] answer = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            // answer[k] = (int) list.get(k); 명시적으로 (int) 캐스팅할 필요 없음
            answer[k] = (int) list.get(k);
        }
        return answer;
    }
    private boolean isPrime(int i) {
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}
