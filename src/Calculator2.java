import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;


public class Calculator2 {
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
        mapOperations.put("/", (a, b) -> {
            if (b == 0)
                throw new ArithmeticException("Division by null");
            return a / b;
        });
    }


    public static Integer computeExpression(String str) {
        if (str == null || str.isBlank())
            throw new IllegalArgumentException("expression cannot be null or empty");


        str = str.replaceAll("\\s+", "");// 5 * 9->5*9


        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();


        tokenize(str, numbers, operators);
        applyMulDiv(numbers, operators);


        return applyAddSub(numbers, operators);
    }


    private static Integer applyAddSub(List<Integer> numbers, List<Character> operators) {
        int res = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            BinaryOperator<Integer> operation = mapOperations.get(String.valueOf(op));
            if (operation == null)
                throw new IllegalStateException("unknown operator");
            int right = numbers.get(i + 1);
            res = operation.apply(res, right);
        }
        return res;
    }


    private static void applyMulDiv(List<Integer> numbers, List<Character> operators) {
        int i = 0;
        while (i < operators.size()) {
            char op = operators.get(i);
            if (op == '*' || op == '/') {
                int left = numbers.get(i);
                int right = numbers.get(i + 1);
                BinaryOperator<Integer> operation = mapOperations.get(String.valueOf(op));
                if (operation == null)
                    throw new IllegalStateException("unknown operator");
                int res = operation.apply(left, right);
                numbers.set(i, res);//3,4 *->3*4=12 12-> next number ->remove *
                numbers.remove(i + 1);
                operators.remove(i);
            } else
                i++;
        }


    }


    private static void tokenize(String str, List<Integer> numbers, List<Character> operators) {
        int i = 0;
        int n = str.length();
        boolean expectNumber = true;
        while (i < n) {
            char c = str.charAt(i);
            if (expectNumber) {
                int sign = 1;
                if (c == '+' || c == '-') {//-1 +4
                    if (c == '-')
                        sign = -1;// 1+4+6- 1+4+*4
                    i++;
                    if (i >= n || !Character.isDigit(str.charAt(i))) {
                        throw new IllegalArgumentException("expected digit after  unary sign");
                    }
                    c = str.charAt(i);
                }
                if (!Character.isDigit(c))
                    throw new IllegalArgumentException("expected digit");
                int value = 0;
                while (i < n && Character.isDigit(str.charAt(i))) {
                    value = value * 10 + (str.charAt(i) - '0');// 1234 0+1->1*10+2->12->12*10+3->123*10+4
                    i++;
                }
                numbers.add(sign * value);
                expectNumber = false;// дальше жду уже оператор


            } else {
                if ("+-*/".indexOf(c) >= 0) {
                    operators.add(c);
                    i++;
                    expectNumber = true;
                } else
                    throw new IllegalArgumentException("expected operator");
            }


        }
        if (expectNumber)
            throw new IllegalArgumentException("expression cannot end with an operator");
        if (numbers.size() != operators.size() + 1)
            throw new IllegalStateException("internal parsing error");


    }


    public static void main(String[] args) {
        System.out.println(computeExpression("34-2*5/4")); // 32
        System.out.println(computeExpression("34 - 2 * 5 / 4")); // 32
        System.out.println(computeExpression("2+3*4")); // 14
        System.out.println(computeExpression("2 +  3 * 4")); // 14
        System.out.println(computeExpression("10/2+3*2")); // 11
        System.out.println(computeExpression(" 10 / 2 + 3 * 2  ")); // 11
        System.out.println(computeExpression("10+100*2/1")); // 210
        System.out.println(computeExpression("10 + 100 * 2 / 1")); // 210
        System.out.println(computeExpression("10/2++3*2")); // 11
        System.out.println(computeExpression("1--2"));
    }
}


