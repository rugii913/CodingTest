package programmers.lv0;

import java.util.Arrays;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

// lv0 한 번만 등장한 문자
public class Lessons120896 {
    /*
    * 풀이 3 - 제한사항을 잘 이용한 예시
    * */

    // 풀이 1 - 배열만 사용
    //   -> 소문자만 있으므로 char로 바꿔서 비교해도 괜찮다. 대문자가 있다면 String으로 comparator를 사용해야할 것
    // 1. char 배열로 만들어 정렬한다. (순서 보존 아니어도 별 상관은 없음)
    // 2. char가 들장한 횟수를 센 int[] counts 배열을 만든다.
    // 3. count = 1 인덱스를 확인하여 해당 인덱스에 위치한 char들만 걸러낸다.
    public String solution1(String s) {
        int stringLength = s.length();
        // char 배열로 만들어 정렬한다.
        char[] chars = s.toCharArray();
        char tmp;
        for (int i = 0; i < stringLength; i++) {
            for (int j = 0; j < stringLength; j++) {
                if (chars[i] < chars[j]) {
                    tmp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = tmp;
                }
            }
        }

        // 각 char와 같은 인덱스에 위치한 count = lastIndex - firstIndex + 1
        int[] counts = new int[stringLength];
        for (int i = 0; i < stringLength; i++) {
            int firstIndex = -1;
            int lastIndex = -1;
            for (int j = 0; j < stringLength; j++) {
                if (chars[j] == chars[i]) {
                    firstIndex = j;
                    break;
                }
            }
            for (int k = firstIndex; k < stringLength; k++) {
                if (chars[k] != chars[i]) {
                    lastIndex = k - 1;
                    break;
                } else if (k == stringLength - 1 && chars[k] == chars[i]) {
                    // 이 부분이 없으면 정렬된 chars에서 마지막 원소가 중복 원소가 아닌 한 번 등장한 원소인 경우 에러
                    // 테스트 케이스 "hello" -> "eho"
                    lastIndex = k;
                }
            }
            counts[i] = lastIndex - firstIndex + 1;
        }

        // 한 번만 등장한 char들(count가 1인 것들)만 걸러내서 새 배열로 만든다.
        int countUniqueChars = 0;
        char[] tmpArray = new char[stringLength];
        for (int i = 0; i < stringLength; i++) {
            if (counts[i] == 1) {
                tmpArray[countUniqueChars++] = chars[i];
            }
        }

        char[] uniqueChars = new char[countUniqueChars];
        for (int i = 0; i < countUniqueChars; i++) {
            uniqueChars[i] = tmpArray[i];
        }

        StringBuilder sb = new StringBuilder(countUniqueChars);
        return sb.append(uniqueChars).toString();
    }

    // 풀이 2 - TreeSet 사용 - 코드가 그렇게 많이 줄어들지 않는다.
    public String solution2(String s) {
        NavigableSet<String> set = new TreeSet<>();
        String[] sSplit = s.split("");
        for (String letter : sSplit) {
            set.add(letter);
        }

        int lettersSize = set.size();
        String[] letters = new String[lettersSize];
        for (int i = 0; i < lettersSize; i++) {
            letters[i] = set.pollFirst();
        }

        int[] counts = new int[lettersSize];
        for (int i = 0; i < lettersSize; i++) {
            for (int j = 0; j < s.length(); j++) {
                if (sSplit[j].equals(letters[i])) {
                    counts[i]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lettersSize; i++) {
            if (counts[i] == 1) {
                sb.append(letters[i]);
            }
        }

        return sb.toString();
    }

    // 풀이 3(다른 풀이 참고) - 전체 소문자 알파벳에 대해 출현 횟수를 셈
    // 제한사항을 잘 활용 - 어차피 제한사항으로 인해 다뤄야하는 char들이 정해져있으므로 이렇게 풀어도 될 듯하다.
    public String solution3(String s) {
        int[] countsOfAlphabet = new int[26];
        for(char c : s.toCharArray()){
            countsOfAlphabet[c - Character.codePointAt("a", 0)]++;
        }

        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < 26; i++){
            if(countsOfAlphabet[i] == 1){
                answer.append((char)(i + 'a'));
            }
        }
        return answer.toString();
    }

    // 풀이 4(다른 풀이 참고) - stream
    public String solution4(String s) {
        // 이해가 잘 안 됨 ** groupingBy
        return Arrays.stream(s.split(""))
                .collect(Collectors.groupingBy(s1 -> s1))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() <= 1)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.joining());
    }
}
