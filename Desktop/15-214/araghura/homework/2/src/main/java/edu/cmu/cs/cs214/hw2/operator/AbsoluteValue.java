package edu.cmu.cs.cs214.hw2.operator;

/**
 * Implements the AbsoluteValue operator
 */
public class AbsoluteValue implements UnaryOperator {
	@Override
	public double apply(double arg) {
		return Math.abs(arg);
	}

	@Override
	public String toString() {
		return "abs";
	}
}
