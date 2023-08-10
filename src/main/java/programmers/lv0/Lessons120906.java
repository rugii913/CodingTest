package programmers.lv0;

import java.util.Arrays;

// lv0 자릿수 더하기
public class Lessons120906 {

    /*
    * 1. 굳이 반복문을 두 번 쓸 필요도 없었고, int[]를 하나 선언할 필요도 없었음
    *    더 간결한 코드를 생각해보자.
    * 2. Java 이중 콜론 :: - 메서드 참조 표현식(method reference expression)
    *    [인스턴스]::[메서드명(또는 new)]로 사용 (static 메서드인 경우 [인스턴스] 대신 [클래스]로 사용 가능
    *    - 람다 표현식에서 람다식이 건네는 파라미터와 받는 쪽의 파라미터가 동일할 때 축약해서 쓸 수 있다.
    * */

    // 풀이 1 - 반복문이 두 번, int[]
    public int solution1(int n) {
        int[] digitArray = new int[6];

        int i = 0;
        while (n > 0) {
            digitArray[i++] = n % 10;
            n = n / 10;
        }

        int answer = 0;
        for (int j = 0; j < digitArray.length; j++) {
            answer += digitArray[j];
        }

        return answer;
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(int n) {
        int answer = 0;

        while (n > 0) {
            answer += n % 10;
            n /= 10;
        }

        return answer;
    }

    // 풀이 3(다른 풀이 참고, 스트림 사용)
    public int solution3(int n) {
        return Arrays.stream(String.valueOf(n).split("")).mapToInt(Integer::parseInt).sum();
    }
}
