package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs228.hw1.Animal.Gender;

public class RiverTest 
{
	private River r;
	private String File;
	
	@Before
	public void startUp() 
	{
		r = new River(8);
	}
	
	@Test
	public void testGetLength() 
	{
		assertEquals(8,r.getLength());
		
		//see if it resets correctly
		r = new River(10);
		assertEquals(10,r.getLength());
	}


	@Test
	public void testNumEmpty() 
	{
		//initially, nothing in r
		assertEquals(8,r.numEmpty());
		
		Animal pepsi = new Bear(3, Gender.FEMALE);
		r.addRandom(pepsi);
		assertEquals(7,r.numEmpty());
		
		Animal sprite = new Fish(2, Gender.MALE);
		Animal fanta = new Bear(3, Gender.FEMALE);
		Animal coke = new Fish(2, Gender.MALE);
		Animal mellowyellow = new Bear(3, Gender.FEMALE);
		
		r.addRandom(sprite);
		r.addRandom(fanta);
		r.addRandom(coke);
		r.addRandom(mellowyellow);
		
		assertEquals(3,r.numEmpty());
	}

	@Test
	public void testAddRandom() 
	{
		Animal a1 = new Bear(0, Gender.MALE);
		assertTrue(r.addRandom(a1));
		
		Animal a2 = new Fish(0, Gender.MALE);
		Animal a3 = new Bear(0, Gender.FEMALE);
		Animal a4 = new Fish(0, Gender.MALE);
		Animal a5 = new Bear(0, Gender.FEMALE);
		Animal a6 = new Fish(0, Gender.MALE);
		Animal a7 = new Bear(0, Gender.FEMALE);
		Animal a8 = new Fish(0, Gender.MALE);
		
		r.addRandom(a2);
		r.addRandom(a3);
		r.addRandom(a4);
		r.addRandom(a5);
		r.addRandom(a6);
		r.addRandom(a7);
		r.addRandom(a8);
		
		assertFalse(r.addRandom(new Fish(0,Gender.MALE)));
	}

}
