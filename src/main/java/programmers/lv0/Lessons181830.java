package programmers.lv0;

// lv0 정사각형으로 만들기
public class Lessons181830 {

    // 풀이 1
    public int[][] solution1(int[][] arr) {
        int rows = arr.length;
        int columns = arr[0].length;
        int criterion = Math.max(rows, columns);

        int[][] answer;
        if (rows < columns) {
            answer = new int[criterion][criterion];
            System.arraycopy(arr, 0, answer, 0, arr.length);
        } else if (rows > columns) {
            answer = new int[criterion][criterion];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(arr[i], 0, answer[i], 0, arr[i].length);
            }
        } else {
            answer = arr;
        }

        return answer;
    }

    // 풀이 2(다른 풀이 참고)
    // - rows < columns, rows > columns 각각의 경우 분기하지 않음
    // -> new int[max][max] 만들면 어차피 모든 원소가 0인 array 생성됨
    // -> rows, columns 크기의 대소 관계가 어떻게 되든 간에, 각 행에 원래 있던 행들의 열 크기 만큼을 복사
    // -> 빈 공간은 이미 0으로 채워진 상태
    public int[][] solution2(int[][] arr) {
        int max = Math.max(arr.length, arr[0].length);
        int[][] array = new int[max][max];

        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, array[i], 0, arr[0].length);
        }
        return array;
    }

    // 풀이 3(다른 풀이 참고 수정) - 이렇게 수동으로 배열 복사하는 것조차도 풀이 1보다는 간단한 느낌이다.
    public int[][] solution3(int[][] arr) {
        int max = Math.max(arr.length, arr[0].length);

        int[][] result = new int[max][max];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                result[i][j] = arr[i][j];
            }
        }

        return result;
    }
}
