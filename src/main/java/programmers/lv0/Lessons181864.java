package programmers.lv0;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// lv0 문자열 바꿔서 찾기
public class Lessons181864 {

    // 풀이 1
    public int solution1(String myString, String pat) {
        StringBuilder sb = new StringBuilder(myString);
        for (int i = 0; i < myString.length(); i++) {
            switch (sb.charAt(i)) {
                case 'A':
                    sb.setCharAt(i, 'B');
                    break;
                case 'B':
                    sb.setCharAt(i, 'A');
                    break;
            }
        }
        return sb.toString().contains(pat) ? 1 : 0;
    }

    // 풀이 1_1(다른 풀이 참고) - 내가 푼 것보다 더 깔끔한 듯
    public int solution1_1(String myString, String pat) {
        StringBuffer sb = new StringBuffer();
        for (char ch : myString.toCharArray())
            if (ch == 'A') sb.append('B');
            else sb.append('A');
        return sb.toString().contains(pat) ? 1 : 0;
    }

    // 풀이 2(다른 풀이 참고) - 괜찮은 꼼수
    public int solution2(String myString, String pat) {
        myString = myString.replace("A", "a").replace("B", "A").replace("a", "B");
        return myString.contains(pat) ? 1 : 0;
    }

    // 풀이 3(다른 풀이 참고) - 스트림은 이렇게 써야함
    public int solution3(String myString, String pat) {
        return myString.contains(Stream.of(pat.split("")).map(str -> "A".equals(str) ? "B" : "A").collect(Collectors.joining())) ? 1 : 0;
    }

    // 풀이 3-1(위 참고) - 위 풀이 보고 실험
    public int solution3_1(String myString, String pat) {
        return myString.contains(Stream.of(pat.split("")).map(str -> {
            if (str.equals("A")) {
                return "B";
            } else if (str.equals("B")) {
                return "A";
            } else {
                return str;
            }
        }).collect(Collectors.joining())) ? 1 : 0;
    }

    // 풀이 3-2(위 참고) - pat가 아니라 myString을 변환해도 잘 되는지 확인
    public int solution3_2(String myString, String pat) {
        return Stream.of(myString.split("")).map(str -> {
            if (str.equals("A")) {
                return "B";
            } else if (str.equals("B")) {
                return "A";
            } else {
                return str;
            }
        }).collect(Collectors.joining()).contains(pat) ? 1: 0;
    }

    // 혼자 해보면서 실패한 코드들 - 스트림을 잘못 쓴 것들
    public int solutionx(String myString, String pat) {
//        return myString.lines().map(x -> {
//            if (x == "A") {
//                return "B";
//            } else if (x == "B") {
//                return "A";
//            } else {
//                return x;
//            }
//        }).toString().contains(pat) ? 1 : 0;

//        return myString.lines().map(x -> {
//            if (x.equals("A")) {
//                return "B";
//            } else if (x.equals("B")) {
//                return "A";
//            } else {
//                return x;
//            }
//        }).collect(Collectors.joining()).contains(pat) ? 1 : 0;


//        return Stream.of(myString).map(x -> {
//            if (x.equals("A")) {
//                return "B";
//            } else if (x.equals("B")) {
//                return "A";
//            } else {
//                return x;
//            }
//        }).collect(Collectors.joining()).contains(pat) ? 1 : 0;

        Arrays.stream(myString.split("")).forEach(x -> {
            if (x.equals("A")) {
                x = "B";
            } else if (x.equals("B")) {
                x = "A";
            }
        });
        return myString.contains(pat) ? 1 : 0;
    }
}
