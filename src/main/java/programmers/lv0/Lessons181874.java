package programmers.lv0;

// lv0 A 강조하기
public class Lessons181874 {

    // 풀이 1
    public String solution1(String myString) {
        return myString.toLowerCase().replace('a', 'A');
    }

    // 풀이 2(다른 풀이 참고) - String matches(regex)
    public String solution2(String myString) {
        StringBuilder answer = new StringBuilder();

        String[] strArr = myString.split("");

        for (String str : strArr) {
            if (str.equals("a")) {
                answer.append("A");
            } else if (str.matches("[B-Z]")) {
                answer.append(str.toLowerCase());
            } else {
                answer.append(str);
            }
        }

        return answer.toString();
    }
}
