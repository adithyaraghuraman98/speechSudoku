package edu.cmu.cs.cs214.hw2.operator;

/**
 * Implementation of negation operation
 */
public class Negation implements UnaryOperator {

	@Override
	public double apply(double arg) {
		return arg*(-1);
	}

	@Override
	public String toString() {
		return "Neg";
	}
}
