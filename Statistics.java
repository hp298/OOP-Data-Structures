/** An instance contains the probabilities that a person gets a virus<br>
 * and a person becomes immune in a time step.
 *
 * @author Mshnik, revised by Gries. */
public class Statistics {
	/** Probability of contagion. In range [0, 1]. */
	private double contagionChance;

	/** Probability of immunization. In range [0, 1]. */
	private double immunizationChance;

	/** Constructor: an instance with contagion probability cp and <br>
	 * immunization probability ip. <br>
	 * Precondition: 0 <= cp, ip <= 1. */
	public Statistics(double cp, double ip) {
		contagionChance= cp;
		immunizationChance= ip;
	}

	/** = (new random number) < (the probability of contagion). */
	public boolean virusSpreadsToPerson() {
		return Math.random() < contagionChance;
	}

	/** = (new random number) < (the probability of becoming immune). */
	public boolean personBecomesImmune() {
		return Math.random() < immunizationChance;
	}
}
