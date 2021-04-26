package numstack.model;

public class ListIntStackTest extends IntStackTest {

	@Override
	public IntStack create() {
		return new ListIntStack();
	}

}
