package programmers.lv0;

// lv0 n개 간격의 원소들
public class Lessons181888 {

    // 풀이 1 - answer의 length 정할 때 삼항 연산자 사용 - 굳이 Math.ceil(~) 같은 걸 쓸 필요는 없을 것 같다.
    public int[] solution1(int[] num_list, int n) {
        int length = num_list.length % n  == 0 ? num_list.length / n : num_list.length / n + 1;
        int[] answer = new int[length];

        for (int i = 0; i < length; i++) {
            answer[i] = num_list[n * i];
        }

        return answer;
    }
}
