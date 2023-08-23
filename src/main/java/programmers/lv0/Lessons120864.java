package programmers.lv0;

import java.util.Arrays;
import java.util.StringTokenizer;

// lv0 숨어있는 숫자의 덧셈 (2)
public class Lessons120864 {
    /*
    * regex 정규표현식 사용
    * 전체 정리 https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%A0%95%EA%B7%9C%EC%8B%9DRegular-Expression-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%A0%95%EB%A6%AC
    * 중복 공백 제거 https://www.techiedelight.com/ko/remove-duplicate-whitespaces-from-string-java/
    * */

    // 풀이 1 - replaceAll regex 사용 1 - 통과
    public int solution1(String my_string) {
        String[] intStrings = my_string.replaceAll("[\\D]", " ").split(" ");

        int answer = 0;
        for (String intString : intStrings) {
            if (intString.length() == 0) { // 공백이 이어져서 반복되는 경우 때문에... => 풀이 1-2에서 해결한 방식 볼 것
                continue;
            }
            answer += Integer.parseInt(intString);
        }
        return answer;
    }
    // 풀이 1-1 replaceAll regex 사용 2 - xxxxxxxx 통과 안 됨 - 테스트 8 런타임 에러 -> 통과 되도록 수정: 숫자 문자열이 없는 경우 때문임
    // if (intString.length() == 0) 이 부분 없앨 수 있도록, trim(), 중복 공백 제거
    public int solution1_1(String my_string) {
        String[] intStrings = my_string.replaceAll("[\\D]", " ").trim().replaceAll("\\s{2,}", " ").split(" ");

        // 이 부분 없으면 테스트 8 런타임 에러 // 풀이 1에서 if (intString.length() == 0) 혹은 풀이 2에서 if (!s.equals("")) 부분이 이런 것을 제거한다는 점에서도 의미가 있음
        // if (intStrings[0].equals("")) { // equals("") 비교하는 대신 isEmpty를 쓸 수도 있을 것 (cf.) string.isEmpty(), isBlank()
        if (intStrings[0].isEmpty()) {
                return 0;
        }

        int answer = 0;
        for (String intString : intStrings) {
            answer += Integer.parseInt(intString);
        }
        return answer;
    }

    // 풀이 1-2(다른 풀이 참고) - 풀이 1과 같음 - my_string이 알파벳 대소문자 및 공백으로만 이뤄졌음을 이용했을 뿐
    public int solution1_2(String my_string) {
        String[] str = my_string.replaceAll("[a-zA-Z]", " ").split(" ");

        int answer = 0;
        for (String s : str) {
            if (!s.equals("")) { // 여기서도 풀이 1처럼 공백이 이어져서 반복되는 경우 처리
                answer += Integer.parseInt(s);
            }
        }

        return answer;
    }

    // 풀이 2 - StringTokenizer 사용
    public int solution2(String my_string) {
        int answer = 0;
        String s = my_string.replaceAll("[^0-9]", " ");
        StringTokenizer st = new StringTokenizer(s, " ");

        // StringTokenizer의 경우 delimiter로 들어온 걸 전부 뛰어넘기 때문에
        // "abcdefga" 이렇게 숫자 없는 문자열이 들어온 경우 token이 없는 것으로 판단하고 while 조건에서 바로 튕겨져 나간다.
        // 따라서 숫자 없는 문자열을 처리하기 위한 별도 로직이 필요 없다.
        while (st.hasMoreTokens()) {
            answer += Integer.parseInt(st.nextToken());
        }

        return answer;
    }
    
