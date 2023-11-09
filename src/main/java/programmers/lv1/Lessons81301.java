package programmers.lv1;

// lv1 숫자 문자열과 영단어
public class Lessons81301 {

    // 풀이 1
    public int solution1(String s) {
        String replaced = s.replace("zero", "0")
                .replace("one", "1")
                .replace("two", "2")
                .replace("three", "3")
                .replace("four", "4")
                .replace("five", "5")
                .replace("six", "6")
                .replace("seven", "7")
                .replace("eight", "8")
                .replace("nine", "9");
        return Integer.parseInt(replaced);
    }
    
    // 풀이 2(다른 풀이 참고) - 더 깔끔
    public int solution2(String s) {
        String[] strings = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i = 0; i < strings.length; i++) {
            s = s.replace(strings[i], String.valueOf(i));
        }

        return Integer.parseInt(s);
    }
}
