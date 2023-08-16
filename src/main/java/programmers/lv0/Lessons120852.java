package programmers.lv0;

import java.util.ArrayList;
import java.util.List;

// lv0 소인수 분해
public class Lessons120852 {
    /*
    * https://st-lab.tistory.com/152, https://blog.hyelie.com/entry/%EC%95%BD%EC%88%98-%EC%86%8C%EC%9D%B8%EC%88%98%EB%B6%84%ED%95%B4-%EC%B5%9C%EB%8C%80%EA%B3%B5%EC%95%BD%EC%88%98%EC%B5%9C%EC%86%8C%EA%B3%B5%EB%B0%B0%EC%88%98-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
    * - 소인수 분해 알고리즘 관련 위 두 블로그 참고할 것
    * - 소인수 분해는 원래 brute forced이다. 조사할 대상은 sqrt로 줄일 수 있을 뿐
    *   - 자연수 n에 대해 n의 어떤 약수 x와 n/x에 대해 min(x, n/x) <= sqrtn이다.
    * */

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

    // 풀이 2 - 풀이 1에서 약수 배열 구하는 메서드 분리
    // 단순 배열만 사용해서 빠르다.
    // ! 이 풀이의 문제점 getDivisor했을 때 정렬되어 있지 않음.
    //   - 특이한 점은 그래도 테스트 케이스들을 모두 통과한다는 점
    public int[] solution2(int n) {
        int[] divisors = getDivisor(n);

        int countPrimeFactor = 0;
        int[] tmpArray = new int[divisors.length];
        for (int i = 0; i < divisors.length; i++) {
            if (isPrime(divisors[i])) {
                tmpArray[countPrimeFactor++] = divisors[i];
            }
        }

        int[] primeFactors = new int[countPrimeFactor];
        for (int i = 0; i < countPrimeFactor; i++) {
            primeFactors[i] = tmpArray[i];
        }

        return primeFactors;
    }
    private int[] getDivisor(int number) {
        int count = 0;
        int sqrtNumber = (int) Math.sqrt(number);
        int[] tmpArray = new int[sqrtNumber * 2];

        for (int i = 1; i <= sqrtNumber; i++) {
            if (number % i == 0) {
                if (i * i == number) {
                    tmpArray[count++] = i;
                } else {
                    tmpArray[count++] = i;
                    tmpArray[count++] = number / i;
                }
            }
        }

        int[] divisors = new int[count];
        for (int j = 0; j < count; j++) {
            divisors[j] = tmpArray[j];
        }

        return divisors;
    }
    private boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }
        int sqrtNumber = (int) Math.sqrt(number);
        for (int i = 2; i <= sqrtNumber; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
