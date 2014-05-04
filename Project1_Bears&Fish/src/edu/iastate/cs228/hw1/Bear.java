package edu.iastate.cs228.hw1;

import java.util.Random;

/**
 * 
 * @author Camryn Williams
 * 
 */
public class Bear extends Animal 
{
	/**
	 * The strength of the Bear.
	 */
	public int strength;

	/**
	 * Creates a Bear of a random age and gender.
	 */
	public Bear() 
	{
		super();
		age = RandomSingleton.getInstance().nextInt(10);
	}

	/**
	 * Creates a Bear of given age and gender
	 * 
	 * @param age
	 *            The age of the animal to be created.
	 * @param gender
	 *            The gender of the Bear to be created.
	 */
	public Bear(int age, Gender gender) 
	{
		super(age,gender);
	}

	/**
	 * Returns true if the current age of the animal has reached the limit for
	 * the species; otherwise, it return false.
	 * 
	 * @return true if age limit has been reached, false otherwise.
	 */
	public boolean maxAge() 
	{
		return age >= BEAR_MAX_AGE;
	}

	/**
	 * If the current age of the animal is less than the maximum for the
	 * species, increment the age of the animal by one and return true.
	 * Otherwise, it leaves the age as is and return false.
	 * 
	 * @return true if the age has been incremented, false otherwise.
	 */
	public boolean incrAge() 
	{
		if(age < BEAR_MAX_AGE)
		{
			age++;
			return true;
		}
		
		return false;
	}

	/**
	 * Returns the current strength of the Bear.
	 * 
	 * @return The current strength.
	 */
	public int getStrength()
	{
		int[] oldness = {0,1,2,3,4,5,6,7,8,9};
		int[] power = {1,2,3,4,5,4,3,2,1,0};
		
		for(int i = 0; i < oldness.length; i++)
		{
			if(oldness[i] == age)
			{
				strength = power[i];
			}
		}
		
		return strength;
	}

	/**
	 * Produces a string representation of this Bear object as described in the
	 * Project Description. e.g. BF7 would be returned for a 7-year old female
	 * bear.
	 * 
	 * @return The string representation of this Bear.
	 */
	@Override
	public String toString() 
	{
		String g;
		if(gender == Gender.FEMALE)
		{
			g = "F";
		}
		
		else
		{
			g = "M";
		}
		
		return "B" + g + age;
	}
}