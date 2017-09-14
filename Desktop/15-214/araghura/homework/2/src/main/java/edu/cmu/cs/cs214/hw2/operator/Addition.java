package edu.cmu.cs.cs214.hw2.operator;

/**
 * Implements the Addition operator
 */
public class Addition implements BinaryOperator{
	@Override
	public double apply(double arg1, double arg2) {
		return arg1 + arg2;
	}

	@Override
	public String toString() {
		return "+";
	}
}
