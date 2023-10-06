package programmers.lv0;

import java.math.BigInteger;

// lv0 9로 나눈 나머지
public class Lessons181914 {

    // 풀이 1 // 0.03ms ~ 7.81ms
    public int solution1(String number) {
        int sumOfDigits = 0;

        for (char ch : number.toCharArray()) {
            sumOfDigits += Character.getNumericValue(ch);
        }

        return sumOfDigits % 9;
    }

    // 풀이 1-1(다른 풀이 수정) // 0.02ms ~ 3.09ms
    public int solution1_1(String number) {
        int sumOfDigits = 0;

        for (char ch : number.toCharArray()) {
            sumOfDigits += ch - '0';
        }

        return sumOfDigits % 9;
    }

    // 풀이 2(다른 풀이 참고 수정) - chars() 자체가 Intstream을 반환함, Charstream은 없음 // 1.05ms ~ 7.46ms
    public int solution2(String number) {
        return number.chars().map(c -> c - '0').sum() % 9;
    }

    // 풀이 3(다른 풀이 참고 수정) - BigInteger(문제 의도와는 맞지 않지만...) // 0.65ms ~ 165.47ms
    public int solution3(String number) {
        BigInteger x = new BigInteger(number);
        BigInteger y = new BigInteger("9");
        return x.remainder(y).intValue();
    }
}
