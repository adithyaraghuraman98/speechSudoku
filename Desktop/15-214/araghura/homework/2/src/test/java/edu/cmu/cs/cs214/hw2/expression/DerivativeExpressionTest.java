package edu.cmu.cs.cs214.hw2.expression;

import edu.cmu.cs.cs214.hw2.operator.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DerivativeExpressionTest {
    VariableExpression variableExpression;
    BinaryExpression fn;
    UnaryExpression function;
    DerivativeExpression out;

    @Before
    public void setUp(){
        variableExpression = new VariableExpression("x");
        variableExpression.store(8);
        BinaryExpression binaryExpression = new BinaryExpression(variableExpression, new NumberExpression(2),
                new Subtraction());
        fn = new BinaryExpression(variableExpression,binaryExpression,new Multiplication());
        function = new UnaryExpression(fn, new Negation());
        out = new DerivativeExpression(function, variableExpression);
    }

    @Test
    public void testAll(){
        assertEquals(-14,out.eval(),.005);
        variableExpression.store(-3);
        assertEquals(8,out.eval(),.005);
        assertEquals("x", variableExpression.toString());
        assertEquals("x", variableExpression.name());
        assertEquals("(Neg((x+1.0E-9)*((x+1.0E-9)-(2.0)))-Neg((x)*((x)-(2.0))))/1.0E-9", out.toString());
        assertEquals("(x)*((x)-(2.0))",fn.toString());
        assertEquals("Neg((x)*((x)-(2.0)))",function.toString());
    }

}