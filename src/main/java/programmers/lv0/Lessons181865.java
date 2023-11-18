package programmers.lv0;

// lv0 간단한 식 계산하기
public class Lessons181865 {

    // 풀이 1 - 개선된 switch 사용(Java 14 2020년 3월 시작)
    public int solution1(String binomial) {
        String[] strings = binomial.split(" ");
        int a = Integer.parseInt(strings[0]);
        int b = Integer.parseInt(strings[2]);

        return switch (strings[1]) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> 0;
            // default -> throw new IllegalArgumentException(); // 이런 식으로 간 풀이도 있는데 이게 괜찮은 건지는 모르겠다.
        };
    }

    // 풀이 1-1(다른 풀이 참고)
    public int solution1_1(String binomial) {
        String[] parts = binomial.split(" ");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[2]);
        char op = parts[1].charAt(0);
        int result = 0;
        switch (op) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            default: // 이게 괜찮은 방법일까?
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
        return result;
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(String binomial) {
        String[] cal = binomial.split(" ");

        if (cal[1].equals("+")) {
            return (Integer.parseInt(cal[0])) + (Integer.parseInt(cal[2]));
        } else if (cal[1].equals("-")) {
            return (Integer.parseInt(cal[0])) - (Integer.parseInt(cal[2]));
        } else {
            return (Integer.parseInt(cal[0])) * (Integer.parseInt(cal[2]));
        }
    }
}
