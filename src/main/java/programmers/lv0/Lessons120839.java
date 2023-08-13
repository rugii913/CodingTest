package programmers.lv0;

// lv0 가위 바위 보
public class Lessons120839 {

    // 풀이 1 - StringBuffer, 반복문, switch - case 사용
    public String solution1(String rsp) {
        StringBuffer sb = new StringBuffer(rsp);
        for (int i = 0; i < rsp.length(); i++) {
            switch(sb.charAt(i)) {
                case '2':
                    sb.setCharAt(i, '0');
                    break;
                case '0':
                    sb.setCharAt(i, '5');
                    break;
                case '5':
                    sb.setCharAt(i, '2');
                    break;
            }
        }
        return sb.toString();
    }

    // 풀이 2(다른 풀이 힌트) - 속도 큰 차이 없음
    public String solution2(String rsp) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < rsp.length(); i++) {
            switch(sb.charAt(i)) {
                case '2':
                    sb.append('0');
                    break;
                case '0':
                    sb.append('5');
                    break;
                case '5':
                    sb.append('2');
                    break;
            }
        }
        return sb.toString();
    }

    // 풀이 3(다른 풀이 참고) - 약간 더 빠름
    public String solution3(String rsp) {
        char[] charArray = rsp.toCharArray();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < charArray.length; i++) {
            switch(charArray[i]) {
                case '2':
                    sb.append('0');
                    break;
                case '0':
                    sb.append('5');
                    break;
                case '5':
                    sb.append('2');
                    break;
            }
        }
        return sb.toString();
    }

    // 풀이 4(다른 풀이 참고) - StringBuffer보다 StringBuilder가 더 빠르다
    public String solution4(String rsp) {
        char[] charArray = rsp.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {
            switch(charArray[i]) {
                case '2':
                    sb.append('0');
                    break;
                case '0':
                    sb.append('5');
                    break;
                case '5':
                    sb.append('2');
                    break;
            }
        }
        return sb.toString();
    }
}
