package programmers.lv0;

import java.util.HashMap;
import java.util.Map;

// lv0 로그인 성공?
public class Lessons120883 {

    // 풀이 1
    public String solution1(String[] id_pw, String[][] db) {
        for (int i = 0; i < db.length; i++) {
            if (db[i][0].equals(id_pw[0])) {
                if (db[i][1].equals(id_pw[1])) {
                    return "login";
                } else {
                    return "wrong pw";
                }
            }
        }
        return "fail";
    }

    // 풀이 2(다른 풀이 참고) - stream
    public String solution2(String[] id_pw, String[][] db) {
        return getDbMap(db).
                get(id_pw[0]) != null ? getDbMap(db).get(id_pw[0]).equals(id_pw[1]) ? "login" : "wrong pw" : "fail";
    }
    private Map<String, String> getDbMap(String[][] db) {
        Map<String, String> map = new HashMap<>();

        for (String[] array : db) {
            map.put(array[0], array[1]);
        }
        return map;
    }
}
