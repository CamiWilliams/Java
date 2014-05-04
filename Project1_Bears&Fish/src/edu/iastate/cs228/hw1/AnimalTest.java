package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs228.hw1.Animal.Gender;

public class AnimalTest 
{
	private Animal pepsi;
	private Animal sprite;
	
	@Before
	public void startUp()
	{
		pepsi = new Bear(3, Gender.FEMALE);
		sprite = new Fish(2, Gender.MALE);
	}
	
	@Test
	public void testGetAge() 
	{
		assertEquals(3, pepsi.getAge());
		assertEquals(2, sprite.getAge());
	}

}