    // 풀이 3 - 연속된 수는 하나의 숫자로 간주하는 부분 때문에 까다로움 => 속도는 풀이 1보다 훨씬 빠르다.
    // +7점
    public int solution3(String my_string) {
        int sum = 0;

        String[] numStrings = getNumbersOnly(my_string);
        for (String numString : numStrings) {
            sum += Integer.parseInt(numString);
        }

        return sum;
    }
    private String[] getNumbersOnly(String inputString) {
        char[] chars = inputString.toCharArray();

        int subNumStart = -1; // 숫자 substring의 시작 인덱스 -> isNumArea == false가 나오면 for 마지막에서 subNumStart == -1로 바꿔줄 것
        int subNumEnd = -1; // 숫자 substring의 끝 인덱스 -> isNumArea == false가 나오면 for 마지막에서 subNumEnd == -1
        boolean isNumArea; // 아래 for에서 현재 인덱스가 num substring 영역에 있는지

        int subNumCount = 0; // 숫자 substring의 개수
        String[] tmp = new String[chars.length];

        final int zeroCodePoint = Character.codePointAt("0", 0);
        final int nineCodePoint = Character.codePointAt("9", 0);

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= zeroCodePoint && chars[i] <= nineCodePoint) { // char가 숫자 char이면
                isNumArea = true;
                if (subNumStart == -1) {
                    subNumStart = i;
                }
            } else { // char가 숫자 char가 아니면
                isNumArea = false;
                if (subNumStart != -1) {
                    subNumEnd = i;
                }
            }

            if (!isNumArea && subNumEnd != -1) { // 앞 char까지는 숫자였는데 지금 인덱스부터 숫자 char가 아닌 것이 나온 경우
                tmp[subNumCount++] = inputString.substring(subNumStart, subNumEnd);
                subNumStart = -1;
                subNumEnd = -1;
            } else if (i == chars.length - 1 && isNumArea) { // 숫자 char가 마지막 인덱스까지 등장하는 경우
                // else if (i == chars.length - 1 && isNumArea && subNumStart != -1) { // for문 두번째 if 때문에 isNumArea == true인 경우엔 subNumStart != -1은 항상 참
                tmp[subNumCount++] = inputString.substring(subNumStart, chars.length);
            }
        }

        String[] numStrings = new String[subNumCount]; // 크기에 맞게 복사
        /*
        // 특별한 이유가 없으면 수동 복사하지 말고 System.arraycopy(~) 사용 - IDE 경고 사항
        for (int i = 0; i < subNumCount; i++) {
            numStrings[i] = tmp[i];
        }
        */
        System.arraycopy(tmp,0, numStrings, 0, subNumCount);

        return numStrings;
    }
    // 풀이 3-1(다른 풀이 참고) - 빠름, if가 많긴 하지만 간결
    // 맥락은 풀이 3과 유사한데 // substring이 아닌 char와 int를 바로 이용함으로써 훨씬 간결해졌다.
    public int solution3_1(String my_string) {
        /*
        // 변수명 알아보기 힘들어서 바꿈
        int answer = 0;
        int ad = -1;
        for(char c : my_string.toCharArray()){
            int cInt =  c;
            if(cInt >= 47 && cInt <= 57){
                if(ad == -1) ad = cInt -48;
                else ad = ad * 10 + (cInt-48);
            }
            else{
                if(ad != -1){
                    answer += ad;
                    ad = -1;
                }
            }
        }
        if(ad != -1) answer += ad;
        return answer;
         */
        int answer = 0;
        int prevNumbers = -1;
        for (char c : my_string.toCharArray()) {
            int cInt = c; // c의 code point를 바로 int로 갖고 옴
            if (cInt >= 48 && cInt <= 57) { // 아스키 코드 48 ~ 57이 숫자 char
                if (prevNumbers == -1) {
                    prevNumbers = cInt - 48; // 숫자를 가져옴
                }
                else {
                    prevNumbers = prevNumbers * 10 + (cInt - 48); // 자릿수 처리
                }
            } else { // 숫자가 아닌 char가 나온 경우 - prevNumbers == 1인 경우 그대로 진행하면 되고, != 1인 경우 앞까지 구해진 수를 더한다.
                if (prevNumbers != -1) {
                    answer += prevNumbers;
                    prevNumbers = -1;
                }
            }
        }
        if (prevNumbers != -1) { // char[]의 마지막 부분이 숫자일 때를 위한 처리
            answer += prevNumbers;
        }
        return answer;
    }

    // 풀이 4(다른 풀이 참고) - stream
    public int solution4(String myString) {
        return Arrays.stream(myString.split("[A-Z|a-z]")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).sum();
    }
}
