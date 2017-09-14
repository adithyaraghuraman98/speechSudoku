package edu.cmu.cs.cs214.hw2.termcalc;

import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumberExpression;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyExpressionMakerTest {
    private Expression n1;
    private Expression n2;
    private Expression sum;
    private Expression sub;
    private Expression prod;
    private Expression div;
    private Expression expo;
    private Expression abs;
    private Expression neg;


    @Before
    public void setUp(){
        ExpressionMaker myExp = new MyExpressionMaker();
        n1 = myExp.numberExpression(5);
        n2 = myExp.numberExpression(8);
        sum = myExp.sumExpression(n1,n2);
        sub = myExp.differenceExpression(n1,n2);
        prod = myExp.productExpression(n1,n2);
        div = myExp.divisionExpression(n1,n2);
        expo = myExp.exponentiationExpression(n1,n2);
        abs = myExp.absoluteValueExpression(n1);
        neg = myExp.negationExpression(n2);


    }
    @Test
    public void testAll(){
        assertEquals(13,sum.eval(),0);
        assertEquals(-3,sub.eval(),0);
        assertEquals(40,prod.eval(),0);
        assertEquals(5.0/8,div.eval(),0);
        assertEquals(Math.pow(5,8),expo.eval(),0);
        assertEquals(5,abs.eval(),0);
        assertEquals(-8,neg.eval(),0);

    }

}