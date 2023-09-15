package programmers.lv0;

// lv0 k의 개수
public class Lessons120887 {

    // 풀이 1 - 0.01ms ~ 4.38ms
    public int solution1(int i, int j, int k) {
        int count = 0;
        for (int index = i; index <= j; index++) {
            count += countK(index, k);
        }
        return count;
    }
    private int countK(int i, int k) {
        int count = 0;
        while (i != 0) {
            if (i % 10 == k) {
                count++;
            }
            i /= 10;
        }
        return count;
    }

    // 풀이 1-1(다른 풀이 참고) - 풀이 1과 같음, 메서드 분리만 안 한 것, 0.02ms ~ 4.40ms
    // 왜 오히려 약간 더 느리게 나오는지는 모르겠다.
    // 아무튼 같은 객체 내의 메서드를 호출하는 것에 따른 부하는 거의 없는 듯하다.
    public int solution1_1(int i, int j, int k) {
        int answer = 0;

        for (int num = i; num <= j; num++) {
            int tmp = num;
            while (tmp != 0) {
                if (tmp % 10 == k)
                    answer++;
                tmp /= 10;
            }
        }
        return answer;
    }

    // 풀이 1-3 - count도 함께 넘겨 보기 0.01ms ~ 4.07ms 미세하게 더 빨라짐
    public int solution1_3(int i, int j, int k) {
        int count = 0;
        for (int index = i; index <= j; index++) {
            count = countK1_3(index, k, count);
        }
        return count;
    }
    private int countK1_3(int i, int k, int count) {
        while (i != 0) {
            if (i % 10 == k) {
                count++;
            }
            i /= 10;
        }
        return count;
    }

    // 풀이 2(다른 풀이 참고) - 13.40ms ~ 5607.25ms 이런 건 너무 느려서 사용할 수가 없다.
    public int solution2(int i, int j, int k) {
        String str = "";
        for(int a = i; a <= j; a++) {
            str += a+"";
        }

        return str.length() - str.replace(k+"", "").length();
    }
}
