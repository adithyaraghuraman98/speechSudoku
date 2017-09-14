package edu.cmu.cs.cs214.hw2.operator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperatorTest {
    private double a = 2.0;
    private double b = 3.0;
    private double zero = 0;

    @Test
    public void testAll(){
        assertEquals(5.0, new Addition().apply(a,b),0);
        assertEquals(-1.0, new Subtraction().apply(a,b),0);
        assertEquals(6.0, new Multiplication().apply(a,b),0);
        assertEquals(2.0/3.0, new Division().apply(a,b), 0);
        assertEquals(1, new Division().apply(b,b), 0);
        assertEquals(Double.POSITIVE_INFINITY, new Division().apply(b,zero),0);
        assertEquals(Double.NaN,new Division().apply(zero,zero),0);
        assertEquals(9.0, new Exponentiation().apply(b,a),0);
        assertEquals(-2.0, new Negation().apply(a),0);
        assertEquals(3.0, new AbsoluteValue().apply(new Negation().apply(b)),0);
        assertEquals("+",new Addition().toString());
        assertEquals("-", new Subtraction().toString());
        assertEquals("*", new Multiplication().toString());
        assertEquals("/", new Division().toString());
        assertEquals("^", new Exponentiation().toString());
        assertEquals("Neg", new Negation().toString());
        assertEquals("abs", new AbsoluteValue().toString());
    }
}