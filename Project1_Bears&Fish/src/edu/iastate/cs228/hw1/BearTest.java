package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs228.hw1.Animal.Gender;

public class BearTest 
{
	private Bear b;
	private Bear c;
	
	@Before
	public void startUp() 
	{
		b = new Bear(0, Gender.FEMALE);
		c = new Bear(9, Gender.MALE);
	}
	@Test
	public void testMaxAge() 
	{
		assertFalse(b.maxAge());
		assertTrue(c.maxAge());
	}

	@Test
	public void testIncrAge() 
	{
		assertFalse(c.incrAge());
		assertTrue(b.incrAge());
	}

	@Test
	public void testGetStrength() 
	{
		assertEquals(1,b.getStrength());
	}

	@Test
	public void testToString() 
	{
		assertEquals("BF0",b.toString());
	}
}
