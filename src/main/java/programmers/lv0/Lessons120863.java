package programmers.lv0;

import java.util.StringTokenizer;

// lv0 다항식 더하기
public class Lessons120863 {
    // NumberFormatException 발생, 어디서 오류 생겼는지 도저히 감이 안 와서 IDE로 넘어옴
    // => regex 문제였음
    // (정규 표현식 관련 참고)
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html#sum
    // https://sooftware.io/regex/
    // https://couplewith.tistory.com/91
    // https://velog.io/@minji/Java-%EC%A0%95%EA%B7%9C%ED%91%9C%ED%98%84%EC%8B%9DString.replaceAll-%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%B9%98%ED%99%98

    // 풀이 1 - split(regex) 사용 +14점???
    public String solution1(String polynomial) {
        int coeff = 0;
        int constant = 0;

        // char로 생각 indexOf('x') 사용하면?
        // for (String term : polynomial.split(" + ")) { // split의 매개변수는 regex이기 때문에 이렇게 넣으면 안 된다.
        // for (String term : polynomial.split("\\s[+]\\s")) { // -> 이건 가능함, 그런데 이상하게 느리다.
        // enhanced for 사용할 때, ( ~ ) 안에서 다른 연산을 시키지 말고, 객체만 주는 게 나은 것 같다.
        String[] terms = polynomial.replace(" ", "").split("[+]");
        // String[] terms = polynomial.replace(" ", "").split(\\s"[+]\\s"); //이렇게 바꿔도 속도가 많이 느려짐
        for (String term : terms) {
            if (term.contains("x")) {
                if (term.length() > 1) {
                    // String coeffString = term.substring(0, term.length() - 1);
                    // coeff += Integer.parseInt(coeffString); // parseInt에 바로 CharSequence 넣을 수 있음
                    coeff += Integer.parseInt(term, 0, term.length() - 1, 10);
                } else { // x의 계수가 1인 경우
                    coeff++;
                }

            } else {
                constant += Integer.parseInt(term);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (coeff != 0) {
            if (coeff == 1) { // x의 계수가 1인 경우의 반환값 때문에 한참 헤맸음
                sb.append('x');
            } else {
                sb.append(coeff).append("x");
            }

            if (constant != 0) {
                sb.append(" + ").append(constant);
            }
            return sb.toString();

        } else if (constant != 0) {
            return String.valueOf(constant);
        } else {
            return String.valueOf(0);
        }
    }

    // 풀이 1 주석 제거한 코드 - split(regex) 사용
    public String solution1_removedComment(String polynomial) {
        int coeff = 0;
        int constant = 0;

        String[] terms = polynomial.replace(" ", "").split("[+]");
        for (String term : terms) {
            if (term.contains("x")) {
                if (term.length() > 1) {
                    coeff += Integer.parseInt(term, 0, term.length() - 1, 10);
                } else {
                    coeff++;
                }

            } else {
                constant += Integer.parseInt(term);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (coeff != 0) {
            if (coeff == 1) {
                sb.append('x');
            } else {
                sb.append(coeff).append("x");
            }

            if (constant != 0) {
                sb.append(" + ").append(constant);
            }
            return sb.toString();

        } else if (constant != 0) {
            return String.valueOf(constant);
        } else {
            return String.valueOf(0);
        }
    }

    // 풀이 2(다른 풀이 참고) - 풀이 1에 비해 많이 느림 약 10ms 정도 - 아마 마지막 삼항 연산자 부분 때문인 것 같다.
    public String solution2(String polynomial) {
        int xCount = 0;
        int num = 0;

        for (String s : polynomial.split(" ")) {
            if (s.contains("x")) {
                xCount += s.equals("x") ? 1 : Integer.parseInt(s.replaceAll("x", ""));
            } else if (!s.equals("+")) {
                num += Integer.parseInt(s);
            }
        }
        return (xCount != 0 ? xCount > 1 ? xCount + "x" : "x" : "") + (num != 0 ? (xCount != 0 ? " + " : "") + num : xCount == 0 ? "0" : "");
    }

    // 풀이 실패 - replace, split 사용하지 않으려했으나 포기
    public String solutionx(String polynomial) {
        char[] polynomialCharArray = polynomial.toCharArray();

        int numOfOperator = 0;
        int[] tmp = new int[polynomialCharArray.length];
        for (int i = 0; i < polynomialCharArray.length; i++) {
            if (polynomialCharArray[i] == '+') {
                tmp[numOfOperator++] = i;
            }
        }

        int[] operatorIndices = new int[numOfOperator];
        for (int i = 0; i < numOfOperator; i++) {
            operatorIndices[i] = tmp[i];
        }

        int numOfTerms = numOfOperator + 1;
        int[] boundaryIndices = new int[numOfTerms * 2];

        boundaryIndices[0] = 0;
        boundaryIndices[boundaryIndices.length -1] = polynomialCharArray.length;
        for (int i = 1; i < operatorIndices.length; i += 2) {
            boundaryIndices[i] = operatorIndices[i] - 2;
            boundaryIndices[i + 1] = operatorIndices[i] + 2;
        }

        StringBuffer[] termStringBuffers = new StringBuffer[numOfTerms];

        for (int i = 0; i < numOfTerms; i++) {
            termStringBuffers[i] = new StringBuffer()
                    .append(polynomialCharArray, boundaryIndices[i], boundaryIndices[i + 1] - boundaryIndices[i] + 1);
        }

        int coeff = 0;
        int constant = 0;
        for (int i = 0; i < numOfTerms; i++) {
            if (termStringBuffers[i].charAt(termStringBuffers[i].length()) == 'x') {
                constant += Integer.parseInt(termStringBuffers[i], 0, termStringBuffers.length - 1, 10);
            } else {
                coeff += Integer.parseInt(termStringBuffers[i], 0, termStringBuffers.length, 10);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (coeff == 0) {
            return String.valueOf(constant);
        } else if (constant == 0) {
            return sb.append(coeff).append("x").toString();
        } else {
            return sb.append(coeff).append("x + ").append(constant).toString();
        }
    }
}
