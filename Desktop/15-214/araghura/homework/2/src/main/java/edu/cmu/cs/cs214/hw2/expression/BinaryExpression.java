package edu.cmu.cs.cs214.hw2.expression;

import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;

/**
 * Implementation of a binary expression. Here a binary expression consists of two Expressions that are
 * going to be combined in some form to give an output.
 */
public class BinaryExpression implements Expression {
    private final Expression exp1;
    private final Expression exp2;
    private final BinaryOperator op;

    /**
     * This constructor initializes a binary expression. The expression is a combination of two expressions
     * @param exp1 first expression
     * @param exp2 second expression
     * @param op operation combining the two expressions
     */
    public BinaryExpression(Expression exp1, Expression exp2, BinaryOperator op){
    this.exp1 = exp1;
    this.exp2 = exp2;
    this.op = op;
    }

    @Override
    public double eval() {
        return op.apply(exp1.eval(),exp2.eval());
    }

    @Override
    public String toString() {
        return "("+ exp1.toString()+ ")" + op.toString()+ "("+ exp2.toString()+")";
    }
}
