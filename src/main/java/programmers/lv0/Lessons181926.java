package programmers.lv0;

// lv0 수 조작하기 1
public class Lessons181926 {

    /*
    * 1. switch문 사용 방법 다시 암기할 것
    * 2. enhanced for문, 인라인화 더 적극적으로 생각할 것
    * * 삼항 연산자? 이 경우엔 그렇게 보기 좋진 않을 듯
    * */

    // 풀이 1 - 통과는 하는데 오래 걸림, 6ms 걸리는 것도 있음
    public int solution1(int n, String control) {
        char[] charArray = control.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case 'w':
                    n += 1;
                    break;
                case 's':
                    n -= 1;
                    break;
                case 'd':
                    n += 10;
                    break;
                case 'a':
                    n -= 10;
                    break;
            }
        }

        return n;
    }

    // 풀이 2(다른 풀이 참고, 테스트에 따라 차이 있으나 전반적으로 살짝 더 빠름) - enhanced for문, 인라인화 더 생각
    public int solution2(int n, String control) {
        for (char ch : control.toCharArray()) {
            switch (ch) {
                case 'w':
                    n += 1;
                    break;
                case 's':
                    n -= 1;
                    break;
                case 'd':
                    n += 10;
                    break;
                case 'a':
                    n -= 10;
                    break;
                default:
                    break;
            }
        }

        return n;
    }

    // 풀이 3(다른 풀이 참고) - 이런 중첩된 삼항 연산자도 가능은 하다...
    // 성능도 약간은 더 나은 것 같긴 한데, 짧다고 해서 과연 가독성이 좋을지?
    public int solution3(int n, String control) {
        for(char c : control.toCharArray()){
            n += c == 'w' ? 1 : c == 's' ? -1 : c == 'd' ? 10 : -10;
        }
        return n;
    }

    // 풀이 4(다른 풀이 참고) - IntStream을 반환하는 String의 chars()라는 메서드를 사용...
    public int solution4(int n, String control) {
        return control.chars().reduce(n, (acc, c) -> acc + (c == 'w' ? 1 : c == 's' ? -1 : c == 'd' ? 10 : -10));
    }
}
