package programmers.lv3;

// lv3 금과 은 운반하기 - 이진 탐색
public class Lessons86053 {
    /*
     * 인터넷 검색하여 어떤 알고리즘에 속하는 문제인지 확인하고 풀이함 - 이 문제에 대한 풀이의 코드 자체를 보진 않음.
     * - 취업과 이직을 위한 프로그래머스 코딩 테스트 문제 풀이 - 8장 이진 탐색 부분 - 다른 문제 어떻게 풀이했는지 참고
     */

    // 풀이 1 // 0.13ms ~ 160.55ms -> 0.08ms ~ 113.80ms
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {

        final long timeWorst = (long) (2 * Math.pow(10, 9) * 2 * Math.pow(10, 5));
        // 최악의 경우 a = b = 10^9, 도시의 수 = 1, w[0] = 1, t[0] = 10^5 => (2 * Math.pow(10, 9)) / 1 * (2 * Math.pow(10, 5) - 1)이지만 간략하게...
        long timeStart = 0;
        long timeEnd = timeWorst;

        long[] deliveryCounts = new long[g.length];

        long givenTime = -1;

        // while (timeStart < timeEnd) { // TODO: 이진 탐색 종료 조건 다시 잘 생각해보기
        while (timeStart != timeEnd - 1) {
            givenTime = (timeStart + timeEnd) / 2;

            if (isAvailableInGivenTime1_2(givenTime, a, b, g, s, w, t, deliveryCounts)) {
                timeEnd = givenTime;
            } else {
                timeStart = givenTime;
            }
        }

        return timeEnd;
    }

    // 0.13ms ~ 160.55ms
    private boolean isAvailableInGivenTime1_1(long givenTime, int a, int b, int[] g, int[] s, int[] w, int[] t, long[] deliveryCounts) {
        for (int i = 0; i < t.length; i++) {
            deliveryCounts[i] = givenTime % (2L * t[i]) >= t[i] ? givenTime / (2L * t[i]) + 1: givenTime / (2L * t[i]);
        }

        boolean isTotalAvailable;
        long total = 0; // total 확인 안 하면 테스트케이스 1 실패
        for (int i = 0; i < t.length; i++) {
            total += Math.min(w[i] * deliveryCounts[i], g[i] + s[i]);
        }
        isTotalAvailable = total >= a + b; 

        boolean isGoldAvailable;
        long goldSum = 0;
        for (int i = 0; i < t.length; i++) {
            goldSum += Math.min(w[i] * deliveryCounts[i], g[i]);
        }
        isGoldAvailable = goldSum >= a;

        boolean isSilverAvailable;
        long silverSum = 0;
        for (int i = 0; i < t.length; i++) {
            silverSum += Math.min(w[i] * deliveryCounts[i], s[i]);
        }
        isSilverAvailable = silverSum >= b;

        return isTotalAvailable && isGoldAvailable && isSilverAvailable; // TODO: 맘에 안 드는 부분 꼭 세개를 다 확인해야 할까?
    }

    // 0.08ms ~ 113.80ms // isAvailableInGivenTime1_1에서 굳이 필요 없는 변수 없애고, 불필요한 반복문 줄임
    private boolean isAvailableInGivenTime1_2(long givenTime, int a, int b, int[] g, int[] s, int[] w, int[] t, long[] deliveryCounts) {
        for (int i = 0; i < t.length; i++) {
            deliveryCounts[i] = givenTime % (2L * t[i]) >= t[i] ? givenTime / (2L * t[i]) + 1: givenTime / (2L * t[i]);
        }

        long total = 0; // total 확인 안 하면 테스트케이스 1 실패
        long goldSum = 0;
        long silverSum = 0;
        for (int i = 0; i < t.length; i++) {
            total += Math.min(w[i] * deliveryCounts[i], g[i] + s[i]);
            goldSum += Math.min(w[i] * deliveryCounts[i], g[i]);
            silverSum += Math.min(w[i] * deliveryCounts[i], s[i]);
        }

        return total >= a + b && goldSum >= a && silverSum >= b; // TODO: 맘에 안 드는 부분 꼭 세개를 다 확인해야 할까?
    }
}
