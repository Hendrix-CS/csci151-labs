package numstack.model;

public class ArrayIntStackTest extends IntStackTest {

	@Override
	public IntStack create() {
		return new ArrayIntStack();
	}

}
