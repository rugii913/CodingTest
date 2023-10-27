package programmers.lv0;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Stack;

// lv0 두 수의 합
public class Lessons181846 {
    // a, b 최대 길이가 100,000

    // 풀이 1 - BigInteger 사용
    public String solution1(String a, String b) {
        return new BigInteger(a).add(new BigInteger(b)).toString();
    }

    // 풀이 x 중단
    public String solutionx(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        int longerLength = Math.max(aChars.length, bChars.length);
        int shorterLength = Math.min(aChars.length, bChars.length);
        char[] longerChars = aChars.length == longerLength ? aChars : bChars;

        char[] sumChars = new char[longerLength + 1];
        Arrays.fill(sumChars, '0'); // -> 이 부분이 없어서 결과값이 아예 이해할 수 없게 나왔음 한참 고민했었음 - char[] 각 요소의 기본값은 '0'이 아니라 u0000임을 기억하기

        for (int i = 1; i <= shorterLength; i++) {
            int tmp = Character.getNumericValue(aChars[aChars.length - i])
                    + Character.getNumericValue(bChars[bChars.length - i])
                    + Character.getNumericValue(sumChars[sumChars.length - i]);

            if (tmp > 10) {
                sumChars[sumChars.length - i - 1] = '1';
                sumChars[sumChars.length - i] = (char) (tmp % 10);
            } else {
                sumChars[sumChars.length - i] = (char) (tmp);
            }
        }

        for (int i = shorterLength + 1; i <= longerLength; i++) {
            int tmp = Character.getNumericValue(longerChars[longerLength - i])
                    + Character.getNumericValue(sumChars[sumChars.length - i]);

            if (tmp > 10) {
                sumChars[sumChars.length - i - 1] = Character.forDigit(tmp / 10, 10);
                sumChars[sumChars.length - i] = Character.forDigit(tmp % 10, 10);
            } else {
                sumChars[sumChars.length - i] = Character.forDigit(tmp, 10);
            }
        }

        return sumChars[0] != 0
                ? new String(sumChars)
                : new String(sumChars).substring(1);
    }

    // 풀이 2(다른 풀이 참고) - 내가 포기한 방식과 유사한 느낌인 듯
    public String solution2(String a, String b) {
        int length = Math.max(a.length(), b.length()) + 1;

        char[] arr = new char[length];
        Arrays.fill(arr, '0');

        char[] ar = a.toCharArray(), br = b.toCharArray();

        for (int i = 0; i < ar.length; i++) {
            arr[length - 1 - i] += (char) (ar[ar.length - 1 - i] - '0');
        }

        for (int i = 0; i < br.length; i++) {
            arr[length - 1 - i] += (char) (br[br.length - 1 - i] - '0');
        }

        for (int i = length - 1; i > 0; i--) {
            if (arr[i] > '9') {
                arr[i - 1] += 1;
                arr[i] -= 10;
            }
        }

        String answer = new String(arr);
        if (arr[0] == '0')
            answer = answer.substring(1);
        return answer;
    }

    // 풀이 2-1(다른 풀이 참고)
    public String solution2_1(String a, String b) {
        String answer = "";
        int len1 = a.length();
        int len2 = b.length();
        int maxIdx = Math.max(len1, len2);
        int[] nums = new int[maxIdx];
        for (int i = 0; i < maxIdx - 1; i++) {
            if (i < len1) {
                nums[maxIdx - (i + 1)] += Character.getNumericValue(a.charAt(len1 - (i + 1)));
            }
            if (i < len2) {
                nums[maxIdx - (i + 1)] += Character.getNumericValue(b.charAt(len2 - (i + 1)));
            }
            if (nums[maxIdx - (i + 1)] > 9) {
                nums[maxIdx - (i + 1)] -= 10;
                nums[maxIdx - (i + 2)] += 1;
            }
        }
        if (maxIdx - 1 < len1) {
            nums[0] += Character.getNumericValue(a.charAt(len1 - maxIdx));
        }
        if (maxIdx - 1 < len2) {
            nums[0] += Character.getNumericValue(b.charAt(len2 - maxIdx));
        }
        if (nums[0] > 9) {
            nums[0] -= 10;
            answer += "1";
        }
        for (int i = 0; i < maxIdx; i++) {
            answer += String.valueOf(nums[i]);
        }
        return answer;
    }

    // 풀이 2-2(다른 풀이 참고) - StringBuilder 사용
    public String solution2_2(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;

            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }

    // 풀이 2-3(다른 풀이 참고) - stack 사용
    public String solution2_3(String a, String b) {
        String answer = "";
        Stack<Integer> stack = new Stack<>();
        String tmp = "";

        int shortLen = Math.min(a.length(), b.length());
        int longLen = Math.max(a.length(), b.length());

        if (a.length() != b.length()) {
            if (shortLen == a.length()) {
                for (int i = shortLen; i < longLen; i++) {
                    tmp += "0";
                }
                a = tmp + a;
            } else {
                for (int i = shortLen; i < longLen; i++) {
                    tmp += "0";
                }
                b = tmp + b;
            }
        }

        boolean carry = false;
        int sumNum = 0;

        for (int i = a.length() - 1; i >= 0; i--) {
            int NumA = Integer.parseInt(String.valueOf(a.charAt(i)));
            int NumB = Integer.parseInt(String.valueOf(b.charAt(i)));

            if (carry) {
                sumNum = NumA + NumB + 1;
                carry = false;
            } else {
                sumNum = NumA + NumB;
            }

            if (sumNum >= 10) {
                carry = true;
                stack.push(sumNum - 10);
            } else {
                stack.push(sumNum);
            }
        }

        if (carry) {
            stack.push(1);
        }

        while (!stack.isEmpty()) {
            answer += stack.pop();
        }

        return answer;
    }
}
