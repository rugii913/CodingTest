package programmers.lv0;

import java.util.Arrays;

// lv0 겹치는 선분의 길이
public class Lessons120876 {
    // 풀이 x - 단순히 두 line을 비교해서 겹치는 길이를 더하면, 세 선분이 모두 겹치는 부분이 중복 계산된다.
    // ex. lines = [[0, 5], [3, 9], [1, 10]], result = 8에서 3 ~ 5 같은 부분
    // 그리고 getOverlapLength(int[] line1, int[] line2) 여기서도 잘못된 부분이 있음 
    // line1이 line2를 아예 포함하고 있는 경우를 고려하고 있지 않음
    public int solutionx(int[][] lines) {
        int sumOfOverlapLength = 0;
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                sumOfOverlapLength += getOverlapLengthx(lines[i], lines[j]);
            }
        }
        return sumOfOverlapLength;
    }
    private int getOverlapLengthx(int[] line1, int[] line2) {
        if (line1[0] <= line2[0]) {
            if (line1[1] <= line2[0]) {
                return 0;
            } else {
                return line1[1] - line2[0];
            }
        } else {
            return getOverlapLengthx(line2, line1);
        }
    }

    // 풀이 1x
    // 교집합 구하기 -> 교집합끼리의 합집합 구하기 -> 길이 구하기?
    public int solution1x(int[][] lines) {
        return 0;
    }

    // 풀이 2x - 중단
    // 정렬
    public int solution2x(int[][] lines) {
        /* 시도 1 포함하고 있는 경우 고려 x
        int a = lines[0][1] - lines[1][0];
        int b = lines[1][1] - lines[2][0];
        int c = lines[0][1] - lines[2][0];

        int sum = 0;
        if (a > 0) sum += a;
        if (b > 0) sum += b;
        if (c > 0) sum -= c;
        return sum;
         */
        /* 시도 2 테스트 1, 2, 9 실패
        int overlap0And1 = Math.max(0, Math.min(lines[0][1], lines[1][1]) - lines[1][0]);
        int overlap1And2 = Math.max(0, Math.min(lines[1][1], lines[2][1]) - lines[2][0]);
        int overlap0And2 = Math.max(0, Math.min(lines[0][1], lines[2][1]) - lines[2][0]);

        return overlapFirstAndSecond + overlapSecondAndThird - overlapFirstAndThird;
         */
        linesSortx(lines);

        int[] line0 = lines[0];
        int[] line1 = lines[1];
        int[] line2 = lines[2];

        int[] overlap0And1 = getOverlapx(line0, line1);
        int[] overlap1And2 = getOverlapx(line1, line2);
        int[] overlap0And2 = getOverlapx(line0, line2);

        if (overlap0And2 != IntLine.NOT_OVERLAPPED.getLineArray()) {
            if (overlap0And1[1] <= overlap1And2[0]) {
                return (overlap0And1[1] - overlap0And1[0]) + (overlap1And2[1] - overlap1And2[0]);
            } else {
                return (overlap0And1[1] - overlap0And1[0]) + (overlap1And2[1] - overlap1And2[0]) - (overlap0And1[1] - overlap1And2[0]);
            }
        } else { // overlap0And2 == NOT_OVERLAPPED
            
            return 0;
        }
    }
    private void linesSortx(int[][] lines) { // 정렬 잘못 짬 solution2 앞부분 참고할 것
        // int[] tmp = new int[lines[0].length]; // 초기화 필요 없음
        int[] tmp;

        for (int i = 0; i < lines.length - 1; i++) {
            for (int j = i; j < lines.length - 1; j++) {
                if (lines[j][0] > lines[j + 1][0]) { // 시작 위치가 작은 것을 앞으로
                    tmp= lines[j + 1];
                    lines[j + 1] = lines[j];
                    lines[j] = tmp;
                }

                if (lines[j][0] == lines[j + 1][0] && lines[j][1] > lines[j + 1][1]) { // 시작 위치가 같으면 끝 위치가 작은 것을 앞으로
                    tmp= lines[j + 1];
                    lines[j + 1] = lines[j];
                    lines[j] = tmp;
                }
            }
        }
    }
    private int[] getOverlapx(int[] line1, int[] line2) {
        // 겹치는 부분 없는 것 먼저 return
        if (line1[1] <= line2[0] || line2[1] <= line1[0]) {
            return IntLine.NOT_OVERLAPPED.getLineArray();
        }

        // 정렬
        int[] tmp;
        if (line1[0] > line2[0]) { // 시작 위치가 작은 것을 앞으로
            tmp = line2;
            line2 = line1;
            line1 = tmp;
        }

        if (line1[0] == line2[0] && line1[1] > line2[1]) { // 시작 위치가 같으면 끝 위치가 작은 것을 앞으로
            tmp = line2;
            line2 = line1;
            line1 = tmp;
        }

        // 정렬 후 겹치는 구간 구하기
        // line1이 line2를 포함하는 형태라면 overlap의 end == line2[1]
        int[] overlap = {line2[0], Math.min(line1[1], line2[1])};
        return overlap;
    }

    // 풀이 1 - 점으로 따지자 - 테스트 2, 5, 6 실패
    public int solution1(int[][] lines) {
        /* // 전부 순회 말고 그냥 간단하게
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length; j++) {
                if (lines[i][j] > max) {
                    max = lines[i][j];
                }
                if (lines[i][j] < min) {
                    min = lines[i][j];
                }
            }
        }
        */
        int max = Math.max(Math.max(lines[0][1], lines[1][1]), lines[2][1]);
        int min = Math.min(Math.min(lines[0][0], lines[1][0]), lines[2][0]);

        int[] countIncluded = new int[max - min - 1];
        for (int i = 1; i < max - min; i++) { // i = 1일 때가 (min + 1), i = max - min - 1일 때가 (max - 1)
            for (int[] line : lines) {
                if (min + i > line[0] && min + i < line[1]) {
                    countIncluded[i - 1]++;
                }
            }
        }

        int countDot = 0;
        for (int i = 0; i < countIncluded.length; i++) {
            if (countIncluded[i] >= 2) {
                countDot++;
            }
        }

        int countOfZero = 0;
        for (int i = 0; i < countIncluded.length; i++) {
            if (countIncluded[i] == 0) {
                countOfZero++;
            }
        }

        if (countOfZero == 0) { // 전부 이어져 있는 경우
            return countDot + 2;
        } else if (countOfZero == 1) { // [0, 2] [2, 5]처럼 붙어있는 점인 경우
            return countDot + 1;
        } else { // 영역이 아예 두 군데로 나눠져 있는 경우
            return countDot + 0;
        }
    }
    // 풀이 1_1 - 점으로 따지기 수정 - 테스트 3, 7 실패
    public int solution1_1(int[][] lines) {
        int max = Math.max(Math.max(lines[0][1], lines[1][1]), lines[2][1]);
        int min = Math.min(Math.min(lines[0][0], lines[1][0]), lines[2][0]);

        int[] dots = new int[max - min + 1]; // => min(==min+0)이 dots[0], max(==min+max-min)가 dots[max - min]

        int countIntersection = 0;
        int[][] intersections = {};
        int sectionStart = Integer.MAX_VALUE;
        int sectionEnd = Integer.MIN_VALUE;

        for (int i = 0; i < dots.length; i++) {
            for (int[] line : lines) {
                if (min + i >= line[0] && min + i <= line[1]) {
                    dots[i]++;
                }
            }
            if (dots[i] >= 2 && sectionStart == Integer.MAX_VALUE) { // sectionStart를 기록
                sectionStart = i;
            } else if (dots[i] == 1 && sectionStart != Integer.MAX_VALUE) { // sectionEnd와 countIntersection을 기록
                sectionEnd = i - 1;
                int[][] tmp = new int[++countIntersection][2];
                System.arraycopy(intersections, 0, tmp, 0, intersections.length);
                tmp[countIntersection - 1][0] = sectionStart;
                tmp[countIntersection - 1][1] = sectionEnd;
                intersections = tmp;

                sectionStart = Integer.MAX_VALUE;
                sectionEnd = Integer.MIN_VALUE;
            } else if (i == dots.length -1 && sectionStart != Integer.MAX_VALUE) {
                sectionEnd = i;
                int[][] tmp = new int[++countIntersection][2];
                System.arraycopy(intersections, 0, tmp, 0, intersections.length);
                tmp[countIntersection - 1][0] = sectionStart;
                tmp[countIntersection - 1][1] = sectionEnd;
                intersections = tmp;
            }
        }

        if (intersections.length == 0) {
            return 0;
        } else if (intersections.length == 1) {
            return intersections[0][1] - intersections[0][0];
        } else {
            return (intersections[0][1] - intersections[0][0]) + (intersections[1][1] - intersections[1][0]);
        }
    }
    // 풀이 1_2 - 점으로 따지기 수정 0.5 사용 - 통과
    public int solution1_2(int[][] lines) {
        int max = Math.max(Math.max(lines[0][1], lines[1][1]), lines[2][1]);
        int min = Math.min(Math.min(lines[0][0], lines[1][0]), lines[2][0]);

        int[] linesOverDots = new int[max - min];
        for (int i = 0; i < max - min; i++) { // i = 0일 때 (min + 0.5), i = max - min - 1일 때가 (max - 0.5)와 대응됨
            for (int[] line : lines) {
                if (min + i + 0.5 > line[0] && min + i + 0.5 < line[1]) {
                    linesOverDots[i]++;
                }
            }
        }

        int countDot = 0;
        for (int i = 0; i < linesOverDots.length; i++) {
            if (linesOverDots[i] >= 2) {
                countDot++;
            }
        }

        return countDot;
    }

    // 풀이 2 - sort 후 경우의 수 - 테스트 1 실패
    // (1) 겹치는 곳이 나눠져 있지 않은 경우 start = line[0][0], end = Math.max(line[0][1], line[1][1])
    // (2) 겹치는 곳이 나눠져 있는 경우 start1 = line[0][0], end1 = Math.max
    public int solution2(int[][] lines) {
        // 정렬 - 시작 위치가 작은 line을 앞으로 + 시작 위치가 같으면 끝 위치 작은 것을 앞으로
        int[] tmp;

        for (int i = 0; i < lines.length - 1; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                if (lines[i][0] > lines[j][0]) { // 시작 위치가 작은 것을 앞으로
                    tmp= lines[j];
                    lines[j] = lines[i];
                    lines[i] = tmp;
                }

                if (lines[i][0] == lines[j][0] && lines[i][1] > lines[j][1]) { // 시작 위치가 같으면 끝 위치가 작은 것을 앞으로
                    tmp= lines[j];
                    lines[j] = lines[i];
                    lines[i] = tmp;
                }
            }
        }
        
        // 겹치는 두 영역이 이어져 있는 경우
        // if (lines[0][1] >= lines[2][0]) { // lines[0][1] == lines[1][1] == lines[2][0]인 경우가 문제가 된다.
        if (lines[0][1] > lines[2][0]) {
            int start = lines[1][0]; // 정렬되어 있으므로 겹치는 start는 어쨌든 lined[1][0]에서 시작
            // end는 lines[0][1], lines[1][1], lines[2][1] 중 두 번째로 큰 값
            int end;
            /*
            if (lines[0][1] <= lines[1][1] && lines[0][1] <= lines[2][1]) {
                end = Math.min(lines[1][1], lines[2][1]);
            } else if (lines[0][1] >= lines[1][1] && lines[0][1] >= lines[2][1]) {
                end = Math.max(lines[1][1], lines[2][1]);
            } else {
                end = lines[0][1];
            }
            */
            int[] endNominee = {lines[0][1], lines[1][1], lines[2][1]};
            Arrays.sort(endNominee);
            end = endNominee[1];
            return end - start;
        }

        // 겹치는 영역이 한 곳만 있거나, 겹치는 두 영역이 나눠져 있는 경우 if (lines[0][1] <= lines[2][0]) { ~ } 생략
        int sum = 0;
        if (lines[0][1] > lines[1][0]) {
            sum += Math.min(lines[0][1], lines[1][1]) - lines[1][0];
        }
        if (lines[1][1] > lines[2][0]) {
            sum += Math.min(lines[1][1], lines[2][1]) - lines[2][0];
        }
        return sum;
    }
    
    // 풀이 3 - 클래스 사용
    public int solution3(int[][] lines) {
        return 0;
    }
}

