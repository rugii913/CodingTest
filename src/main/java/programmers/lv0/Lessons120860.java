package programmers.lv0;

// lv0 직사각형 넓이 구하기
public class Lessons120860 {

    // 풀이 1 9점...?
    public int solution1(int[][] dots) {
        int numberOfDots = dots.length;
        int[] valuesOfX = new int[numberOfDots];
        int[] valuesOfY = new int[numberOfDots];

        for (int i = 0; i < dots.length; i++) {
            valuesOfX[i] = dots[i][0];
            valuesOfY[i] = dots[i][1];
        }

        int width = Math.max(distance(valuesOfX[0], valuesOfX[1]), distance(valuesOfX[0], valuesOfX[2]));
        int height = Math.max(distance(valuesOfY[0], valuesOfY[1]), distance(valuesOfY[0], valuesOfY[2]));
        // 직사각형이므로 단 두 개의 점만 비교해서 width와 height를 가져오는 것이 가능함

        return width * height;
    }
    private int distance(int a, int b) {
        return Math.abs(a - b);
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(int[][] dots) {
        int xOf0thElement = dots[0][0];
        int height = 0;
        int width = 0;

        for (int i = 1; i < dots.length; i++) {
            if (dots[i][0] == xOf0thElement) {
                height = Math.abs(dots[i][1] - dots[0][1]);
            } else {
                width = Math.abs(dots[i][0] - dots[0][0]);
            }
            // x 값이 같은 element에서는 height를 빼오고,
            // 그 외에는 width를 빼올 수 있다.
        }
        return height * width;
    }

    // 풀이 3(다른 풀이 참고)
    public int solution3(int[][] dots) {
        int maxX = dots[0][0];
        int minX = dots[0][0];
        int maxY = dots[0][1];
        int minY = dots[0][1];

        for(int[] dot : dots) {
            if(dot[0] >= maxX) {
                maxX = dot[0];
            } else { // 직사각형이기 때문에 else if로 다른 조건 주는 게 아니라 else로 묶어도 상관 없다. (단 두 값만 존재)
                minX = dot[0];
            }

            if(dot[1] >= maxY) {
                maxY = dot[1];
            } else { // 직사각형이기 때문에 else if로 다른 조건 주는 게 아니라 else로 묶어도 상관 없다. (단 두 값만 존재)
                minY = dot[1];
            }
        }
        return (maxX - minX) * (maxY - minY);
    }
}
