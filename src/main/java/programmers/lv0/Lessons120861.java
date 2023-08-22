package programmers.lv0;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// lv0 캐릭터의 좌표
public class Lessons120861 {

    // 풀이 1 +11점..???
    public int[] solution1(String[] keyinput, int[] board) {
        int maxX = board[0] / 2;
        int minX = - maxX;
        int maxY = board[1] / 2;
        int minY = - maxY;

        int[] position = {0, 0};

        for (String key : keyinput) {
            switch (key) {
                case "up":
                    position[1] = Math.min(position[1] + 1, maxY);
                    break;
                case "down":
                    position[1] = Math.max(position[1] - 1, minY);
                    break;
                case "right":
                    position[0] = Math.min(position[0] + 1, maxX);
                    break;
                case "left":
                    position[0] = Math.max(position[0] - 1, minX);
                    break;
            }
        }
        return position;
    }

    // 풀이 2(다른 풀이 참고) - 짧지만 가독성 안 좋음
    public int[] solution2(String[] keyinput, int[] board) {
        int[] now = {0, 0};
        for (int i = 0; i < keyinput.length; i++){
            if (keyinput[i].equals("left")) now[0] -= now[0] > -(board[0] / 2) ? 1 : 0;
            else if (keyinput[i].equals("right")) now[0] += now[0] < (board[0] / 2) ? 1 : 0;
            else if (keyinput[i].equals("down")) now[1] -= now[1] > -(board[1] / 2) ? 1 : 0;
            else if (keyinput[i].equals("up")) now[1] += now[1] < (board[1] / 2) ? 1 : 0;
        }
        return now;
    }

    // 풀이 3(다른 풀이 참고) - stream
    public int[] solution3(String[] keyinput, int[] board) {
        return Arrays.stream(keyinput).map(s -> getMap().get(s)).reduce((ints1, ints2) -> new int[] {
                Math.abs(ints1[0] + ints2[0]) > board[0] / 2 ? board[0] / 2 * (ints1[0] < 0 ? -1 : 1) : ints1[0] + ints2[0],
                Math.abs(ints1[1] + ints2[1]) > board[1] / 2 ? board[1] / 2 * (ints1[1] < 0 ? -1 : 1) : ints1[1] + ints2[1]
        }).orElse(new int[]{0, 0});
    }
    private Map<String, int[]> getMap() {
        Map<String, int[]> map = new HashMap<>();

        map.put("up", new int[] {0, 1});
        map.put("down", new int[] {0, - 1});
        map.put("left", new int[] {-1, 0});
        map.put("right", new int[] {1, 0});
        return map;
    }
}
