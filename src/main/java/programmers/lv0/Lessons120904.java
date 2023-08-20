package programmers.lv0;

// lv0 숫자 찾기
public class Lessons120904 {
    // 숫자 % 연산 쓸까 고민하다가 애초에 문제 의도가 string.indexOf(~)인 듯하여 indexOf(~) 사용함

    // 풀이 1
    public int solution1(int num, int k) {
        String stringNum = String.valueOf(num);
        String stringK = String.valueOf(k);

        int index = stringNum.indexOf(stringK);
        return index == -1 ? index : index + 1;
    }

    // 풀이 2(다른 풀이 참고) - "-"는 k가 문자열에 있다면 인덱스 1부터 세도록 하는 용도 
    public int solution(int num, int k) {
        return ("-" + num).indexOf(String.valueOf(k));
    }
}
