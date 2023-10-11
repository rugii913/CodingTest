package programmers.lv0;

import java.util.Arrays;
import java.util.List;

// lv0 왼쪽 오른쪽
public class Lessons181890 {

    // 풀이 1 - Arrays.copyOfRange(~)
    public String[] solution1(String[] str_list) {
        String[] answer = new String[0];

        for (int i = 0; i < str_list.length; i++) {
            if (str_list[i].equals("l")) {
                answer = Arrays.copyOfRange(str_list, 0, i);
                break;
            } else if (str_list[i].equals("r")) {
                answer = Arrays.copyOfRange(str_list, i + 1, str_list.length);
                break;
            }
        }

        return answer;
    }

    // 풀이 2(다른 풀이 참고 수정) - List 사용 - Arrays.asList(~)
    public String[] solution2(String[] str_list) {
        List<String> list = Arrays.asList(str_list);

        int lIndex = list.indexOf("l");
        int rIndex = list.indexOf("r");

        if (lIndex != -1 && rIndex != -1) {
            return lIndex < rIndex
                    ? list.subList(0, lIndex).toArray(String[]::new)
                    : list.subList(rIndex + 1, str_list.length).toArray(String[]::new);
        }

        return new String[]{};
    }
}
