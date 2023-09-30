package programmers.lv0;

// lv0 더 크게 합치기
public class Lessons181939 {
    // ***** 풀이 3-1-1 IDE 검사 경고에 따른 수정이 만능은 아니다.

    // 풀이 1 - 속도 차이도 얼마 나지 않기 때문에, 가독성을 생각한다면 풀이 2처럼 String을 사용하는 게 나을 수도 있다. // 0.03ms ~ 0.11ms
    public int solution1(int a, int b) {
        int digitsOfA = 0;
        int digitsOfB = 0;

        int tmpA = a;
        while (tmpA > 0) {
            tmpA /= 10;
            digitsOfA++;
        }

        int tmpB = b;
        while (tmpB > 0) {
            tmpB /= 10;
            digitsOfB++;
        }

        int aCirclePlusB = a * (int) Math.pow(10, digitsOfB) + b;
        int bCirclePlusA = b * (int) Math.pow(10, digitsOfA) + a;

        return Math.max(aCirclePlusB, bCirclePlusA);
    }

    // 풀이 1-1 - 연산을 갖고 있는 새 클래스 정의 // 0.20ms ~ 0.49ms
    // 그런데 풀이 2와 같이 String으로 이어붙이기를 이용해도 이것보다는 빠르므로 굳이 이렇게 풀 필요가 없다.
    public int solution1_1(int a, int b) {
        IntegerWithCirclePlusOperation aIntegerWithCirclePlusOperation = new IntegerWithCirclePlusOperation(a);
        IntegerWithCirclePlusOperation bIntegerWithCirclePlusOperation = new IntegerWithCirclePlusOperation(b);

        int aCirclePlusB = aIntegerWithCirclePlusOperation.circlePlus(bIntegerWithCirclePlusOperation);
        int bCirclePlusA = bIntegerWithCirclePlusOperation.circlePlus(aIntegerWithCirclePlusOperation);

        return Math.max(aCirclePlusB, bCirclePlusA);
    }

    static class IntegerWithCirclePlusOperation {

        Integer value;

        public IntegerWithCirclePlusOperation(Integer value) {
            this.value = value;
        }

        public Integer circlePlus(IntegerWithCirclePlusOperation operand) {
            int operandNumberOfDigit = getNumberOfDigit(operand);
            return this.value * (int) Math.pow(10, operandNumberOfDigit) + operand.value;
        }

        private int getNumberOfDigit(IntegerWithCirclePlusOperation integerWithCirclePlusOperation) {
            int numberOfDigit = 0; // 문제 제한 조건상 입력값이 1 이상으로 들어오므로, 0인 경우를 고려해서 분기할 필요 럾음
            int intValue = integerWithCirclePlusOperation.value;

            while (intValue > 0) {
                numberOfDigit++;
                intValue /= 10;
            }

            return numberOfDigit;
        }
    }

    // 풀이 2 // 0.05ms ~ 0.15ms
    public int solution2(int a, int b) {
        String aCirclePlusBString = String.valueOf(a).concat(String.valueOf(b));
        int aCirclePlusB = Integer.parseInt(aCirclePlusBString);

        String bCirclePlusAString = String.valueOf(b).concat(String.valueOf(a));
        int bCirclePlusA = Integer.parseInt(bCirclePlusAString);

        return Math.max(aCirclePlusB, bCirclePlusA);
    }

    // 풀이 3(다른 풀이 참고) // 7.26ms ~ 11.58ms - 풀이 2보다 더 간결할 수는 있겠지만, 나는 피하고 싶은 방법이다.
    public int solution3(int a, int b) {
        return Math.max(Integer.parseInt(a + "" + b), Integer.parseInt(b + "" + a));
    }

    // 풀이 3-1(다른 풀이 참고) // 1.21ms ~ 2.13ms - 풀이 2보다 더 간결할 수는 있겠지만, 나는 피하고 싶은 방법이다. - 그래도 풀이 3, 3개들 중에서는 그나마 낫다.
    public int solution3_1(int a, int b) {
        int ab = Integer.parseInt(Integer.toString(a) + Integer.toString(b));
        int ba = Integer.parseInt(Integer.toString(b) + Integer.toString(a));
        return ab >= ba ? ab : ba;
    }

    // ***** 풀이 3-1-1(위 풀이 수정) // 8.86ms ~ 14.49ms
    // - IDE 검사 경고에 따라 코드를 수정했는데, 속도도 느려지고, 가독성도 그다지 좋지 않다.
    //   - (cf.) 속도 저하의 큰 요인은
    //   Integer.toString(a) + Integer.toString(b)를 Integer.toString(a) + b로,
    //   Integer.toString(b) + Integer.toString(a)를 Integer.toString(b) + a로 바꾼 부분
    // - ***** IDE 검사 경고에 따르는 게 만능이 아니다.
    public int solution3_1_1(int a, int b) {
        int ab = Integer.parseInt(Integer.toString(a) + b);
        int ba = Integer.parseInt(Integer.toString(b) + a);
        return Math.max(ab, ba);
    }

    // 풀이 3-2(다른 풀이 참고) // 7.15ms ~ 15.28ms - 풀이 2보다 더 간결할 수는 있겠지만, 나는 피하고 싶은 방법이다.
    public int solution3_2(int a, int b) {
        int answer = 0;
        int aLong = Integer.parseInt("" + a + b);
        int bLong = Integer.parseInt("" + b + a);
        answer = aLong > bLong ? aLong : bLong;

        return answer;
    }
}
