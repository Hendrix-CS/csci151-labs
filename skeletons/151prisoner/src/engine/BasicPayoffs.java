package engine;

import specs.Payoffs;
import specs.Score;

public class BasicPayoffs implements Payoffs {
	@Override
	public Score score(boolean defect1, boolean defect2) {
		if (defect1 && defect2) {
			return new Score(-5, -5);
		} else if (defect1 && !defect2) {
			return new Score(15, -10);
		} else if (!defect1 && defect2) {
			return new Score(-10, 15);
		} else {
			return new Score(10, 10);
		}
	}
}
