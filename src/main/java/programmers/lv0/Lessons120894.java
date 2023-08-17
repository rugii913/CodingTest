package programmers.lv0;

import java.util.Map;

// lv0 영어가 싫어요
public class Lessons120894 {

    // 풀이 1 - 생각보다 그렇게 느리진 않다.
    public long solution1(String numbers) {
        numbers = numbers.replace("zero", "0")
                .replace("one", "1")
                .replace("two", "2")
                .replace("three", "3")
                .replace("four", "4")
                .replace("five", "5")
                .replace("six", "6")
                .replace("seven", "7")
                .replace("eight", "8")
                .replace("nine", "9");

        return Long.parseLong(numbers);
    }

    // 풀이 2(다른 풀이 참고) - 원본에서는 replaceAll(~)을 사용했는데 replace(~)를 사용해도 된다.
    public long solution2(String numbers) {
        String[] numbers_arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for (int i = 0; i < numbers_arr.length; i++) {
            numbers = numbers.replace(numbers_arr[i], String.valueOf(i));
            // numbers = numbers.replaceAll(numbers_arr[i], String.valueOf(i)); 로 가능
            // -> replaceAll의 첫번째 인수가 정규식이어야 하는데, 정규식 형태가 아니라 일반적인 String이 들어가더라도 잘 작동한다.
        }

        return Long.parseLong(numbers);
    }

    // 풀이 3(다른 풀이 참고) - Map 사용
    public long solution3(String numbers) {
        Map<String, Integer> numberMap
                = Map.of("zero", 0, "one", 1, "two", 2,"three", 3, "four", 4, "five", 5, "six", 6, "seven", 7, "eight", 8, "nine", 9);

        StringBuilder answer = new StringBuilder(); // 문자열 숫자들 저장할 sb
        StringBuilder builder = new StringBuilder(); // 영어를 숫자 문자열로 바꿀 때 사용할 임시 작업용 sb

        for (String str : numbers.split("")) {
            builder.append(str);
            if (numberMap.containsKey(builder.toString())) {
                answer.append(numberMap.get(builder.toString()));
                builder.delete(0, builder.length());
            }
        }
        return Long.parseLong(answer.toString());
    }

    // 풀이 3-1(다른 풀이 참고) - Map 사용 - 풀이 3보다 간결, 속도 면에서도 더 좋음
    public long solution3_1(String numbers) {
        Map<String, String> numberMap
                = Map.of("zero", "0", "one", "1", "two", "2","three", "3",
                "four", "4", "five", "5", "six", "6",
                "seven", "7", "eight", "8", "nine", "9");

        for (String str : numberMap.keySet()) {
            numbers = numbers.replace(str, numberMap.get(str));
        }

        return Long.parseLong(numbers);
    }
}
