package programmers.lv0;

import java.util.Arrays;
import java.util.stream.IntStream;

// lv0 할 일 목록
public class Lessons181885 {

    // 풀이 1
    public String[] solution1(String[] todo_list, boolean[] finished) {
        String[] notFinishedList = new String[todo_list.length];

        int notFinishedCount = 0;
        for (int i = 0; i < todo_list.length; i++) {
            if (!finished[i]) {
                notFinishedList[notFinishedCount++] = todo_list[i];
            }
        }

        return Arrays.copyOfRange(notFinishedList, 0, notFinishedCount);
    }

    // 풀이 2(다른 풀이 참고) - 배열을 직접 사용하지 않는 방식
    public String[] solution2(String[] todo_list, boolean[] finished) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < todo_list.length; i++) {
            if (!finished[i]) {
                sb.append(todo_list[i]).append(",");
            }
        }

        return sb.toString().split(",");
    }

    // 풀이 3(다른 풀이 참고) - stream
    public String[] solution3(String[] todo_list, boolean[] finished) {
        return IntStream.range(0, todo_list.length).filter(i -> !finished[i]).mapToObj(i -> todo_list[i]).toArray(String[]::new);
    }
}
