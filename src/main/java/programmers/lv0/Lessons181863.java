package programmers.lv0;

// lv0 rny_string
public class Lessons181863 {
    /*
    * replace(~)와 replaceAll(~)의 차이는 String 정규표현이냐 CharSequence냐의 차이
    * */

    public String solution1(String rny_string) {
        return rny_string.replace("m", "rn");
    }

    public String solution2(String rny_string) {
        return rny_string.replaceAll("m", "rn");
    }
}
