package numstack.timetest;

import numstack.model.ArrayIntStack;
import numstack.model.IntStack;
import numstack.model.ListIntStack;

public class TimeTest {
	public static void main(String[] args) {
		int size = args.length > 0 ? Integer.parseInt(args[0]) : 1000000;
		
		long start = System.currentTimeMillis();
		test(new ArrayIntStack(), size);
		long durationArray = System.currentTimeMillis() - start;
		
		start = System.currentTimeMillis();
		test(new ListIntStack(), size);
		long durationList = System.currentTimeMillis() - start;
		
		System.out.println("Array: " + durationArray + "ms");
		System.out.println("List:  " + durationList + "ms");
	}
	
	public static void test(IntStack stack, int size) {
		for (int i = 0; i < size; i++) {
			stack.push(i);
		}
	}
}
