package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs228.hw1.Animal.Gender;

public class FishTest {

	private Fish b;
	private Fish c;
	
	@Before
	public void startUp() 
	{
		b = new Fish(0, Gender.FEMALE);
		c = new Fish(4, Gender.MALE);
	}
	@Test
	public void testMaxAge() 
	{
		assertFalse(a.maxAge());
		assertTrue(d.maxAge());
	}

	@Test
	public void testIncrAge() 
	{
		assertFalse(d.incrAge());
		assertTrue(a.incrAge());
	}

	@Test
	public void testToString() 
	{
		assertEquals("FF0",b.toString());
	}

}
