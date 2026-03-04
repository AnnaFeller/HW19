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
    @Test
    void testDoubleMinus() {
        assertEquals(3, Calculator2.computeExpression("1--2"));
    }
    @Test
    void testNegativeNumber() {
        assertEquals(3, Calculator2.computeExpression("-1+4"));
    }
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
    @Test
    void testOnlyNumber() {
        assertEquals(5, Calculator2.computeExpression("5"));
    }
    @Test
    void testNegativeOnlyNumber() {
        assertEquals(-5, Calculator2.computeExpression("-5"));
    }
    @Test
    void testMultipleOperations() {
        assertEquals(9, Calculator2.computeExpression("1+2+3+3"));
    }
    @Test
    void testMulThenAdd() {
        assertEquals(7, Calculator2.computeExpression("1+2*3"));
    }
    @Test
    void testDivThenMul() {
        assertEquals(4, Calculator2.computeExpression("8/2*1"));
    }
}