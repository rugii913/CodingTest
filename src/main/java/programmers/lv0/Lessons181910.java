package programmers.lv0;

// lv0 문자열 뒤의 n글자
public class Lessons181910 {

    // 풀이 1
    public String solution1(String my_string, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(my_string.charAt(my_string.length() - 1 - i));
        }
        return sb.reverse().toString();
    }

    // 풀이 2(다른 풀이 참고) - string.substring() 사용, 훨씬 간편하고 속도도 빠름
    public String solution2(String my_string, int n) {
        return my_string.substring(my_string.length() - n);
    }

    // 풀이 3 - 스택을 구현해봤는데, 생각보다 속도가 잘 나오지 않았다.
    public String solution3(String my_string, int n) {
        PseudoStack stack = new PseudoStack(n);
        for (int i = 0; i < n; i++) {
            stack.push(my_string.charAt(my_string.length() - 1 - i));
        }

        char[] charArray = new char[n];
        for (int i = 0; i < n; i++) {
            charArray[i] = stack.pop();
        }

        return new String(charArray);
    }
    static class PseudoStack {
        char top;
        int size = 0;
        char[] array;
        public PseudoStack(int n) {
            this.array = new char[n];
        }
        public void push(char c) {
            array[++size - 1] = c;
            this.top = c;
        }
        public char pop() {
            if (size == 1) {
                char tmp = top;
                this.top = ' ';
                size = 0;
                return tmp;
            } else {
                char tmp = top;
                this.top = array[--size -1];
                return tmp;
            }
        }
    }
}
