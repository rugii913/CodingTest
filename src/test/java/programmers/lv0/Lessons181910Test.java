package programmers.lv0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lessons181910Test {
    String my_string = "ProgrammerS123";
    int n = 11;
    String result = "grammerS123";
    Lessons181910 test = new Lessons181910();

    @Test
    void solution2() {
        assertEquals(result, test.solution3(my_string, n));
    }
}