class IntLine {
    private final int[] lineArray;
    private final int start;
    private final int end;
    private final int length;
    public static final IntLine NOT_OVERLAPPED = null;

    public IntLine(int[] lineArray) {
        if (lineArray.length != 2 || lineArray[0] == lineArray[1]) {
            throw new IllegalArgumentException();
        }
        if (lineArray[0] > lineArray[1]) {
            int tmp = lineArray[0];
            lineArray[1] = lineArray[0];
            lineArray[0] = tmp;
        }

        this.lineArray = lineArray;
        this.start = lineArray[0];
        this.end = lineArray[1];
        this.length = this.end - this.end;
    }

    public IntLine(int start, int end) {
        if (start == end) {
            throw new IllegalArgumentException();
        }
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }

        this.lineArray = new int[]{start, end};
        this.start = start;
        this.end = end;
        this.length = end - start;
    }

    public int[] getLineArray() {
        return lineArray;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getLength() {
        return length;
    }

    public static IntLine[] lineSort(IntLine line1, IntLine line2) {
        IntLine tmp;
        if (line1.getStart() > line2.getStart()) { // 시작 위치가 작은 것을 앞으로
            tmp = line2;
            line2 = line1;
            line1 = tmp;
        }
        return new IntLine[]{line1, line2};
    }

    public IntLine getIntersection(IntLine anotherLine) {
        IntLine[] sortedLines = IntLine.lineSort(this, anotherLine);
        if (sortedLines[0].getEnd() <= sortedLines[1].getStart()) {
            return IntLine.NOT_OVERLAPPED;
        } else {
            return new IntLine(sortedLines[1].getStart(), sortedLines[0].getEnd());
        }
    }

    public IntLine[] getUnion(IntLine anotherLine) {
        if (this.getIntersection(anotherLine) == IntLine.NOT_OVERLAPPED) {
            if (this.getEnd() == anotherLine.getStart() || this.getStart() == anotherLine.getEnd()) { // intersection이 없지만, 서로 붙어있는 경우
                int unionStart = Math.min(this.getStart(), anotherLine.getStart());
                int unionEnd = Math.max(this.getEnd(), anotherLine.getEnd());
                return new IntLine[]{new IntLine(unionStart, unionEnd)};
            } else { // intersection이 없고, 서로 붙어 있지 않은 경우
                return IntLine.lineSort(this, anotherLine);
            }
        } else { // intersection이 있는 경우
            int unionStart = Math.min(this.getStart(), anotherLine.getStart());
            int unionEnd = Math.max(this.getEnd(), anotherLine.getEnd());
            return new IntLine[]{new IntLine(unionStart, unionEnd)};
        }
    }

    public static IntLine[] getUnion(IntLine line1, IntLine line2) {
        int max = Math.max(line1.end, line2.end);
        int min = Math.max(line1.start, line2.start);

        IntLine[] union = {};

        int unionStart = min;
        int unionEnd = max;
        boolean isIncluded = false;
        for (int i = min; i <= max; i++) {
            if (line1.include(i) || line2.include(i)) {
                if (unionStart == min) {
                    unionStart = i;
                    isIncluded = true;
                }
            }
            if (!line1.include(i) && !line2.include(i)) {
                if (isIncluded) {
                    unionEnd = i;
                    isIncluded = false;
                }
                IntLine[] tmp = new IntLine[union.length + 1];
                System.arraycopy(union, 0, tmp, 0, union.length);
                tmp[union.length] = new IntLine(unionStart, unionEnd);
                union = tmp;
            } else if (i == max) {
                unionEnd = i;

                IntLine[] tmp = new IntLine[union.length + 1];
                System.arraycopy(union, 0, tmp, 0, union.length);
                tmp[union.length] = new IntLine(unionStart, unionEnd);
                union = tmp;
            }
        }

        return union;
    }

    public static IntLine[] getUnion(IntLine[] lines) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (IntLine line : lines) {
            if (line.end > max) {
                max = line.end;
            }
            if (line.start < min) {
                min = line.start;
            }
        }

        int unionStart = Integer.MAX_VALUE;
        int unionEnd = Integer.MIN_VALUE;
        for (int i = min; i <= max; i++) {
            for (IntLine line : lines) {
                if (line.include(i)) {

                }
            }
        }

        return null;
    }

    private boolean include(int dot) {
        if (this.end >= dot && this.start <= dot) {
            return true;
        } else {
            return false;
        }
    }
}
