package problem_16235;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
	
    @Test
    void exampleTest() {
        int result = someMethod();
        Assertions.assertEquals(42, result, "The result should be 42");
    }
    private int someMethod() {
        return 42;
    }
}
