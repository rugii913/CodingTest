package programmers.lv0;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

// lv0 잘라서 배열로 저장하기
public class Lessons120913 {
    /*
    * ** 풀이 1 double 결과가 필요한 경우의 연산 유의
    * */

    // 풀이 1 - Math.min(~) 사용
    // ** int numOfSubstrings = (int) Math.ceil(originalLength / n)처럼 작성하지 않도록 유의
    public String[] solution1(String my_str, int n) {
        int originalLength = my_str.length();
        // int numOfSubstrings = (int) Math.ceil(originalLength / n); // -> 이렇게 하면 안 된다.
        // => 위처럼 두면 originalLength / n에서 이미 내림한 int가 나와서 제대로된 결과가 나오지 않음
        int numOfSubstrings = (int) Math.ceil((double) originalLength / n);

        String[] answer = new String[numOfSubstrings];

        for (int i = 0; i < numOfSubstrings; i++) {
            answer[i] = my_str.substring(i * n, Math.min((i + 1) * n, originalLength));
        }
        return answer;
    }

    // 풀이 1-1(다른 풀이 참고) - 코드가 매우 짧고 실행 속도도 빠르다.
    public String[] solution1_1(String my_str, int n) {
        String[] answer = my_str.length() % n == 0 ? new String[my_str.length() / n] : new String[my_str.length() / n + 1];

        for (int i = 0; i < answer.length; i++) {
            // (1)
            answer[i] = i * n + n <= my_str.length() - 1 ? my_str.substring(i * n, i * n + n) : my_str.substring(i * n);
            // (2) answer[i] = (i + 1) * n <= my_str.length() ? my_str.substring(i * n, (i + 1) * n) : my_str.substring(i * n);
            // (3) answer[i] = (i + 1) * n < my_str.length() ? my_str.substring(i * n, (i + 1) * n) : my_str.substring(i * n);
            // 위 셋 모두 통과됨
        }
        return answer;
    }
    // # 풀이 1이나 풀이 2 같은 경우엔 (endIndex는 substring에 포함되지 않은 것을 활용하여 생각)
    //   -> 계산된 endIndex(startIndex + n 혹은 (i + 1) * n)와 length를 비교하여 더 작은 값을 선택
    // # 풀이 1_1 (1) 같은 경우
    //   -> 인덱스 i인 substring의 계산된 endIndex(i * n + n) > my_str.length() - 1일 때 my_str.substring(i * n) 적용
    //      (ex1) my_str.length() = 24, n = 6에서 i = 3일 때 my_str.substring(i * n) 적용
    //      (ex2) my_str.length() = 25, n = 6에서 i = 4일 때 my_str.substring(i * n) 적용
    //   -> 마지막 substring에는 endIndex를 아예 주지 않음
    //   ===> (2)도 통과되는 이유는 endIndex(i * n + n) > my_str.length() 경계값을 생각해보면
    //      (ex1) my_str.length() = 24, n = 6에서 i = 3이라서 my_str.substring(i * n, (i + 1) * n) 적용 - 그런데 어차피 결과는 같다.
    //      (ex2) my_str.length() = 25, n = 6에서 i = 4일 때 my_str.substring(i * n) 적용
    //   ===> (3)도 통과되는 이유는 endIndex(i * n + n) >= my_str.length() 경계값을 생각해보면 (정수이기 때문에 어차피 (1)과 같음)
    //      (ex1) my_str.length() = 24, n = 6에서 i = 4일 때 my_str.substring(i * n) 적용
    //      (ex2) my_str.length() = 25, n = 6에서 i = 4일 때 my_str.substring(i * n) 적용
    //   -> (1), (3) vs. (2)의 차이는 배열이 꽉 찬 경우를 어떻게 처리할 거냐의 차이

    // 풀이 2(다른 풀이 참고)
    public String[] solution2(String my_str, int n) {
        int resultCnt = (my_str.length() + n - 1) / n;
        // (꼼수) kn + 1 ~ k(n + 1) -> k로 mapping 되는 함수라고 생각해보자.
        // 위 코드는 아주 간결하지만, 이해하기 쉬운 코드를 위해서는 다른 방식을 택하는 게 나을 것 같다.
        String[] answer = new String[resultCnt];

        for (int i = 0; i < resultCnt; i++) {
            int start = n * i;
            int end = start + n >= my_str.length() ? my_str.length() : start + n;
            // int end = Math.min(start + n, my_str.length()); 와 같다.
            answer[i] = my_str.substring(start, end);
        }

        return answer;
    }

    // 풀이 3(다른 풀이 참고) - stream
    public String[] solution3(String my_str, int n) {
        return IntStream.range(0, my_str.length() / n + (my_str.length() % n > 0 ? 1 : 0))
                .mapToObj(i -> i == my_str.length() / n ? my_str.substring(i * n) : my_str.substring(i * n, (i + 1) * n))
                .toArray(String[]::new);
    }

    // 풀이 4(다른 풀이 참고) - list 사용 - String + String 하는 방식에 stream으로 toArray로 만들어서 느리다.
    // 직관적이긴 하다.
    public String[] solution4(String my_str, int n) {
        List<String> answer = new ArrayList<>();
        String str = "";
        for (int i = 0; i < my_str.length(); i++) {
            str += my_str.charAt(i);
            if (str.length() == n || i == my_str.length() - 1) {
                answer.add(str);
                str = "";
            }
        }

        return answer.stream().toArray(String[]::new);
    }
}
