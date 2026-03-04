import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {


    @Test
    void testSimpleAdd() {
        assertEquals(5, Calculator2.computeExpression("2+3"));
    }

    @Test
    void testPriorityMul() {
        assertEquals(14, Calculator2.computeExpression("2+3*4"));
    }

    @Test
    void testPriorityDiv() {
        assertEquals(32, Calculator2.computeExpression("34-2*5/4"));
    }

    @Test
    void testWithSpaces() {
        assertEquals(11, Calculator2.computeExpression(" 10 / 2 + 3 * 2 "));
    }

    @Test
    void testComplexExpression() {
        assertEquals(210, Calculator2.computeExpression("10+100*2/1"));
    }

    // ====== UNARY SIGNS ======

    @Test
    void testDoubleMinus() {
        assertEquals(3, Calculator2.computeExpression("1--2"));
    }

    @Test
    void testNegativeNumber() {
        assertEquals(3, Calculator2.computeExpression("-1+4"));
    }

    // ====== EXCEPTIONS ======

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class,
                () -> Calculator2.computeExpression("10/0"));
    }

    @Test
    void testNullExpression() {
        assertThrows(IllegalArgumentException.class,
                () -> Calculator2.computeExpression(null));
    }

    @Test
    void testEmptyExpression() {
        assertThrows(IllegalArgumentException.class,
                () -> Calculator2.computeExpression("   "));
    }

    @Test
    void testInvalidOperator() {
        assertThrows(IllegalArgumentException.class,
                () -> Calculator2.computeExpression("2+*3"));
    }

    @Test
    void testEndsWithOperator() {
        assertThrows(IllegalArgumentException.class,
                () -> Calculator2.computeExpression("2+3+"));
    }
}