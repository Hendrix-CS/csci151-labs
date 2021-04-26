import java.util.Random;

public class ExponentialGenerator {

	private Random rand;
	private double lambda;

	public ExponentialGenerator(double lambda) {
		rand = new Random();
		this.lambda = lambda;
	}

	public double nextDouble() {
		return -Math.log(rand.nextDouble())/lambda;
	}
}
