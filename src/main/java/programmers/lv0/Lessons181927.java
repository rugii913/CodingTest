package programmers.lv0;

// lv0 마지막 두 원소
public class Lessons181927 {

    // 풀이 1 // 0.01ms ~ 0.03ms
    public int[] solution1(int[] num_list) {
        int originalLength = num_list.length;
        int last = num_list[originalLength - 1];
        int prev = num_list[originalLength - 2];

        int[] addedArray = new int[originalLength + 1];
        System.arraycopy(num_list, 0, addedArray, 0, originalLength);

        addedArray[originalLength] = last > prev ? last - prev : 2 * last;
        return addedArray;
    }

    // 풀이 1-1 // 0.01ms ~ 0.05ms
    public int[] solution1_1(int[] num_list) {
        int originalLength = num_list.length;
        int last = num_list[originalLength - 1];
        int prev = num_list[originalLength - 2];

        int[] addedArray = new int[originalLength + 1];
        for (int i = 0; i < originalLength; i++) { // (IDE 경고) 수동 배열 복사 - 검사 정보: System.arraycopy() 호출로 대체 가능한 배열 내용의 수동 복사를 보고합니다.
            addedArray[i] = num_list[i];
        }

        addedArray[originalLength] = last > prev ? last - prev : 2 * last;
        return addedArray;
    }
}
