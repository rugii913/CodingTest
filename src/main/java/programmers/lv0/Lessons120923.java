package programmers.lv0;

// lv0 연속된 수의 합
public class Lessons120923 {

    // 풀이 1
    // first부터 first + (num - 1)까지 더한 것이 total -> 0 ~ (num - 1) 더한 것을 total에서 빼주면 first * num이 나오는 것을 이용
    public int[] solution1(int num, int total) {
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += i;
        }
        int first = (total - sum) / num;

        int[] answer = new int[num];
        for (int i = 0; i < num; i++) {
            answer[i] = first + i;
        }
        return answer;
    }

    // 풀이 2 - 등차수열의 합 이용
    public int[] solution2(int num, int total) {
        int[] answer = new int[num];
        int check = num * (num + 1) / 2; // 1 ~ num의 합
        int start = (total - check) / num + 1;
        // total = (x + 1) + ... + (x + num)으로 본 것 => (total - check) = x * num => start = x + 1
        for (int i = 0; i < answer.length; i++) {
            answer[i] = start + i;
        }
        return answer;
    }
}
