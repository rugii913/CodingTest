package programmers.lv1;

import java.util.*;

// lv1 문자열 내 마음대로 정렬하기
public class Lessons12915 {

    // 풀이 1
    public String[] solution1(String[] strings, int n) {
        Arrays.sort(strings);
        // Arrays.sort(strings, (str1, str2) -> Character.compare(str1.charAt(n), str2.charAt(n)));
        Arrays.sort(strings, Comparator.comparingInt(str -> str.charAt(n)));

        return strings;
    }

    // 풀이 1-1x - 원래는 thenComparing을 사용하고 싶었는데, 계속 에러가 발생한다.
    public String[] solution1_1x(String[] strings, int n) {
        /*Arrays.sort(strings, Comparator.comparingInt(str -> str.charAt(n))
                .thenComparing(Comparator.naturalOrder())
        );*/
        return null;
    }

    // 풀이 1-1 - 위 풀이 1-1x 수정 - comparingInt(~) 메서드에 제네릭 타입 명시를 해주니, 컴파일 에러 사라짐
    public String[] solution1_1(String[] strings, int n) {
        Arrays.sort(strings, Comparator.<String>comparingInt(str -> str.charAt(n)).thenComparing(Comparator.naturalOrder()));

        return strings;
    }

    // 풀이 1-2(다른 풀이 참고) - Comparator 구현
    public String[] solution1_2(String[] strings, int n) {
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.charAt(n) > s2.charAt(n)) return 1;
                else if (s1.charAt(n) == s2.charAt(n)) return s1.compareTo(s2);
                else if (s1.charAt(n) < s2.charAt(n)) return -1;
                else return 0;
            }
        });
        return strings;
    }

    // 풀이 1-3(위 풀이 수정) - Comparator 구현
    public String[] solution1_3(String[] strings, int n) {
        Arrays.sort(strings, (s1, s2) ->
                s1.charAt(n) == s2.charAt(n) ? s1.compareTo(s2) : s1.charAt(n) - s2.charAt(n)
        );
        return strings;
    }

    // 풀이 2(다른 풀이 참고) - 가장 위에 있어서 가져왔는데, 굳이... 라는 생각이 든다... - 심지어 훨씬 느림...
    public String[] solution2(String[] strings, int n) {
        String[] answer = {};
        List<String> list = new ArrayList<>();
        for (String string : strings) {
            list.add(string.charAt(n) + string);
        }
        Collections.sort(list);

        answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).substring(1);
        }
        return answer;
    }

    // 풀이 3(다른 풀이 참고) - 배열에서 직접 정렬, 빠름
    public String[] solution3(String[] strings, int n) {
        for (int i = 0; i < strings.length - 1; i++) { // charAt(n) 비교하며 정렬
            String temp = "";
            for (int k = i + 1; k < strings.length; k++) {
                if (strings[i].charAt(n) > strings[k].charAt(n)) {
                    temp = strings[i];
                    strings[i] = strings[k];
                    strings[k] = temp;
                }
            }
        }
        for (int i = 0; i < strings.length - 1; i++) { // 일반적인 String lexicographically 정렬
            String temp = "";
            for (int k = i + 1; k < strings.length; k++) {
                if (strings[i].charAt(n) == strings[k].charAt(n)) {
                    if (strings[i].compareTo(strings[k]) > 0) {
                        temp = strings[i];
                        strings[i] = strings[k];
                        strings[k] = temp;
                    }
                }
            }
        }

        return strings;
    }
}
