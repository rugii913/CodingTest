package programmers.lv0;

// lv0 특이한 정렬
public class Lessons120880 {

    // 풀이 1 - lv0 등수 매기기 풀이 4 보조변수 활용 아이디어 참고
    public int[] solution1(int[] numlist, int n) {
        int length = numlist.length;

        int[] distances = new int[length];
        for (int i = 0; i < length; i++) {
            distances[i] = Math.abs(n - numlist[i]);
        }
        
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            int rank = 0; // 보조변수: distances[i]의 순위를 저장할 것
            for (int j = 0; j < length; j++) { // cf. 자기 자신은 아래 조건문들에 해당하는 조건이 없으므로 rank++ 없이 지나치게 됨
                if (distances[i] > distances[j]) {
                    rank++;
                } else if (distances[i] == distances[j] && numlist[i] < numlist[j]) {
                    rank++;
                }
            }
            answer[rank] = numlist[i];
        }

        return answer;
    }
}
