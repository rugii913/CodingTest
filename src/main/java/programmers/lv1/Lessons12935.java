package programmers.lv1;

// lv1 제일 작은 수 제거하기
public class Lessons12935 {

    // 풀이 1
    public int[] solution1(int[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }

        int indexOfMin = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[indexOfMin] > arr[i]) {
                indexOfMin = i;
            }
        }

        int[] diminishedArr = new int[arr.length - 1];
        System.arraycopy(arr, 0, diminishedArr, 0, indexOfMin);
        System.arraycopy(arr, indexOfMin + 1, diminishedArr, indexOfMin, arr.length - 1 - indexOfMin);
        return diminishedArr;
    }

    // 풀이 2
    public int[] solution2(int[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }

        int indexOfMin = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[indexOfMin] > arr[i]) {
                indexOfMin = i;
            }
        }

        int[] result = new int[arr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            if (i < indexOfMin) {
                result[i] = arr[i];
            } else if (i > indexOfMin) {
                result[i - 1] = arr[i];
            }
        }
        return result;

        /*
        * 아래와 같은 식으로 진행하면 indexOfMin 이후의 모든 index 영역에 의미 없는 값이 들어간다.
        int[] result = new int[arr.length - 1];
        int resultIndex = 0;
        for (int number : arr) {
            if (resultIndex != indexOfMin) {
                result[resultIndex++] = number;
            }
        }
        return result;
        * */
    }
}
