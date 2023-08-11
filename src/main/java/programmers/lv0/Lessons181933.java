package programmers.lv0;

// lv0 flag에 따라 다른 값 반환하기
public class Lessons181933 {

    // 풀이 1 - 간단한 삼항 연산자 문제
    public int solution1(int a, int b, boolean flag) {
        return flag ? a + b : a - b;
    }
}
