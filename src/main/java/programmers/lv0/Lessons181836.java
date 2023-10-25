package programmers.lv0;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// lv0 그림 확대
public class Lessons181836 {
    // 딱히 더 괜찮은 풀이가 없는 것 같다...

    // 풀이 1
    public String[] solution1(String[] picture, int k) {
        char[] tmp = new char[picture[0].length() * k];
        String[] newPicture = new String[picture.length * k];

        for (int i = 0; i < picture.length; i++) {
            char[] charsOfIthString = picture[i].toCharArray();

            for (int j = 0; j < charsOfIthString.length; j++) {
                Arrays.fill(tmp, j * k, (j + 1) * k, charsOfIthString[j]);
            }

            String newRow = new String(tmp);
            for (int j = 0; j < k; j++) {
                newPicture[i * k + j] = newRow;
            }
        }

        return newPicture;
    }

    // 풀이 2 - 약간 바꿨지만 크게 차이는 없음, Arrays.fill(~) 사용 안 함, int 나눗셈 연산 활용
    public String[] solution2(String[] picture, int k) {
        char[] tmp = new char[picture[0].length() * k];
        String[] newPicture = new String[picture.length * k];

        for (int i = 0; i < picture.length; i++) {
            char[] charsOfIthString = picture[i].toCharArray();

            for (int j = 0; j < tmp.length; j++) {
                tmp[j] = charsOfIthString[j / k];
            }

            for (int j = 0; j < newPicture.length; j++) {
                if (j / k == i) {
                    newPicture[j] = new String(tmp);
                }
            }
        }

        return newPicture;
    }

    // 풀이 3(다른 풀이 참고) - stream
    public String[] solution3(String[] picture, int k) {
        return IntStream.range(0, picture.length * k) // 반환할 새로운 String[]의 길이만큼의 인덱스
                .mapToObj(i -> // 인덱스마다 mapping
                        IntStream.range(0, picture[i / k].length()) // 새로운 IntStream 생성, 범위는 i / k번째 String의 length 만큼
                                .mapToObj(l -> String.valueOf(picture[i / k].charAt(l)).repeat(k)) // i / k번째 String은 각 string 인덱스의 문자들을 repeat(k) 한 것
                                .collect(Collectors.joining()))
                // mapping된 String을 새 String으로 합치기 // 이렇게 하고 하면 0 ~ k - 1로 mapping된 String끼리 같고, k ~ 2k -1로 mapping된 String 끼리 같고, ...
                .toArray(String[]::new); // 맨 처음 IntStream에 mapping된 순서 따라서 toArray
    }

    // 풀이 4(다른 풀이 참고) - 굳이...
    public String[] solution4(String[] picture, int k) {
        String[] answer = new String[picture.length * k];
        int idx = 0;

        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < k; j++) {
                StringBuilder sb = new StringBuilder();

                for (int l = 0; l < picture[i].length(); l++) {
                    sb.append(String.valueOf(picture[i].charAt(l)).repeat(k));
                }

                answer[idx++] = sb.toString();
            }
        }

        return answer;
    }

    // 풀이 5(다른 풀이 참고) - 굳이...
    public String[] solution5(String[] picture, int k) {
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();

        String dot = "", x = "";
        for (int i = 0; i < k; i++) {
            sb1.append(".");
            sb2.append("x");
        }
        dot = sb1.toString();
        x = sb2.toString();
        // k배 늘린 String을 미리 준비하고 replace
        // 그런데 이럴 거면 dot = ".".repeat(k); x = ".".repeat(k); 이렇게 하는 게 나을 듯

        int idx = 0;
        String[] answer = new String[picture.length * k];
        for (int i = 0; i < picture.length; i++) {
            String result = picture[i].replace(".", dot);
            result = result.replace("x", x);

            for (int j = 0; j < k; j++)
                answer[idx++] = result;
        }
        return answer;
    }
}
