package edu.cmu.cs.cs214.hw2.termcalc;

import edu.cmu.cs.cs214.hw2.expression.BinaryExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumberExpression;
import edu.cmu.cs.cs214.hw2.expression.UnaryExpression;
import edu.cmu.cs.cs214.hw2.operator.AbsoluteValue;
import edu.cmu.cs.cs214.hw2.operator.Addition;
import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;
import edu.cmu.cs.cs214.hw2.operator.Division;
import edu.cmu.cs.cs214.hw2.operator.Exponentiation;
import edu.cmu.cs.cs214.hw2.operator.Multiplication;
import edu.cmu.cs.cs214.hw2.operator.Negation;
import edu.cmu.cs.cs214.hw2.operator.Subtraction;
import edu.cmu.cs.cs214.hw2.operator.UnaryOperator;

/**
 * Implementation of the ExpressionMaker interface that allows the terminal
 * calculator to parse the input expression and use the appropriate operator
 * implementations.
 */
public class MyExpressionMaker implements ExpressionMaker {

	@Override
	public Expression sumExpression(Expression addend1, Expression addend2) {
		BinaryOperator add = new Addition();
		BinaryExpression out = new BinaryExpression(addend1,addend2,add);
		return out;
	}

	@Override
	public Expression differenceExpression(Expression op1, Expression op2) {
        BinaryOperator subtract = new Subtraction();
        BinaryExpression out = new BinaryExpression(op1,op2,subtract);
        return out;
	}

	@Override
	public Expression productExpression(Expression factor1, Expression factor2) {
        BinaryOperator multiply = new Multiplication();
        BinaryExpression out = new BinaryExpression(factor1,factor2,multiply);
        return out;
	}

	@Override
	public Expression divisionExpression(Expression dividend, Expression divisor) {
        BinaryOperator divide = new Division();
        BinaryExpression out = new BinaryExpression(dividend, divisor, divide);
        return out;
	}

	@Override
	public Expression exponentiationExpression(Expression base, Expression exponent) {
        BinaryOperator raise = new Exponentiation();
        BinaryExpression out = new BinaryExpression(base,exponent,raise);
        return out;
	}

	@Override
	public Expression negationExpression(Expression operand) {
		UnaryOperator negate = new Negation();
        UnaryExpression out = new UnaryExpression(operand,negate);
		return out;
	}

	@Override
	public Expression absoluteValueExpression(Expression value) {
        UnaryOperator abs = new AbsoluteValue();
        UnaryExpression out = new UnaryExpression(value,abs);
        return out;
	}

	@Override
	public Expression numberExpression(double value) {
		NumberExpression out = new NumberExpression(value);
		return out;
	}

}
