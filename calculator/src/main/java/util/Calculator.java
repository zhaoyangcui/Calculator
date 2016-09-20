

package util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

    public double result = 0;

    public java.util.Stack<Double> number;
    private java.util.Stack<Operators> operators;

    private ArrayList<Operators> operatorsSave;

    private ArrayList<Character> inputSave;
    private ArrayList<Double> numberSave;
    private int xPoint;
    private int yPoint;

    private int pushOperators = 0;

    public Calculator(String operation) {
        number = new java.util.Stack<Double>();
        operators = new java.util.Stack<Operators>();

        operatorsSave = new ArrayList<Operators>();
        inputSave = new ArrayList<Character>();
        numberSave = new ArrayList<Double>();

        String input = operation;

        for (int i = 0; i < input.length(); i++) {
            inputSave.add(input.charAt(i));
        }

        operators.push(new Operators('#', 0, 0));

        letterFliter();

        saveOperatorToArrayList();

        try {
            if (!operatorsErrorFilter()) {
                throw new Exception();
            } else {
                pushNumberAndOperatorToSStack();
                System.out.println(input + " = " + new BigDecimal(number.get(number.size() - 1).doubleValue()).setScale(4, BigDecimal.ROUND_HALF_UP));
                result = number.get(number.size() - 1).doubleValue();
            }
        } catch (Exception e) {
        }

    }

    public Calculator(String operation, int x, int y) {

        number = new java.util.Stack<Double>();
        operators = new java.util.Stack<Operators>();

        xPoint = x;
        yPoint = y;
        operatorsSave = new ArrayList<Operators>();
        inputSave = new ArrayList<Character>();
        numberSave = new ArrayList<Double>();

        String input = operation;

        for (int i = 0; i < input.length(); i++) {
            inputSave.add(input.charAt(i));
        }

        operators.push(new Operators('#', 0, 0));

        letterFliter();
        deal_Point();
        saveOperatorToArrayList();

        try {
            if (!operatorsErrorFilter()) {
                throw new Exception();
            } else {
                pushNumberAndOperatorToSStack();
                result = number.get(number.size() - 1).doubleValue();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private Boolean operatorsErrorFilter() {
        int leftNumber = 0;
        int rightNumber = 0;
        for (char c : inputSave) {
            if (c == '(')
                leftNumber++;
            if (c == ')')
                rightNumber++;
        }

        if (leftNumber != rightNumber) {
            return false;
        }
        if (inputSave.get(inputSave.size() - 2) >= 42
                && inputSave.get(inputSave.size() - 2) <= 47
                || inputSave.get(inputSave.size() - 2) == '(') {
            return false;
        }

        for (int i = 0; i < inputSave.size(); i++) {
            if (inputSave.get(i) == ')') {
                if (inputSave.get(i + 1) == '(') {
                    return false;
                }
            }
        }
        return true;
    }

    private void deal_Point() {
        for (int i = 0; i < inputSave.size(); i++) {
            if (inputSave.get(i) == 105) {
                inputSave.remove(i);
                String tempX = Integer.toString(xPoint);
                for (int m = 0; m < tempX.length(); m++) {
                    inputSave.add(i + m, tempX.charAt(m));
                }
            }
            if (inputSave.get(i) == 106) {
                inputSave.remove(i);
                String tempY = Integer.toString(yPoint);
                for (int m = 0; m < tempY.length(); m++) {
                    inputSave.add(i + m, tempY.charAt(m));
                }
            }
        }
    }

    private void letterFliter() {
        for (int i = 0; i < inputSave.size(); i++) {

            if (inputSave.get(i) == 35 || inputSave.get(i) == 40
                    || inputSave.get(i) == 41 || inputSave.get(i) == 43
                    || inputSave.get(i) == 42
                    || (inputSave.get(i) >= 45 && inputSave.get(i) <= 57) || inputSave.get(i) == 105
                    || inputSave.get(i) == 106) {
                continue;
            } else {
                inputSave.remove(i);
                i--;

            }
        }
    }

    private void pushNumberAndOperatorToSStack() {
        double tempNumber = 0;
        int tempPoint = 0;

        Double tempDouble = null;

        ArrayList<Double> tempNumberArrayList = new ArrayList<Double>();
        int i = 0;
        for (; i < inputSave.size(); i++) {
            if (inputSave.get(0) == '+' || inputSave.get(0) == '-'
                    || inputSave.get(0) == '*' || inputSave.get(0) == '/'
                    || inputSave.get(0) == '(' || inputSave.get(0) == ')'
                    || inputSave.get(0) == '#') {
                inputSave.remove(0);
                i = 0;

                if (pushOperators < operatorsSave.size()) {

                    operatorsAndNumberPushOrPop(pushOperators);

                    pushOperators++;
                }

            }


            if (inputSave.get(i) == '.') {
                inputSave.remove(i);
                tempPoint = i;
                for (; ; ) {
                    if (inputSave.get(i) == '.') {
                        inputSave.remove(i);
                    } else {
                        break;
                    }
                }
            }

            if (inputSave.get(i) == '+' || inputSave.get(i) == '-'
                    || inputSave.get(i) == '*' || inputSave.get(i) == '/'

                    || inputSave.get(i) == '(' || inputSave.get(i) == ')'
                    || inputSave.get(i) == '#') {

                for (int j = 0; j < i; j++) {
                    tempDouble = new Double(scanNumber(inputSave.get(j)));
                    tempNumberArrayList.add(tempDouble);
                    tempNumber = tempNumber + tempDouble.intValue()
                            * calculateOrders(i - j);
                }
                if (tempPoint != 0) {
                    tempNumber = tempNumber * calculatePoint(i - tempPoint);
                    tempPoint = 0;
                }
                numberSave.add(new Double(tempNumber));
                number.push(new Double(tempNumber));
                tempNumber = 0;
                if (pushOperators < operatorsSave.size()) {
                    operatorsAndNumberPushOrPop(pushOperators);
                    pushOperators++;
                }

                for (int j = 0; j <= i; j++) {
                    inputSave.remove(0);

                }
                i = 0;
            }
            if (inputSave.size() == 1 && i == 0) {
                operatorsAndNumberPushOrPop(0);
            }
        }
    }

    private void operatorsAndNumberPushOrPop(int pushOperators1) {
        if (operatorsSave.get(pushOperators1).getRightLevel() > operators.get(operators.size() - 1)
                .getLeftLevel()) {
            operators.push(operatorsSave.get(pushOperators1));
        } else if (operatorsSave.get(pushOperators1).getRightLevel() == operators
                .get(operators.size() - 1).getLeftLevel()) {
            operators.pop();
        } else if (operatorsSave.get(pushOperators1).getRightLevel() < operators
                .get(operators.size() - 1).getLeftLevel()) {
            number.push(new Double(calculateNumbers(operators.pop()
                    .getOperator())));
            operatorsAndNumberPushOrPop(pushOperators1);
        }
    }


    private int calculateOrders(int levels) {
        int ordersNumber = 1;
        for (int i = 0; i < levels - 1; i++) {
            ordersNumber = ordersNumber * 10;
        }
        return ordersNumber;
    }

    private double calculatePoint(int position) {
        double tempNum = 1;
        for (int i = 0; i < position; i++) {
            tempNum = tempNum * 0.1;
        }

        return tempNum;
    }

    private double calculateNumbers(char operator) {
        double number1 = number.pop().doubleValue();
        double number2 = number.pop().doubleValue();
        switch (operator) {
            case '+':
                return number1 + number2;
            case '-':
                return number2 - number1;
            case '*':
                return number1 * number2;
            case '/':
                return number2 / number1;
            default:
                return 100;
        }
    }

    private void saveOperatorToArrayList() {

        for (char c : inputSave) {
            switch ((int) c) {
                case '+':
                    operatorsSave.add(new Operators('+', 3, 2));
                    break;
                case '-':
                    operatorsSave.add(new Operators('-', 3, 2));
                    break;
                case '*':
                    operatorsSave.add(new Operators('*', 5, 4));
                    break;
                case '/':
                    operatorsSave.add(new Operators('/', 5, 4));
                    break;
                case '(':
                    operatorsSave.add(new Operators('(', 1, 6));
                    break;
                case ')':
                    operatorsSave.add(new Operators(')', 6, 1));
                    break;
                case '#':
                    operatorsSave.add(new Operators('#', 0, 0));
                    break;
            }

        }
    }

    private int scanNumber(char c) {
        switch ((int) c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                return 10;
        }
    }

    public java.util.Stack<Double> getNumberStack() {
        return number;
    }

    public double getResult() {
        return result;
    }
}
