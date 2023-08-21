package programmers.lv0;

// lv0 OX퀴즈
public class Lessons120907 {
    static final String CORRECT = "O";
    static final String INCORRECT = "X";

    public String[] solution1(String[] quiz) {
        String[] answer = new String[quiz.length];

        for (int i = 0; i < quiz.length; i++) {
            String[] ithQuizSplit = quiz[i].split(" ");
            String ithQuizOperator = ithQuizSplit[1];
            int ithQuizResult = Integer.parseInt(ithQuizSplit[4]);
            int ithQuizOperand1 = Integer.parseInt(ithQuizSplit[0]);
            int ithQuizOperand2 = Integer.parseInt(ithQuizSplit[2]);

            if (ithQuizOperator.equals("+")) {
                answer[i] = ithQuizOperand1 + ithQuizOperand2 == ithQuizResult ? CORRECT : INCORRECT;
            } else if (ithQuizOperator.equals("-")) {
                answer[i] = ithQuizOperand1 - ithQuizOperand2 == ithQuizResult ? CORRECT : INCORRECT;
            }
        }

        return answer;
    }
}
