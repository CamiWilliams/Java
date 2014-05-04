package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs228.hw1.Animal.Gender;

/**
 * 
 * @author Camryn Williams
 *
 */
public class AnimalBearFishRiverTest 
{

	private River r;
	private Fish a;
	private Bear b;
	private Bear c;
	private Fish d;
	
	@Before
	public void startUp() 
	{
		r = new River(8);
		a = new Fish(0, Gender.FEMALE);
		b = new Bear(0, Gender.FEMALE);
		c = new Bear(9, Gender.MALE);
		d = new Fish(4, Gender.MALE);
	}
	
	//Animal
	@Test
	public void testGetAge() 
	{
		assertEquals(0, a.getAge());
		assertEquals(0, b.getAge());
		assertEquals(9, c.getAge());
		assertEquals(4, d.getAge());
	}
	
	//Fish and Bear
	@Test
	public void testMaxAge() 
	{
		assertFalse(b.maxAge());
		assertTrue(c.maxAge());
		assertFalse(a.maxAge());
		assertTrue(d.maxAge());
	}

	@Test
	public void testIncrAge() 
	{
		assertFalse(c.incrAge());
		assertTrue(b.incrAge());
		assertFalse(d.incrAge());
		assertTrue(a.incrAge());
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
		assertEquals("FF0",a.toString());
	}
	
	//River
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
		
		//5 Animals added
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
		
		//Cannot add because full
		assertFalse(r.addRandom(new Fish(0,Gender.MALE)));
	}

}
