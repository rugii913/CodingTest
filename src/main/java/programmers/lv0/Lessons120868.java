package programmers.lv0;

// lv0 삼각형의 완성조건 (2)
public class Lessons120868 {

    // 풀이 1
    public int solution1(int[] sides) {
        // return (sides[0] + sides[1]) - (Math.abs(sides[0] - sides[1])) + 1 - 2;
        return (sides[0] + sides[1]) - (Math.abs(sides[0] - sides[1])) - 1;
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(int[] sides) {
        // int max = Math.max(sides[0], sides[1]); // 풀이엔 있으나 필요 없는 부분
        int min = Math.min(sides[0], sides[1]);
        int answer = min * 2 - 1;
        return answer;
        // return Math.min(sides[0], sides[1]) * 2 -1;
        // 다른 변의 길이가 가질 수 있는 범위 => max - min < ? < max + min => min * 2 -1
    }
}
