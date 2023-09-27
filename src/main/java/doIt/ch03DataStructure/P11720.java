package doIt.ch03DataStructure;

import java.util.Scanner;

// Do it 코딩테스트 ch3-문제 001 숫자의 합 구하기 // P11720
public class P11720 {

    // 풀이 1 - Character.getNumericValue(~) 사용
    public void solution1() {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        String stringNumbers = scanner.next();

        if (size != stringNumbers.length()) {
            System.out.println("(1) 작업을 실행할 숫자의 개수 입력값 (2) 입력된 숫자의 길이가 일치하지 않아 종료합니다.");
            return;
        }

        int sum = 0;
        for (char charNumber : stringNumbers.toCharArray()) {
            sum += Character.getNumericValue(charNumber);
        }

        System.out.println(sum);
    }

    // 풀이 2(책) - char끼리 빼면 바로 int형을 얻을 수 있다.
    public void solution2() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // 입력값을 String형 변수 sNum에 저장한 후 char[]형 변수로 변환하기
        String sNum = sc.next();
        char[] cNum = sNum.toCharArray();
        int sum = 0;
        for (int i = 0; i < cNum.length; i++) {
            sum += cNum[i] - '0'; // cNum[i]를 정수형으로 변환하면서 sum에 더하여 누적하기
        }
        System.out.println(sum);
    }

    // p.37 참고 자료 - 자바에서의 형변환
    public void casting() {
        // String형 -> 숫자형(int, double, float, long, short)
        String sNum = "1234";
        // (1) Wrapper 클래스의 parse~(~) static 메서드
        int i1 = Integer.parseInt(sNum); // 반환 타입이 primitive type
        // (2) Wrapper 클래스의 valueOf(~) static 메서드
        int i2 = Integer.valueOf(sNum); // 반환 타입이 Wrapper type
        // - (IDE 검사) 불필요한 박싱입니다. 대신 'Integer.parseInt()' 호출을 사용할 수 있습니다 
        // - int i2 = (int) Integer.valueOf(sNum);에서 (int) 명시적 형변환 연산자가 생략된 것

        // 숫자형(int, double, float, long, short) -> String형
        int i = 1234;
        // (1) String 클래스의 valueOf(~) static 메서드
        String s1 = String.valueOf(i);
        // (2) Wrapper 클래스의 toString(~) static 메서드
        String s2 = Integer.toString(i);
    }
}
