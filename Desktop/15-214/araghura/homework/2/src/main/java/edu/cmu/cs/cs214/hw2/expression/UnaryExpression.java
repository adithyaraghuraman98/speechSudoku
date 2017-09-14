package edu.cmu.cs.cs214.hw2.expression;

import edu.cmu.cs.cs214.hw2.operator.Operator;
import edu.cmu.cs.cs214.hw2.operator.UnaryOperator;

/**
 * Implements a Unary Expression which consists of an expression and an operator
 * applied to it.
 */
public class UnaryExpression implements Expression {
    private final Expression exp;
    private final UnaryOperator op;

    /**
     * Constructor of a Unary expression that takes in an expression and the
     * operator that needs to be applied to that expression
     * @param exp Input expression
     * @param op The operation that needs to be applied to the expression
     */
    public UnaryExpression(Expression exp, UnaryOperator op){
        this.exp = exp;
        this.op = op;
    }
    @Override
    public double eval() {
        return op.apply(exp.eval());
    }

    @Override
    public String toString(){
        return op.toString()+"("+exp.toString()+")";
    }

}
