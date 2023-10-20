package programmers.lv0;

// lv0 커피 심부름
public class Lessons181837 {

    // 풀이 1
    public int solution1(String[] order) {
        int price = 0;

        for (String orderItem : order) {
            if (orderItem.contains("americano")) {
                price += 4500;
            } else if (orderItem.contains("latte")) {
                price += 5000;
            } else {
                price += 4500;
            }
        }

        return price;
    }
}
