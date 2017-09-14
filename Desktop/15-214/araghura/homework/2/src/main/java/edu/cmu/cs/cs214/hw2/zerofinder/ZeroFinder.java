package edu.cmu.cs.cs214.hw2.zerofinder;

import edu.cmu.cs.cs214.hw2.expression.DerivativeExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;

/**
 * Finds zeros of arbitrary functions using Newton's method.
 */
public class ZeroFinder {
    /**
     * This method takes in a function, variable, initial estimate and a tolerance value in order to estimate the
     * the locations of the roots of the function.
     * @param fn The Expression we want to analyze.
     * @param x The independent variable in fn.
     * @param approxZero Initial estimate of a zero.
     * @param tolerance Acceptable tolerance in the value of the function at the zeroes.
     * @return The approximate position of the zero of fn.
     */
    public static double zero(Expression fn, VariableExpression x, double approxZero, double tolerance){
        x.store(approxZero);

        while(Math.abs(fn.eval())>tolerance){
            DerivativeExpression fPrime = new DerivativeExpression(fn,x);
            double newX = x.eval() - fn.eval()/fPrime.eval();
            x.store(newX);
        }
        return x.eval();
    }
}
