package edu.iastate.cs228.hw2;

import java.util.Random;

/**
 * 
 * Random singleton class. There is no need to create a new class of this. To
 * get the random object from this class just call:
 * 
 * <code><pre>
 * RandomSingleton.getInstance()
 * </pre></code>
 * 
 * Example usage:
 * 
 * <code><pre>
 * int randNumber = RandomSingleton.getInstance().nextInt(10);
 * </pre></code>
 * 
 * If calling from this object multiple times, it is fine to create a variable
 * referencing it:
 * 
 * <code><pre>
 * Random randObj = RandomSingleton.getInstance();
 * int randNumber = randObj.nextInt(10);
 * // Do something...
 * int anotherRandNumber = randObj.nextInt(2);
 * // DO something else...
 * </pre><code>
 * 
 * @author COMS228 TAs
 */
public class RandomSingleton {

	/**
	 * Random object to be used by the class
	 */
	private static Random gen = new Random();

	/**
	 * Gets the Random object
	 * 
	 * @return A Random object, if there is no object, it is created.
	 */
	public static Random getInstance() {
		synchronized (gen) {
			// Don't know what this is? Google: "Java Ternary Operator"
			gen = (gen == null) ? new Random() : gen;
			return gen;
		}
	}

	/**
	 * Sets the seed on the Random object. This is useful for JUnit testing to
	 * provide reliable, same results throughout tests.
	 * 
	 * @param seed
	 *            Seed to set in Random object
	 */
	public static void setSeed(long seed) {
		synchronized (gen) {
			// Lets start fresh!
			gen = new Random(seed);
		}
	}
}
