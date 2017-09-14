package edu.cmu.cs.cs214.hw2.expression;

/**
 * Derivative Expression class that finds an expression approximating to the actual derivative of a given function.
 */
public class DerivativeExpression implements Expression {
    static final double DELTA_X = Math.pow(10,-9);
    private final VariableExpression independentVar;
    private final Expression fn;

    /**
     * Method takes in a function and an independent variable and returns the derivative of the function.
     * @param fn Expression that we intend to find the derivative of
     * @param independentVar The variable with respect to which we are differentiating fn.
     */
    public DerivativeExpression(Expression fn, VariableExpression independentVar) {
        this.fn = fn;
        this.independentVar = independentVar;
    }

    @Override
    public double eval(){
        independentVar.store(independentVar.eval()+DELTA_X);
        double d = fn.eval();
        independentVar.store(independentVar.eval()-DELTA_X);
        return (d - fn.eval())/(DELTA_X);
    }

    @Override
    public String toString() {
        return "("+fn.toString().replace("x","x+"+Double.toString(DELTA_X)) + "-"
                + fn.toString()+")"+"/"+Double.toString(DELTA_X);
    }
}
