package edu.cmu.cs.cs214.hw2.operator;

/**
 * Implements the exponentiation operator
 */
public class Exponentiation implements BinaryOperator {
	@Override
	public double apply(double arg1, double arg2) {
		return Math.pow(arg1, arg2);
	}

	@Override
	public String toString() {
		return "^";
	}
}
