package programmers.lv0;

public class Lessons120908 {

    // 풀이 1 - StringBuffer 사용
    public int solution1(String str1, String str2) {
        int answer = 2;

        if (str1.contains(new StringBuffer(str2))) {
            answer = 1;
        }

        return answer;
    }

    // 풀이 2 - 다른 풀이 참고
    // contains의 인자로 String을 넣을 수 있기 때문에 굳이 StringBuffer를 넣을 필요가 없음
    // 삼항 연산자 사용해서 더 간단하게
    public int solution2(String str1, String str2) {
        return str1.contains(new StringBuffer(str2)) ? 1 : 2;
    }

    //풀이 3 - 맨 처음 풀이, 복잡함 - 그런데 속도는 오히려 풀이 1보다 빠름
    public int solution3(String str1, String str2) {
        int answer = 2;
        char firstCharInStr2 = str2.charAt(0);
        int lengthStr1 = str1.length();
        int lengthStr2 = str2.length();
        int[] indexArray = new int[lengthStr1];

        for (int i = 0; i < lengthStr1; i++) {
            if (str1.charAt(i) == firstCharInStr2) {
                indexArray[i] = 1;
            }
        }

        for (int i = 0; i < lengthStr1; i++) {
            if (indexArray[i] == 1 && i + lengthStr2 <= lengthStr1) {
                if (str1.substring(i, i + lengthStr2).equals(str2)) {
                    answer = 1;
                }
            }
        }

        return answer;
    }
}
