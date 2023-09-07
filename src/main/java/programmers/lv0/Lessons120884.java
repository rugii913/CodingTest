package programmers.lv0;

// lv0 치킨 쿠폰 *사람이 이해하기 쉬운 변수를 고려
public class Lessons120884 {
    // ex1. chicken = 18일 때, 쿠폰은 총 19장으로 서비스는 1개 / chicken = 19일 때, 쿠폰은 총 20장으로 서비스는 2개
    // ex2. chicken = 99일 때, 쿠폰은 총 109장으로 서비스는 10개 / chicken = 100일 때, 쿠폰은 총 110장으로 서비스는 11개

    // 풀이 1
    public int solution1(int chicken) {
        int service = 0;
        int coupon = chicken;
        while (coupon / 10 != 0) {
            service += coupon / 10;
            coupon = coupon - (coupon / 10 * 10) + coupon / 10;
        }
        return service;
    }

    // 풀이 1-1(다른 풀이 참고) - 처음에는 이런 방식을 생각했으나 chicken이라는 변수의 의미가 이상해지는 부분이 생김
    public int solution1_1(int chicken) {
        int answer = 0;
        while (chicken >= 10) {
            int service = chicken / 10;
            int remain = chicken % 10;

            chicken = service + remain; // 이 부분이 chicken이라는 변수를 사람이 이해하기에 이상해진다.

            answer += service;
        }
        return answer;
    }

    // 풀이 1-2(다른 풀이 참고) - 역시 변수의 의미를 받아들이기 어려운 부분이 생긴다.
    public int solution1_2(int chicken) {
        int answer = 0;
        while (chicken >= 10) {
            answer += chicken / 10;
            chicken = chicken / 10 + chicken % 10; // 이 부분이 chicken이라는 변수를 사람이 이해하기에 이상해진다.
        }
        return answer;
    }

    // 풀이 2(다른 풀이 참고)
    // 단순하게 9마리 시킬 때마다 1마리 서비스라고 생각(서비스에도 쿠폰이 딸려오므로 복잡하게 풀이 1처럼 합치고 나누고 할 필요 없음)
    // 딱 9마리면 실제로는 서비스가 없으므로 제외(나누어 떨어질 떄)
    public int solution2(int chicken) {
        int answer = chicken / 9;
        if (chicken > 1 && chicken % 9 == 0) {
            answer--;
        }

        return answer;
    }
    // 풀이 2-1 - 어차피 chicken >= 0으로 주어지므로, 위 풀이를 받아들인다면 아래 풀이도 가능
    public int solution2_1(int chicken) {
        return (chicken - 1) / 9;
    }
}
