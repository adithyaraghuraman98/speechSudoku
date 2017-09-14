package edu.cmu.cs.cs214.hw2.zerofinder;

import edu.cmu.cs.cs214.hw2.expression.BinaryExpression;
import edu.cmu.cs.cs214.hw2.expression.DerivativeExpression;
import edu.cmu.cs.cs214.hw2.expression.NumberExpression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;
import edu.cmu.cs.cs214.hw2.operator.Multiplication;
import edu.cmu.cs.cs214.hw2.operator.Subtraction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZeroFinderTest {
    VariableExpression variableExpression;
    BinaryExpression fn;
    double zero;
    VariableExpression variableExpression1;
    BinaryExpression fn1;
    double zero1;

    @Before
    public void setUp(){
        variableExpression = new VariableExpression("x");
        BinaryExpression binaryExpression = new BinaryExpression(variableExpression, new NumberExpression(2),
                new Subtraction());
        fn = new BinaryExpression(variableExpression,binaryExpression,new Multiplication());
        zero = new ZeroFinder().zero(fn,variableExpression,1.1,0.00005);

        variableExpression1 = new VariableExpression("x");
        fn1 = new BinaryExpression(variableExpression1, new BinaryExpression(variableExpression1,
                new NumberExpression(3),new Subtraction()), new Multiplication());
        zero1 = new ZeroFinder().zero(fn1, variableExpression1, 1,0.00005);
    }

    @Test
    public void testAll(){
        assertEquals(2,zero,0.00005);
        assertEquals(0,zero1,0.00005);
    }

}