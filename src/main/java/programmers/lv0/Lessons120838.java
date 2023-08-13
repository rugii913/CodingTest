package programmers.lv0;

import java.util.HashMap;
import java.util.Map;

// lv0 모스부호 (1)
public class Lessons120838 {
    /*
    * replace를 쓸 수 있을까 했는데, 딱히 더 나은 풀이가 없는 것 같다...
    * */

    // 풀이 1
    public String solution1(String letter) {
        String[] letterArray = letter.split(" ");
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        int codePointAtSmallA = Character.codePointAt("a", 0);
        StringBuilder sb = new StringBuilder();

        for (String l : letterArray) {
            char ch = 0;

            for (int i = 0; i < morse.length; i++) {
                if (morse[i].equals(l)) {
                    ch = (char) (codePointAtSmallA + i);
                    // ch = 'a' + i; // cf. ch = 'a' + 1; 이런 건 되는데, ch = 'a' + i; 이런 건 안 된다.
                }
            }

            sb.append(ch);
        }

        return sb.toString();
    }

    // 풀이 2(다른 풀이 힌트) - 풀이 1 수정, ch 안 거치고 바로 sb에 append
    public String solution2(String letter) {
        String[] letterArray = letter.split(" ");
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        int codePointAtSmallA = Character.codePointAt("a", 0);
        StringBuilder sb = new StringBuilder();

        for (String l : letterArray) {
            for (int i = 0; i < morse.length; i++) {
                if (morse[i].equals(l)) {
                    sb.append((char) (codePointAtSmallA + i));
                }
            }
        }

        return sb.toString();
    }

    // 풀이 3
    public String solution3(String letter) {
        String[] letterArray = letter.split(" ");
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        int codePointAtSmallA = Character.codePointAt("a", 0);
        Map<String, Character> map = new HashMap<>();

        for (int i = 0; i < morse.length; i++) {
            map.put(morse[i], (char) (codePointAtSmallA + i));
        }

        StringBuilder sb = new StringBuilder();
        for (String l : letterArray) {
            sb.append(map.get(l));
        }
        return sb.toString();
    }
}
