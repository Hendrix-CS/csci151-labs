package rpn.model;

import static org.junit.Assert.*;

public enum Op {
	PLUS {
		public int op(int a, int b) {return a + b;}
		public String symbol() {return "+";}
	}, MINUS {
		public int op(int a, int b) {return a - b;}
		public String symbol() {return "-";}
	}, TIMES {
		public int op(int a, int b) {return a * b;}
		public String symbol() {return "*";}
	}, DIV {
		public int op(int a, int b) {return a / b;}
		public String symbol() {return "/";}
		public void fixDivByZero(RPNCalc calc) {fixDivHelp(calc);}
	}, MOD {
		public int op(int a, int b) {return a % b;}
		public String symbol() {return "%";}
		public void fixDivByZero(RPNCalc calc) {fixDivHelp(calc);}
	};
	
	public void test(RPNCalc calc) {
		fixDivByZero(calc);
		int target = op(calc.getNextToLastValue(), calc.getLastValue());
		calc.evaluate(symbol());
		assertEquals(target, calc.getLastValue());
	}
	
	abstract public int op(int a, int b);
	abstract public String symbol();
	public void fixDivByZero(RPNCalc calc) {}
	private static void fixDivHelp(RPNCalc calc) {
		if (calc.getLastValue() == 0) {
			calc.evaluate("1 + ");
		}
	}
}
