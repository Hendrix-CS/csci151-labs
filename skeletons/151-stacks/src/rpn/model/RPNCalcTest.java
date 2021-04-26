package rpn.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class RPNCalcTest {

	RPNCalc calc = new RPNCalc();
	
	@Test
	public void basicTest() {
		calc.evaluate("2 5 *");
		assertEquals(10, calc.getLastValue());
		calc.evaluate("10 +");
		assertEquals(20, calc.getLastValue());
		calc.evaluate("5 -");
		assertEquals(15, calc.getLastValue());
		calc.evaluate("3 /");
		assertEquals(5, calc.getLastValue());
		calc.evaluate("2 %");
		assertEquals(1, calc.getLastValue());
		
		assertEquals(1, calc.numValuesLeft());
	}
	
	@Test
	public void testValuesLeft() {
		calc.evaluate("1 2 3 4");
		assertEquals(4, calc.numValuesLeft());
		calc.evaluate("+");
		assertEquals(3, calc.numValuesLeft());
		calc.evaluate("+");
		assertEquals(2, calc.numValuesLeft());
		calc.evaluate("+");
		assertEquals(1, calc.numValuesLeft());
	}
	
	@Test
	public void testPeek() {
		calc.evaluate("1 2");
		assertEquals(1, calc.getNextToLastValue());
		assertEquals(2, calc.getLastValue());
		calc.evaluate("+");
		assertEquals(3, calc.getLastValue());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testError() {
		calc.evaluate("1 2 3 &");
	}
	
	@Test(expected=IllegalStateException.class) 
	public void underflowError() {
		calc.evaluate("1 +");
	}
	
	public final static int NUM_TESTS = 100000;
	public final static int MAX_VAL = Integer.MAX_VALUE / 100;
	
	@Test
	public void bigRandomTest() {
		Random r = new Random();
		for (int i = 0; i < NUM_TESTS; i++) {
			calc.addTerm(Integer.toString(r.nextInt(MAX_VAL) - MAX_VAL / 2));
		}
		
		while (calc.numValuesLeft() > 1) {
			Op.values()[r.nextInt(Op.values().length)].test(calc);
		}
	}
}
