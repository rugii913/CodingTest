package programmers.lv0;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// lv0 문자 개수 세기
public class Lessons181902 {
    // 디버깅 alphabets[i] = alphabets[i - 1]++; 한참 걸림

    // 풀이 1 - 디버깅 alphabets[i] = alphabets[i - 1]++; 한참 걸림
    // 풀이 2보다 비효율적
    public int[] solution1(String my_string) {
        int countUpperCases = 'Z' - 'A' + 1;
        int countLowerCases = 'z' - 'a' + 1;

        char[] alphabets = new char[countUpperCases + countLowerCases];
        alphabets[0] = 'A';
        alphabets[countUpperCases] = 'a';

        for (int i = 1; i < countUpperCases; i++) {
            // alphabets[i] = alphabets[i - 1]++; // 참조값이 증가해서 이렇게 ++하면 안 됨
            alphabets[i] = (char) (alphabets[i - 1] + 1);
        }
        for (int i = countUpperCases + 1; i < alphabets.length; i++) {
            // alphabets[i] = alphabets[i - 1]++;
            alphabets[i] = (char) (alphabets[i - 1] + 1);
        }

        int[] countAlphabet = new int[alphabets.length];
        for (char my_stringChar : my_string.toCharArray()) {
            for (int i = 0; i < alphabets.length; i++) {
                if (my_stringChar == alphabets[i]) {
                    countAlphabet[i]++;
                    break;
                }
            }
        }

        return countAlphabet;
    }

    // 풀이 2(다른 풀이 참고)
    // 풀이 1이 너무 비효율적...
    public int[] solution2(String my_string) {
        int[] answer = new int[52];
        for (int i = 0; i < my_string.length(); i++) {
            char ch = my_string.charAt(i);
            if (ch >= 'a')
                answer[ch - 'a' + 26]++;
            else
                answer[ch - 'A']++;
        }
        return answer;
    }

    // 풀이 3(다른 풀이 참고) - stream - collect(Collectors.groupingBy(~, ~))
    public int[] solution3(String my_string) {
        return IntStream.concat(IntStream.concat(my_string.chars(), IntStream.rangeClosed('A', 'Z')), IntStream.rangeClosed('a', 'z'))
                .mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream() // key는 말고, value만 따와서 stream으로
                .mapToInt(i ->  i.intValue() - 1) // A ~ Z, a ~ z concat해서 counting 했으므로 모두 개수가 1씩 더해져 있는 상태 -> 1씩 빼준다. (모든 알파벳에 해당하는 원소가 있을 것을 보장하기 위해 한 번씩 더해준 것이라고 생각하면 될 듯)
                .toArray();
        // 정렬은 .collect(Collectors.groupingBy(~, ~)에서 이미 발생하는 듯하다.
        // Collectors Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier, Supplier<M> mapFactory, Collector<? super T, A, D> downstream) 참고할 것
    }
}