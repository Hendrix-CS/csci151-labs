package engine;

import specs.Payoffs;
import specs.Score;

public class BasicPayoffs implements Payoffs {
	@Override
	public Score score(boolean askForAll1, boolean askForAll2) {
		if (askForAll1 && askForAll2) {
			return new Score(40, 40);
		} else if (askForAll1) {
			return new Score(65, 15);
		} else if (askForAll2) {
			return new Score(15, 65);
		} else {
			return new Score(50, 50);
		}
	}
}
