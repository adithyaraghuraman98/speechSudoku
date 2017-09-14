package edu.cmu.cs.cs214.hw2.operator;

/**
 * Implementation of the Multiplication operator
 */
public class Multiplication implements BinaryOperator{
	@Override
	public double apply(double arg1,double arg2) {
		return arg1*arg2;
	}

	@Override
	public String toString() {
		return "*";
	}
}
