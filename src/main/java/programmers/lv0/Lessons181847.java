package programmers.lv0;

// lv0 0 떼기
public class Lessons181847 {
    // 풀이 3 parseInt(~) 하면 앞의 0이 사라진다....

    // 풀이 1
    public String solution1(String n_str) {
        char[] chars = n_str.toCharArray();

        int firstNot0Index = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                firstNot0Index = i;
                break;
            }
        }

        return n_str.substring(firstNot0Index);
    }

    // 풀이 2
    public String solution2(String n_str) {
        // int index = 0; while (n_str.charAt(index++) == '0') { ... } 이런 식으로 가면 n_str의 length가 점점 줄어드므로, 원하지 않는 결과가 나온다.
        while (n_str.charAt(0) == '0') {
            n_str = n_str.replaceFirst("0", "");
        }
        return n_str;
    }

    // 풀이 2-1 - while, charAt(~)을 사용한 것은 풀이 2와 같지만, 풀이 1에 더 가까운 풀이
    public String solution2_1(String n_str) {
        int index = -1;
        while (n_str.charAt(++index) == '0') {
            // Do nothing;
        }
        return n_str.substring(index);
    }

    // 풀이 2-2 - while 안이 빈 것이 맘에 안 들어서 for를 사용한다면...
    public String solution2_2(String n_str) {
        int beginIndex = 0;
        for (int i = 0; n_str.charAt(i) == '0'; i++) {
            beginIndex++;
        }
        return n_str.substring(beginIndex);
    }

    // 풀이 3(다른 풀이 참고) - parseInt(~) 하면 앞의 0이 사라진다....
    public String solution3(String n_str) {
        // return "" + Integer.parseInt(n_str);
        return String.valueOf(Integer.parseInt(n_str));
    }

    // 풀이 3-1(다른 풀이 참고) - String.format(~)
    public String solution3_1(String n_str) {
        return String.format("%d", Integer.parseInt(n_str));
    }

    // 풀이 4(다른 풀이 참고) - sb.deleteCharAt(~)
    public String solution4(String n_str) {
        StringBuilder sb = new StringBuilder(n_str);

        while (sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
}
