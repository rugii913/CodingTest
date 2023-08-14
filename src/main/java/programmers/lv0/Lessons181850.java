package programmers.lv0;

// lv0 정수 부분
public class Lessons181850 {
    /*
    * - 래퍼 클래스의 intValue() 같은 메서드들 있는 것을 기억
    * */

    // 풀이 1
    public int solution1(double flo) {
        // return (int) Math.floor(flo); - 굳이 Math.floor(flo)를 사용한 후 (int)로 캐스팅하면 속도가 느리다.
        // return ((Double) flo).intValue(); - (Double)로 감싸면 double.intValue()를 사용할 수 있다. 속도도 느리지 않다.
        return (int) flo;
    }

    // 풀이 2(다른 풀이 참고)
    public int solution2(double flo) {
        return Integer.parseInt(Double.toString(flo).substring(0, Double.toString(flo).indexOf(".")));
    }
}
