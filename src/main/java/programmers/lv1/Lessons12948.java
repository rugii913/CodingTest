package programmers.lv1;

import java.util.stream.LongStream;

// lv1 핸드폰 번호 가리기
public class Lessons12948 {

    // 풀이 1
    public String solution1(String phone_number) {
        int maskingLength = phone_number.length() - 4;
        // return new String("*").repeat(maskingLength).concat(phone_number.substring(maskingLength)); // 불필요한 new String(~) 사용
        return "*".repeat(maskingLength).concat(phone_number.substring(maskingLength));
    }

    // 풀이 2(다른 풀이 참고) - 깔끔
    public String solution2(String phone_number) {
        char[] charArray = phone_number.toCharArray();
        for (int i = 0; i < charArray.length - 4; i++) {
            charArray[i] = '*';
        }
        return String.valueOf(charArray);
    }

    // 풀이 3(다른 풀이 참고) - 정규표현식 전방 탐색
    public String solution3(String phone_number) {
        return phone_number.replaceAll(".(?=.{4})", "*");
        // 정규표현식 전방 탐색
        // . 임의의 모든 문자
        // () 그룹화 - 포획 괄호 (?=X ) X, via zero-width positive lookahead --> x(?=y) y가 뒤따라오는 x에만 대응
        // X{n, m} X가 n번 이상, m번 이하 - X{n,} X가 n번 이상 - X{n} X가 n번 // cf. 수량 한정자 별표(*)는 {0, }과, 플러스(+)는 {1, }과, 물음표(?)는 {0, 1} 과 동일
        //   => .{4} 임의의 문자 4개
        // => .(?=.{4}) 임의의 문자 4개가 뒤따라오는 임의의 문자를 그룹화해서 선택
        // 참고 링크
        // - https://jjnooys.medium.com/javascript-%EC%A0%95%EA%B7%9C%ED%91%9C%ED%98%84%EC%8B%9D-729fd1911747
        //   => https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide/Regular_expressions
        //   => 테스트 해볼 수 있는 사이트 https://regexr.com/
        //   => 학습 관련 사이트들 나무위키 링크 https://namu.wiki/w/%EC%A0%95%EA%B7%9C%20%ED%91%9C%ED%98%84%EC%8B%9D#s-4
    }
}
