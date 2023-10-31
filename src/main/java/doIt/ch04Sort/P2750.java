package doIt.ch04Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

// Do it 코딩테스트 ch4-문제 015 수 정렬하기 1(문제 제목: 수 정렬하기) // P2750
public class P2750 {
    /*
    * ㅁ 여러가지 정렬 알고리즘
    *   ㅇ 버블(bubble): 데이터의 인접 요소끼리 비교하고, swap 연산을 수행하며 정렬하는 방식
    *   ㅇ 선택(selection): 대상에서 가장 크거나 작은 데이터를 찾아가 선택을 반복하면서 정렬
    *   ㅇ 삽입(insertion): 대상을 선택해 정렬된 영역에서 선택 데이터의 적절한 위치를 찾아 삽입하면서 정렬
    *   ㅇ 퀵(quick): pivot 값을 선정해 해당 값을 기준으로 정렬
    *   ㅇ 병합(merge): 이미 정렬된 부분 집합들을 효율적으로 병합해 전체를 정렬
    *   ㅇ 기수(radix): 데이터의 자릿수를 바탕으로 비교해 데이터를 정렬
    * */

    /*
    * ㅁ 버블 정렬: 데이터의 인접 요소끼리 비교하고, swap 연산을 수행하며 정렬하는 방식
    *   ㅇ 시간복잡도 O(n^2)
    *   ㅇ 정렬 과정 개요
    *     - (오름차순의 경우) [0, n-1] 구간에서 swap을 통해 가장 큰 값을 n-1 인덱스로 보냄
    *       -> (length - 1은 가장 큰 값으로 정해졌으므로) [0, n-2] 구간에서 swap을 통해 구간 내에서 가장 큰 값을 n-2 인덱스로 보냄
    *       -> - ... 구간을 하나씩 줄이면서 반복 [0, n-1] [0, n-2], [0, n-3], ... [0, 1]
    *     - 만약 swap이 한 번도 발생하지 않은 loop가 있다면 그 구간은 이미 정렬된 것이므로 종료할 수 있음(구간 뒷 부분은 작업을 통해 이미 정렬되어 있음)
    *       -> 풀이 2-1에서 swap 체크하도록 구현해봄, 그다지 깔끔하지 않다.
    *   ㅇ 정렬 과정
    *     (1) 비교 연산이 필요한 루프 범위를 설정
    *     (2) 인접한 데이터 값을 비교
    *     (3) swap 조건에
    *     (4) 루프 범위가 끝날 때까지 (2) ~ (3)을 반복
    *     (5) 정렬된 영역을 확인하여 다음 루프를 실행할 때는 이 영역을 제외
    *     (6) 비교 대상이 없을 때까지 (1) ~ (5)를 반복
    * */

    /*
    * 풀이 2 vs. 풀이 2-1 비교
    * - 풀이 2: 메모리 22,256 KB, 시간 360 ms
    * - 풀이 2-1: 메모리 22,232 KB, 시간 352 ms
    *   - loop에서 swap이 발생하지 않은 경우 정렬을 중단하도록 구현함, 시간 차이가 크지 않음
    *   - 코드도 깔끔하지 않고, 자연스럽지 않음
    * */

    // 풀이 1
    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        for (int i = N; i > 1; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int tmp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = tmp;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(numbers[i]);
        }

        br.close();
    }

    // 풀이 1-1 - Arrays.sort(~)
    public void solution1_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(numbers);

        for (int i = 0; i < N; i++) {
            System.out.println(numbers[i]);
        }

        br.close();
    }

    // 풀이 1-2 - stream
    public void solution1_2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // IntStream.generate(() -> parseIntWithInput(br)).limit(N).sorted().forEach(System.out::println);
        IntStream.range(0, N).map(i -> parseIntWithInput(br)).sorted().forEach(System.out::println);

        br.close();
    }

    private int parseIntWithInput(BufferedReader br) {
        try {
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 풀이 1-3 - stream, br 말고 sc 사용해서 간결하게
    public void solution1_3() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        IntStream.generate(sc::nextInt).limit(N).sorted().forEach(System.out::println);
        sc.close();
    }

    // 풀이 2(책)
    public void solution2() throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(A[i]);
        }

        sc.close();
    }

    // 풀이 2-1(위 풀이 수정)
    // - swap이 한 번도 발생하지 않았는지 체크한다면
    public void solution2_1() throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            boolean swapped = false;
            for (int j = 0; j < N - 1 - i; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            } else {
                swapped = false;
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(A[i]);
        }

        sc.close();
    }
}
