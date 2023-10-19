package programmers.lv0;

import java.time.LocalDate;
import java.util.Arrays;

// lv0 날짜 비교하기
public class Lessons181838 {

    // 풀이 1 - 의미가 다가오지 않는 코딩
    public int solution1(int[] date1, int[] date2) {
        // date1이 date2보다 앞서는 날짜라면 1을, 아니면 0을 return
            if (date1[0] > date2[0]) {
                return 0;
            } else if (date1[0] == date2[0] && date1[1] > date2[1]) {
                return 0;
            } else if (date1[0] == date2[0] && date1[1] == date2[1] && date1[2] >= date2[2]) {
                return 0;
            } else {
                return 1;
            }
    }

    // 풀이 1-1
    public int solution1_1(int[] date1, int[] date2) {
        if (date1[0] < date2[0]) {
            return 1;
        } else if (date1[0] == date2[0] && date1[1] < date2[1]) {
            return 1;
        } else if (date1[0] == date2[0] && date1[1] == date2[1] && date1[2] < date2[2]) {
            return 1;
        } else {
            return 0;
        }
    }

    // 풀이 1-2
    public int solution1_2(int[] date1, int[] date2) {
        int year1 = date1[0];
        int year2 = date2[0];
        int month1 = date1[1];
        int month2 = date2[1];
        int day1 = date1[2];
        int day2 = date2[2];

        if (year1 < year2) {
            return 1;
        } else if (year1 == year2 && month1 < month2) {
            return 1;
        } else if (year1 == year2 && month1 == month2 && day1 < day2) {
            return 1;
        } else {
            return 0;
        }
    }

    // 풀이 2(다른 풀이 참고) - Arrays.compare(~) 사용
    public int solution2(int[] date1, int[] date2) {
        // Arrays.compare는 Java 9부터 시작,
        // lexicographically less이면 음수가 나오는 형태
        //  - 내부에서 Integer의 static 메서드 static int compare(int x, int y) 사용 (Integer.compare(a, b)는 첫번째 인자가 더 작으면 음수)
        // 길이가 같은 두 배열을 넘긴다면, 인덱스 0부터 훑는다고 생각하면 될 것,
        // 첫번째 배열 인자에서 더 작은 원소가 빨리 발견되면 음수, 두번째 배열 인자에서 더 작은 원소가 빨리 발견되면 양수
        return Arrays.compare(date1, date2) < 0 ? 1 : 0;
    }

    // 풀이 3(다른 풀이 참고) - LocalDate 클래스
    public int solution3(int[] date1, int[] date2) {
        /*
        * LocalDate의 주요 필드는 int year, short month, short day,
        * 그리고 이를 활용할만한 몇 가지 메서드들이 있다고 보면 됨 - now, of 같은 static method 등...
        *
        * LocalDate의 isBefore(~)는 비교할 대상으로 들어온 파라미터가, 같은 LocalDate인 경우 아래의 compareTo0(~) 메서드를 사용하는 것으로 구현되어 있음
        * int compareTo0(LocalDate otherDate) {
        *     int cmp = (year - otherDate.year);
        *     if (cmp == 0) {
        *         cmp = (month - otherDate.month);
        *         if (cmp == 0) {
        *             cmp = (day - otherDate.day);
        *         }
        *     }
        *     return cmp;
        * }
        * */

        // return LocalDate.of(date1[0], date1[1], date1[2]).isBefore(LocalDate.of(date2[0], date2[1], date2[2])) ? 1 : 0;

        LocalDate dateA = LocalDate.of(date1[0], date1[1], date1[2]);
        LocalDate dateB = LocalDate.of(date2[0], date2[1], date2[2]);

        if (dateA.isBefore(dateB)) {
            return 1;
        } else {
            return 0;
        }
    }

    // 풀이 4(다른 풀이 참고) - 예능 같은 풀이들
    public int solution4(int[] date1, int[] date2) {
        return date1[0] * 10000 + date1[1] * 100 + date1[2] < date2[0] * 10000 + date2[1] * 100 + date2[2] ? 1 : 0;
        // return Integer.parseInt(date1[0] + "" + date1[1] + "" + date1[2]) >= Integer.parseInt(date2[0] + "" + date2[1] + "" + date2[2]) ? 0 : 1;
    }
}
