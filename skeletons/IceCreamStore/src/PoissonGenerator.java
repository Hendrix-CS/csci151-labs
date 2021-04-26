import java.util.Random;

/**
 * @author Brent Yorgey
 *
 */
public class PoissonGenerator {

	private Random rand;
	private double lambda;

    public PoissonGenerator(double lambda) {
        rand = new Random();
        this.lambda = lambda;
    }

    /**
     * Overload Random's nextInt() method to return an int from a Poisson
     * distribution.
     *
     * @see java.util.Random#nextInt()
     */
    public int nextInt() {

        double elambda = Math.exp(-1 * lambda);
        double product = 1;
        int count = -1;
        do {
            product *= rand.nextDouble();
            count++;
        } while (product >= elambda);

        return count;
    }
}