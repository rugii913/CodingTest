package programmers.lv1;

// lv1 최소직사각형
public class Lessons86491 {
    /*
    처음에 이런 식으로 풀어나가려고 시도했으나 안 됨...
    int widthMax = 0;
    int heightMax = 0;
    for (int[] size : sizes) {
        if (widthMax < size[0] || heightMax < size[1]) {
            if (widthMax > size[1] && heightMax > size[0]) {
                continue;
            } else {
                widthMax = Math.max(widthMax, size[0]);
                heightMax = Math.max(heightMax, size[1]);
            }
        }
    }
    return widthMax * heightMax;
    * */

    // 풀이 1
    public int solution1(int[][] sizes) {
        int maxLongerSide = 0;
        int maxShorterSide = 0;
        for (int[] size : sizes) {
            if (size[0] < size[1]) {
                int tmp = size[0];
                size[0] = size[1];
                size[1] = tmp;
            }

            maxLongerSide = Math.max(maxLongerSide, size[0]);
            maxShorterSide = Math.max(maxShorterSide, size[1]);
        }

        return maxLongerSide * maxShorterSide;
    }

    // 풀이 1-1(다른 풀이 참고) - 풀이 1과 같이 더 긴 변을 index 0으로 몰아넣는 것은 같음, 더 깔끔
    public int solution1_1(int[][] sizes) {
        int width = 0, height = 0;
        for (int[] card : sizes) {
            width = Math.max(width, Math.max(card[0], card[1]));
            height = Math.max(height, Math.min(card[0], card[1]));
        }
        return width * height;
    }
}
