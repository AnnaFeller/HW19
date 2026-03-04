import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;


public class Calculator {
    static Map<String, BinaryOperator<Integer>> mapOperations;
    static {
        mapOperations = new HashMap<>();
//		BinaryOperator<Integer> op1 = new BinaryOperator<>() {
//
//			@Override
//			public Integer apply(Integer t, Integer u) {
//
//				return t + u;
//			}
//		};
//		mapOperations.put("+", op1);
        mapOperations.put("+", (a, b) -> a + b);
        mapOperations.put("*", (a, b) -> a * b);
        mapOperations.put("-", (a, b) -> a - b);
        mapOperations.put("/", (a, b) -> b == 0 ? null : a / b);
    }


    public static Integer computeExpression(String str) {
        str = str.replace("\\s+", "");// 5 * 9->5*9
        if (!isArithmeticExp(str))
            return null;
        String[] operands = getOperands(str);
        System.out.println(Arrays.toString(operands));
        String[] operators = getOperators(str);
        System.out.println(Arrays.toString(operators));
        Integer res = Integer.parseInt(operands[0]);
        for (int i = 1; i < operators.length; i++)
            res = computeOne(res, operators[i], operands[i]);
        return res;
    }


    private static Integer computeOne(Integer res, String operator, String operand) {


        return mapOperations.getOrDefault(operator, (a, b) -> null).apply(res, Integer.parseInt(operand));
    }


    private static String[] getOperators(String str) {


        return str.split("\\d+");// ["",+,-,*]
    }


    private static String[] getOperands(String str) {
        return str.split("\\D+");// 1+5-10*50=>[1,5,10,50]
    }


    //-10+5
    private static boolean isArithmeticExp(String str) {
        String regExp = "\\d+([+*/-]\\d+)*";// 10+6-5/5
        return str.matches(regExp);
    }
    public static void main(String[] args) {
        System.out.println(computeExpression("34-2*5/4")); // 32
        System.out.println(computeExpression("34 - 2 * 5 / 4")); // 32
        System.out.println(computeExpression("2+3*4"));    // 14
        System.out.println(computeExpression("2 +  3 * 4"));    // 14
        System.out.println(computeExpression("10/2+3*2")); // 11
        System.out.println(computeExpression(" 10 / 2 + 3 * 2  ")); // 11
        System.out.println(computeExpression("10+100*2/1")); // 210
        System.out.println(computeExpression("10 + 100 * 2 / 1")); // 210
        System.out.println(computeExpression("10/2+3*2")); // 11
        System.out.println(computeExpression("-10/2+3*2"));
    }
}
