package edu.cmu.cs.cs214.hw2.operator;

/**
 * Implementation of the division operator
 */
public class Division implements BinaryOperator {
	@Override
	public double apply(double arg1, double arg2) {
		return arg1/arg2;
	}

	@Override
	public String toString() {
		return "/";
	}

}
