package programmers.lv0;

import java.util.stream.IntStream;

// lv0 배열에서 문자열 대소문자 변환하기
public class Lessons181875 {
    /*
    * - 배열을 옮긴 stream에서 홀짝 인덱스를 구분하는 방벙
    *   -> IntStream.range(~)로 시작, mapToObj(~) 사용
    * - stream.toArray(IntFunction<A[]> generator)의 사용
    * */

    // 풀이 1
    public String[] solution1(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (i % 2 != 0) {
                strArr[i] = strArr[i].toUpperCase();
            } else {
                strArr[i] = strArr[i].toLowerCase();
            }
            // 삼항 연산자 가능
            // strArr[i] = (i % 2 == 0 ? strArr[i].toLowerCase() : strArr[i].toUpperCase());
        }
        return strArr;
    }

    // 풀이 2(다른 풀이 참고)
    public String[] solution2(String[] strArr) {
        return IntStream.range(0, strArr.length)
                .mapToObj(i -> i % 2 == 0 ? strArr[i].toLowerCase() : strArr[i].toUpperCase())
                .toArray(String[]::new);
    }
}
