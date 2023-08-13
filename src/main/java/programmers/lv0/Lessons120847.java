package programmers.lv0;

import java.util.Arrays;

// lv0 최댓값 만들기(1)
public class Lessons120847 {
    /*
    * 여러 가지 고민할 것들이 있음
    * - 풀이 1 vs. 풀이 2 변수 저장으로 중첩 반복 피하기
    * - 풀이 4 Arrays.sort() 정렬이 풀이 5 정렬 구현보다 느린 이유는 뭘까?
    * */

    // 풀이 1 - 중첩 반복을 안 하려면 풀이 2 참고
    public int solution1(int[] numbers) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (i == j) {
                    continue;
                }

                int product = numbers[i] * numbers[j];
                if (max < product) {
                    max = product;
                }
            }
        }
        return max;
    }
    
    // 잘못된 풀이 - numbers 중 인덱스가 다르지만, 크기가 같은 수가 있는 경우 틀린 풀이가 됨
    public int solutionx(int[] numbers) {
        int max = Integer.MIN_VALUE;
        for (int number1 : numbers) {
            for (int number2 : numbers) {
                if (number1 == number2) {
                    continue;
                }

                int product = number1 * number2;
                if (max < product) {
                    max = product;
                }
            }
        }
        return max;
    }

    // 풀이 2(다른 풀이 참고) - 곱하기를 딱 한 번만 하기 때문에 풀이 1보다 나을 것 같다.
    // 변수에 인덱스를 저장해놓으면 풀이 1처럼 중첩 반복문을 쓸 필요가 없다.
    public int solution2(int[] numbers) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max1Index = -1;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max1) {
                max1 = numbers[i];
                max1Index = i;
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            if (i == max1Index) {
                continue;
            }
            if (numbers[i] > max2) {
                max2 = numbers[i];
            }
        }

        return max1 * max2;
    }

    // 풀이 3 - 스트림 사용
    public int solution3(int[] numbers) {
        int[] tmp = Arrays.stream(numbers).sorted().toArray();
        return tmp[numbers.length - 1] * tmp[numbers.length - 2];
    }

    // 풀이 4(다른 풀이 참고) - Arrays.sort() 메서드가 있으므로 굳이 풀이 3처럼 stream으로 정렬할 필요가 없음 - stream은 느리다.
    // 전부 다 정렬해줘야하기 때문에 풀이 1보다 속도는 느림
    public int solution4(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[numbers.length - 1] * numbers[numbers.length - 2];
    }

    // 풀이 5(다른 풀이 참고)
    // 왜인지는 모르겠지만 직접 구현하는 정렬은 풀이 4 Arrays.sort()보다 빠른 듯하다. - Arrays.sort() 구현 코드 살펴볼 것
    // 풀이 1보다도 빠름, 프로그래머스 9번째 테스트를 제외하고는 풀이 2보다 많이 느리진 않음
    public int solution5(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[i]) {
                    int tmp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = tmp;
                }
            }
        }

        return numbers[numbers.length - 2] * numbers[numbers.length - 1];
    }
}
