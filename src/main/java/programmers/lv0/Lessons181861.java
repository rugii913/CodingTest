package programmers.lv0;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// lv0 배열의 원소만큼 추가하기 - enhanced for - iterator 생각해보기
public class Lessons181861 {
    /*
     * 풀이 1 - ArrayList로 구현 / 풀이 2 - LinkedList를 Queue로 사용(그런데 여기서 굳이 Queue를 사용할 필요가 없다.)
     * 풀이 3-1
     * - 큰 데이터일 때 이런 작업보다는 stream이 더 빠르다.
     * - 반복 작업적으로 이어붙이는 작업이라면 굳이 concat을 쓰지 말고 그냥 StringBuilder를 사용할 것
     * - 데이터의 크기가 커지면 stream을 사용해도 성능이 비슷비슷하다.
      */

    // 풀이 1 - ArrayList로 구현 // 0.03ms ~ 1.84ms
    public int[] solution1(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                list.add(arr[i]);
            }
        }

        /*
        int listSize = list.size();
        int[] answer = new int[listSize];
        for (int i = 0; i < listSize; i++) {
            answer[i] = list.get(i);
        }
         */

        int[] answer = new int[list.size()];
        int answerIndex = -1;
        for (int element : list) {
            answer[++answerIndex] = element;
        }

        return answer;
    }

    // 풀이 1-1 - LinkedList를 Queue로 사용하여 구현 // 0.22ms ~ 2.18ms
    public int[] solution1_1(int[] arr) {
        Queue<Integer> intQueue = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                intQueue.add(arr[i]);
            }
        }

        /*
        int queueSize = intQueue.size();
        int[] answer = new int[queueSize];
        for (int i = 0; i < queueSize; i++) {
            answer[i] = intQueue.poll(); // (IDE 검사) 'intQueue.poll()'을(를) 언박싱하면 'NullPointerException'이 생성될 수 있습니다
        }
         */
        /*
        int[] answer = new int[intQueue.size()];
        int index = -1;
        while (!intQueue.isEmpty()) {
            answer[++index] = intQueue.poll();
        }
         */

        int[] answer = new int[intQueue.size()];
        int index = -1;
        for (int i : intQueue) {
            answer[++index] = i;
            // for문으로 iterator를 사용하면, 굳이 poll()을 사용할 필요가 없음 - 알아서 next로 탐색해주므로
            // - 굳이 poll()을 통해 Queue를 비워줘야하는 게 아니라면...
            // -> 그런데 이렇게 되어버리면 또 굳이 Queue를 사용해야만 하는 이유가 없음
        }

        return answer;
    }

    // 풀이 2 - 배열만으로 구현 // 0.02ms ~ 0.17ms
    public int[] solution2(int[] arr) {
        int sum = 0;

        for (int element : arr) {
            sum += element;
        }

        int[] answer = new int[sum];
        int nextStartIndex = 0;
        for (int element : arr) {
            for (int i = nextStartIndex; i < nextStartIndex + element; i++) {
                answer[i] = element;
            }
            nextStartIndex += element;
        }

        return answer;
    }

    // 풀이 2-1(다른 풀이 참고)
    public int[] solution2_1(int[] arr) {
        int sum = 0;

        for (int element : arr) {
            sum += element;
        }

        int[] answer = new int[sum];

        int idx = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                answer[idx++] = arr[i];
            }
        }

        return answer;
    }

    // 풀이 3(다른 풀이 참고) - Stream, string.repeat(~) 사용 // 3.55ms ~ 9.58ms
    public int[] solution3(int[] arr) {
        String answer = "";

        for (int i : arr) {
            answer += (String.valueOf(i) + ",").repeat(i);
        }
        return Stream.of(answer.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    // 풀이 3-1(위 풀이 수정 실패) - 문제 잘못 읽은 것 - char로 쪼개면 제대로 된 답을 얻을 수 없음
    /*
    public int[] solution3_1(int[] arr) {
        String answerString = "";

        for (int element : arr) {
            String elementString = String.valueOf(element);

            for (int count = 0; count < element; count++) {
                answerString = answerString.concat(elementString);
            }
        }

        char[] charArray = answerString.toCharArray();
        int[] answerArray = new int[charArray.length];

        int answerArrayIndex = -1;
        for (char ch : charArray) {
            answerArray[++answerArrayIndex] = Character.getNumericValue(ch);
            // answerArray[++answerArrayIndex] = ch - '0'; // bing "java char에서 int 얻기" 검색 - Copilot 참고
        }

        return answerArray;
    }
     */

    // 풀이 3-1(위 주석 풀이 수정) // 0.07ms ~ 229.53ms
    // - 큰 데이터일 때 이런 작업보다는 stream이 더 빠르다.
    // - 반복 작업적으로 이어붙이는 작업이라면 굳이 concat을 쓰지 말고 그냥 StringBuilder를 사용할 것
    // - 데이터의 크기가 커지면 stream을 사용해도 성능이 비슷비슷하다.
    public int[] solution3_1(int[] arr) {
        /*
        String answerString = "";
        String delimiter = ",";
         */
        StringBuilder sb = new StringBuilder(); // StringBuilder 사용할 경우 0.10ms ~ 10.63ms // StringBuilder에 Stream 사용할 경우 2.06ms ~ 10.91ms
        char delimiter = ',';

        for (int element : arr) {
            /*
            String elementString = String.valueOf(element);

            for (int count = 0; count < element; count++) {
                answerString = answerString.concat(elementString).concat(delimiter);
                // answerString = answerString + elementString + delimiter; // 13.14ms ~ 123.92ms // concat보다 +가 빠를 수도 있다.
            }
             */
            for (int count = 0; count < element; count++) {
                sb.append(element).append(delimiter);
            }
        }

        // return Stream.of(answerString.split(",")).mapToInt(Integer::parseInt).toArray(); // 2.16ms ~ 224.64ms
        // 이 부분보다 위의 concat 부분이 문제인 것으로 보인다.

        return Stream.of(sb.toString().split(",")).mapToInt(Integer::parseInt).toArray(); // 2.06ms ~ 10.91ms

        /*
        String answerString = sb.toString();
        String[] stringArray = answerString.split(",");

        int[] answerArray = new int[stringArray.length];

        int answerArrayIndex = -1;
        for (String string : stringArray) {
            answerArray[++answerArrayIndex] = Integer.parseInt(string);
        }

        return answerArray;
         */
    }

    // 풀이 3-2(다른 풀이 참고) // 3.55ms ~ 9.58ms
    public int[] solution3_2(int[] arr) {
        return Arrays.stream(Arrays.stream(arr)
                        .mapToObj(i -> (i + " ").repeat(i))
                        .collect(Collectors.joining(""))
                        .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
