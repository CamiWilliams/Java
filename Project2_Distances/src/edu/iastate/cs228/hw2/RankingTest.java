package edu.iastate.cs228.hw2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *Submit a file containing JUnit test classes containing multiple test cases for all the methods
 *in the Ranking class. Your code must
	(a) test that methods through exceptions under the appropriate circumstances (e.g., null
	arguments),
	(b) verify that all three constructors produce valid rankings (i.e., permutations of 1 through n)
	(c) test boundary cases —including proper handling of single-element rankings, inversion
	counting on ascending and descending sequences, determining distances between identical 
	rankings and rankings that are reversals of each other, etc.,
	(d) perform any additional tests that you deem necessary.
 * 
 * @author Camryn Williams
 *
 */

public class RankingTest 
{
	private Ranking r_int;
	private Ranking r_int_array;
	private Ranking r_float_array;
	private Ranking r_exception_lessthanone;
	private Ranking r_exception_intnull;
	private Ranking r_exception_floatnull;
	private Ranking r_exception_duplicate;
	private Ranking single_element;
	private Ranking ascending; //identical to r_int_array
	private Ranking descending;
	private int n;

	
	@Before
	public void setUp()
	{
		float[] f = {0.75f,0.36f,0.65f,-1.5f,0.85f}; //Example 6
		float[] a = {0.01f,0.05f,0.01f,0.05f,0.2f};//duplicates
		float[] n2 = null;
		
		int[] i = {1,2,3,4,5,6};
		int[] j = {6,5,4,3,2,1};
		int[] n1 = null;
		int[] s = {2};
		
		n = 6;
		
		//Ranking(int n)
		r_int = new Ranking(n); //Constructs a random ranking of the numbers 1 through n. Must run in O(n log n) time.
		r_exception_lessthanone = new Ranking(-1); //Throws an IllegalArgumentException if n < 1
	
		//Ranking(int[] rank)
		r_int_array = new Ranking(i); //Constructs a ranking o of the set U = {1,...,rank.length}, 
									  //where o(i) = rank[i-1]; Runs in O(nlogn) time, where n = rank.length
		r_exception_intnull = new Ranking(n1); //Throws a NullPointerException if rank is null.
		
		//Ranking(float[] scores)
		r_float_array = new Ranking(f); //Constructs a ranking of the set U = {1,...,scores.length, where element
										//i gets rank k if scores[i-1] is the kth largest element in array scores.
										//Must run in O(nlogn), where is n = scores.length
		r_exception_floatnull = new Ranking(n2); //Throws a NullPointerException if scores is null
		r_exception_duplicate = new Ranking(a); //Throws an IllegalArgumentException if scores contains duplicate values
	
		single_element = new Ranking(s);
		ascending = new Ranking(i);
		descending = new Ranking(j);
	}
	
	@Test
	public void isValidConstructor()
	{
		//int j = 1;
		int count = 0;
		
		for(int i = 1; i <= n; i++)
		{
			int j = 1;
			while(j <= n)
			{
				if(i == r_int.getRank(j))
				{
					j++;
					count++;
					break;
				}
			
				else
				{
					j++;
				}
			}
		}
		
		assertEquals(n,count);
	}
	
	/**
	 * public int getNumItems()
	 */
	@Test
	public void getNumItemsTest()
	{
		assertEquals(6,r_int.getNumItems());
		assertEquals(6,r_int_array.getNumItems());
		assertEquals(5,r_float_array.getNumItems());
		assertEquals(1,single_element.getNumItems());
	}
	
	@Test 
	(expected=IllegalArgumentException.class) public void getNumItemsIllegalTest()
	{
		//Should throw IllegalArgumentException
		r_exception_lessthanone.getNumItems();
		r_exception_duplicate.getNumItems();
	}
	
	@Test 
	(expected=NullPointerException.class) public void getNumItemsNullTest()
	{
		//Should throw NullPointerException
		r_exception_intnull.getNumItems();
		r_exception_floatnull.getNumItems();
	}
	
	/**
	 * public int getRank(int i)
	 */
	@Test
	public void getRankTest()
	{
		assertEquals(6,r_int_array.getRank(1)); //{1,2,3,4,5,6} is ranking and num-set, so o[1] = 1 **Not index based
		assertEquals(4,r_float_array.getRank(2)); // o = (2,4,3,5,1) so the o[2] = 4
		assertEquals(1,single_element.getRank(1));
		assertEquals(1,ascending.getRank(6)); //1,2,3,4,5,6
	}
	
	@Test 
	(expected=IllegalArgumentException.class) public void getRankIllegalTest()
	{
		//Should throw IllegalArgumentException
		r_int_array.getRank(7); //DNE
		r_exception_lessthanone.getRank(1);
		r_exception_duplicate.getRank(1);
	}
	
