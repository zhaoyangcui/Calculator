package util;

public class Operators {
    private char operator;
    private int leftLevel;
    private int rightLevel;

    public Operators() {

    }

    public Operators(char operator, int leftLevel, int rightLevel) {
        this.operator = operator;
        this.leftLevel = leftLevel;
        this.rightLevel = rightLevel;
    }

    public char getOperator() {
        return operator;
    }

    public int getLeftLevel() {
        return leftLevel;
    }

    public int getRightLevel() {
        return rightLevel;
    }

    @Override
    public String toString() {
        String operators = null;
        operators = this.getOperator() + "";
        return operators;
    }
}
