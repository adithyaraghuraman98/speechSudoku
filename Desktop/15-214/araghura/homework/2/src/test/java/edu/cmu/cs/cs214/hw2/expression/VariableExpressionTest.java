package edu.cmu.cs.cs214.hw2.expression;

import com.sun.nio.sctp.SctpSocketOption;
import edu.cmu.cs.cs214.hw2.operator.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VariableExpressionTest {
    VariableExpression variableExpression;
    BinaryExpression out;

    @Before
    public void setUp(){
        variableExpression = new VariableExpression("x");
        variableExpression.store(5);
        BinaryExpression binaryExpression = new BinaryExpression(variableExpression, new NumberExpression(2),
                new Subtraction());
        out = new BinaryExpression(variableExpression,binaryExpression,new Multiplication());
    }

    @Test
    public void testAll(){
        assertEquals(15,out.eval(),0);
    }
}