	@Test 
	(expected=NullPointerException.class) public void getRankNullTest()
	{
		//Should throw NullPointerException
		r_exception_intnull.getRank(1);
		r_exception_floatnull.getRank(1);
	}
	
	/** 
	 * public static int footrule(Ranking r1, Ranking r2)
	 */
	@Test
	public void footruleTest()
	{	
		int[] one = {4,3,1,5,2}; //Table 1
		int[] two = {5,4,1,3,2};
		
		Ranking f1 = new Ranking(one);
		Ranking f2 = new Ranking(two);
		
		assertEquals(4, Ranking.footrule(f1,f2));
		assertEquals(18, Ranking.footrule(ascending, descending));// 1,2,3,4,5,6 vs 6,5,4,3,2,1
		assertEquals(0, Ranking.footrule(ascending, r_int_array));
	}
	
	@Test 
	(expected=IllegalArgumentException.class) public void footruleIllegalTest()
	{
		//Should throw IllegalArgumentException
		Ranking.footrule(r_int,r_float_array); //different lengths
		Ranking.footrule(descending,single_element);
	}
	
	@Test 
	(expected=NullPointerException.class) public void footruleNullTest()
	{
		//Should throw NullPointerException
		Ranking.footrule(r_int,r_exception_floatnull);
	}
	
	/**
	 * public static int kemeny(Ranking r1, Ranking r2)
	 */
	@Test
	public void kemenyTest()
	{
		int[] one = {4,3,1,5,2}; //Table 1
		int[] two = {5,4,1,3,2};
		
		Ranking k1 = new Ranking(one);
		Ranking k2 = new Ranking(two);
		
		assertEquals(2, Ranking.kemeny(k1,k2));
		assertEquals(15, Ranking.kemeny(ascending, descending));// 1,2,3,4,5,6 vs 6,5,4,3,2,1
		assertEquals(0, Ranking.kemeny(ascending, r_int_array));
	
	}
	
	@Test 
	(expected=IllegalArgumentException.class) public void kenemyIllegalTest()
	{
		//Should throw IllegalArgumentException
		Ranking.kemeny(r_int,r_float_array);
		Ranking.kemeny(descending,single_element);
	}
	
	@Test 
	(expected=NullPointerException.class) public void kenemyNullTest()
	{
		//Should throw NullPointerException
		Ranking.kemeny(r_int,r_exception_floatnull);
	}
	
	/**
	 * public int fDist(Ranking other)
	 */
	@Test
	public void fDistTest()
	{
		int[] one = {4,3,1,5,2}; //Table 1
		int[] two = {5,4,1,3,2};
		
		Ranking f1 = new Ranking(one);
		Ranking f2 = new Ranking(two);
		
		assertEquals(4, f1.kDist(f2));
		assertEquals(18, ascending.fDist(descending));// 1,2,3,4,5,6 vs 6,5,4,3,2,1
		assertEquals(0, ascending.fDist(r_int_array));
	
	}
	
	@Test 
	(expected=IllegalArgumentException.class) public void fDistIllegalTest()
	{
		//Should throw IllegalArgumentException
		r_int.fDist(r_float_array);
		descending.fDist(single_element);
	}
	
	@Test 
	(expected=NullPointerException.class) public void fDistNullTest()
	{
		//Should throw NullPointerException
		r_int.fDist(r_exception_floatnull);
		r_int.fDist(r_exception_intnull);
	}
	
	/**
	 * public int kDist(Ranking other)
	 */
	@Test
	public void kDistTest()
	{
		int[] one = {4,3,1,5,2}; //Table 1
		int[] two = {5,4,1,3,2};
		
		Ranking k1 = new Ranking(one);
		Ranking k2 = new Ranking(two);
		
		assertEquals(2, k1.kDist(k2));
		assertEquals(15, ascending.kDist(descending));// 1,2,3,4,5,6 vs 6,5,4,3,2,1
		assertEquals(0, ascending.kDist(r_int_array));
	
	}
	
	@Test 
	(expected=IllegalArgumentException.class) public void kDistIllegalTest()
	{
		//Should throw IllegalArgumentException
		r_int.kDist(r_float_array);
		descending.kDist(single_element);
	}
	
	@Test 
	(expected=NullPointerException.class) public void kDistNullTest()
	{
		//Should throw NullPointerException
		r_int.kDist(r_exception_floatnull);
		r_int.kDist(r_exception_intnull);
	}
	
	/**
	 * public int invCount()
	 */
	@Test
	public void invCountTest()
	{
		//			 6,5,4,3,2,1
		//Compare   {1,2,3,4,5} so since 4 should be 3, 3 should be 5- two swaps necessary(Table 2)
		int[] one = {1,2,4,5,3};
		
		Ranking inv1 = new Ranking(one);
		
		assertEquals(2,inv1.invCount());
		assertEquals(0,ascending.invCount());
		assertEquals(3,descending.invCount());
		assertEquals(0,single_element.invCount());
	}
}
