package edu.cmu.cs.cs214.hw2.guicalc;
import edu.cmu.cs.cs214.hw2.operator.AbsoluteValue;
import edu.cmu.cs.cs214.hw2.operator.Addition;
import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;
import edu.cmu.cs.cs214.hw2.operator.Division;
import edu.cmu.cs.cs214.hw2.operator.Exponentiation;
import edu.cmu.cs.cs214.hw2.operator.Multiplication;
import edu.cmu.cs.cs214.hw2.operator.Negation;
import edu.cmu.cs.cs214.hw2.operator.Subtraction;
import edu.cmu.cs.cs214.hw2.operator.UnaryOperator;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Main program that runs the GUI Calculator
 */
public class Main {
	/**
	 * The main method to run the GUI Calculator
	 * @param args : do not take in any arguments from the command line
	 */
    public static void main(String[] args) {
        
        Set<UnaryOperator> unaryOperators = new LinkedHashSet<UnaryOperator>();
        UnaryOperator neg = new Negation();
        UnaryOperator abs = new AbsoluteValue();
        unaryOperators.add(neg);
        unaryOperators.add(abs);
        
        
        Set<BinaryOperator> binaryOperators = new LinkedHashSet<BinaryOperator>();
        BinaryOperator add = new Addition();
        BinaryOperator sub = new Subtraction();
        BinaryOperator mult = new Multiplication();
        BinaryOperator div = new Division();
        BinaryOperator exp = new Exponentiation();
        binaryOperators.add(add);
        binaryOperators.add(sub);
        binaryOperators.add(mult);
        binaryOperators.add(div);
        binaryOperators.add(exp);
        
        // Run the calculator!
        new GuiCalculator(unaryOperators, binaryOperators);
    }
}
