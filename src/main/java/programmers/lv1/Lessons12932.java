package programmers.lv1;

import java.math.BigInteger;

// lv1 자연수 뒤집어 배열로 만들기
public class Lessons12932 {

    // 풀이 1 // 0.03ms ~ 0.05ms
    public int[] solution1(long n) {
        int arrayLength = (int) Math.log10(n) + 1;

        int[] answer = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            answer[i] = (int) (n % 10);
            n /= 10;
        }

        return answer;
    }

    // 풀이 2 - String 사용 // 0.04ms ~ 0.06ms 풀이 1과 속도 차이 거의 없음
    public int[] solution2(long n) {
        String stringN = String.valueOf(n);
        int length = stringN.length();

        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = Character.getNumericValue(stringN.charAt(length - 1 - i));
        }

        return answer;
    }

    // 풀이 3x - BigInteger 연습 - 테스트 4에서 계속 실패함 -반례를 생각해낼 수가 없다...
    // 참고(BigInteger & BigDecimal 사용법) - https://inpa.tistory.com/entry/JAVA-%E2%98%95-BigInteger-BigDecimal-%EC%9E%90%EB%A3%8C%ED%98%95-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%B4%9D%EC%A0%95%EB%A6%AC
    // cf. 실수 표현(부동 소수점) 원리 한눈에 이해하기 - https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%8B%A4%EC%88%98-%ED%91%9C%ED%98%84%EB%B6%80%EB%8F%99-%EC%86%8C%EC%88%98%EC%A0%90-%EC%9B%90%EB%A6%AC-%ED%95%9C%EB%88%88%EC%97%90-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0
    public int[] solution3x(long n) {
        BigInteger bigN = BigInteger.valueOf(n);

        int arrayLength = 0;
        BigInteger copyBigN = bigN;
        while (copyBigN.intValue() > 0) {
            arrayLength++;
            copyBigN = copyBigN.divide(BigInteger.TEN);
        }

        int[] answer = new int[arrayLength];

        for (int i = 0; i < arrayLength; i++) {
            answer[i] = bigN.remainder(BigInteger.TEN).intValue();
            bigN = bigN.divide(BigInteger.TEN);
        }

        return answer;
    }

    // 풀이 4(다른 풀이 참고)
    public int[] solution4(long n) {
        return new StringBuilder().append(n).reverse().chars().map(Character::getNumericValue).toArray();
    }

    // 풀이 4-1(다른 풀이 참고)
    public int[] solution4_1(long n) {
        StringBuilder sb = new StringBuilder(String.valueOf(n)).reverse();

        int[] answer = new int[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            answer[i] = Character.getNumericValue(sb.charAt(i));
        }
        return answer;
    }
}
