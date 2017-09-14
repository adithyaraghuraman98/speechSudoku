package edu.cmu.cs.cs214.hw2.expression;

/**
 * Implements the NumberExpression which simply turns a number into an expression.
 */
public class NumberExpression implements Expression {
	private final double val;
	/**
	 * @param d The number that needs to be turned into an expression
	 */
	public NumberExpression(double d) {
		val = d;
	}
	@Override
	public double eval() {
		// TODO Auto-generated method stub
		return val;
	}

	@Override
	public String toString() {
		return Double.toString(val);
	}
}
