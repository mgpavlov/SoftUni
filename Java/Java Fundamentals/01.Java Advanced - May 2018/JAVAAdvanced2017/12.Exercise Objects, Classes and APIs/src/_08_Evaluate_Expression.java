import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class _08_Evaluate_Expression {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] expression = bf.readLine().split(" ");
        Deque<String> output = new ArrayDeque<>(); //queue
        Deque<String> operators = new ArrayDeque<>(); //stack
        Map<String, Integer> priorites = new HashMap<>();
        priorites.put("*", 3);
        priorites.put("/", 3);
        priorites.put("+", 2);
        priorites.put("-", 2);
        priorites.put("(", 1);

        for (String token : expression) {
            try {
                double num = Double.parseDouble(token);
                output.add(token);
            } catch (Exception e) {
                if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                    if (operators.isEmpty()) {
                        operators.push(token);
                        continue;
                    } else {
                        while (!operators.isEmpty() && priorites.get(operators.peek()) >= priorites.get(token)) {
                            String operatorOnTopOfStack = operators.pop();
                            output.add(operatorOnTopOfStack);
                        }
                    }
                    operators.push(token);
                } else if (token.equals("(")) {
                    operators.push(token);
                } else if (token.equals(")")) {
                    while (!operators.peek().equals("(")) {
                        String operatorOnTopOfStack = operators.pop();
                        output.add(operatorOnTopOfStack);
                    }
                    operators.pop();
                }
            }
        }

        while (!operators.isEmpty()) {
            String operatorOnTopOfStack = operators.pop();
            output.add(operatorOnTopOfStack);
        }

        double result = 0.0;
        Deque<Double> operands = new ArrayDeque();

        for (String str : output) {
            try {
                double num = Double.parseDouble(str);
                operands.push(num);
            } catch (Exception e) {
                result = 0.0;
                double operand2 = 0.0;
                double operand1 = 0.0;
                switch (str.charAt(0)) {
                    case '+':
                        operand2 = operands.pop();
                        operand1 = operands.pop();
                        result = operand1 + operand2;
                        operands.push(result);
                        break;
                    case '-':
                        operand2 = operands.pop();
                        operand1 = operands.pop();
                        result = operand1 - operand2;
                        operands.push(result);
                        break;
                    case '*':
                        operand2 = operands.pop();
                        operand1 = operands.pop();
                        result = operand1 * operand2;
                        operands.push(result);
                        break;
                    case '/':
                        operand2 = operands.pop();
                        operand1 = operands.pop();
                        result = operand1 / operand2;
                        operands.push(result);
                        break;
                    default:

                        break;
                }
            }
        }

        System.out.printf("%.2f", result);
    }
}

