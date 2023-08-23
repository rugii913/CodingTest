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
            if (coeff == 1) { // x의 계수가 1인 경우 때문에 한참 헤맸음
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

    // 풀이 2(다른 풀이 참고) - 풀이 1에 비해 많이 느림 약 10ms 정도
    // - 아마 마지막 삼항 연산자 부분 때문인 것 같다.
    // - 마지막 부분만 풀이 1 방식으로 변경해도 10ms -> 0.1ms 정도로 줄어든다.(풀이 1보다 빠름)
    public String solution2(String polynomial) {
        /*
        변수명 수정함 xCount -> coeff, num -> constant, s -> term
        int xCount = 0;
        int num = 0;
        */
        int coeff = 0;
        int constant = 0;

        for (String term : polynomial.split(" ")) {
            if (term.contains("x")) {
                coeff += term.equals("x") ? 1 : Integer.parseInt(term.replaceAll("x", ""));
            } else if (!term.equals("+")) {
                constant += Integer.parseInt(term);
            }
        }
        return (coeff != 0 ? coeff > 1 ? coeff + "x" : "x" : "") + (constant != 0 ? (coeff != 0 ? " + " : "") + constant : coeff == 0 ? "0" : "");
        // String + 연산, 삼항 연산 등 때문에 느린 듯하다.
    }


    // 풀이 3(다른 풀이 참고) - StringTokenizer 사용, 풀이 1보다 약간 더 빠름
    // StringTokenizer - string.split(~)보다 강화된 것처럼 생각해볼 수 있을 듯
    public String solution3(String polynomial) {
        // StringTokenizer st = new StringTokenizer(polynomial, " + "); // delim 인자 " + "로 넣으나 " +"로 넣으나 같음
        StringTokenizer st = new StringTokenizer(polynomial, " +");

        int xsum = 0; // 1차항 계수 합
        int sum = 0; // 상수항 계수 합
        while (st.hasMoreTokens()) {
            String term = st.nextToken(); // 변수명 변경 str -> term

            if (term.contains("x")) {
                String coeff = term.replace("x", ""); // 변수명 변경 x -> coeff
                // System.out.println(x); 원래 풀이 중 필요 없는 부분 주석 처리
                if (coeff.isBlank()) {
                    xsum += 1;
                } else {
                    xsum += Integer.parseInt(coeff);
                }
            } else {
                sum += Integer.parseInt(term);
            }
        }

        StringBuilder sb = new StringBuilder(); // 원래 풀이에서 위에 있던 new StringBuilder() 위치를 여기로 변경  
        if (xsum == 0) {
            sb.append(sum);
        } else if (sum == 0) {
            if (xsum == 1) {
                sb.append("x");
            } else {
                sb.append(xsum).append("x");
            }
        } else {
            if (xsum == 1) {
                sb.append("x").append(" + ").append(sum);
            } else {
                sb.append(xsum).append("x").append(" + ").append(sum);
            }

        }

        return sb.toString();
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
