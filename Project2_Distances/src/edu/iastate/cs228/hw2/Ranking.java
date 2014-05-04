package edu.iastate.cs228.hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Camryn Williams
 * 
 */
public class Ranking implements IRanking 
{

	/**
	 * Your code must include implementations of algorithms SORTANDCOUNT and
	 * MERGEANDCOUNT and it must use these implementations to count inversions
	 * and compute Kemeny distance.
	 */
	
	private static int items;
	private int[] rank_array;
	private static int inv;

	/**
	 * Constructs a random ranking of the numbers 1 through n. Throws an
	 * IllegalArgumentException if n < 1. Must run in O(n log n) time <br>
	 * <br>
	 * <strong>Note:</strong> For random number generation, use the
	 * RandomSingleton class from Project 1; this generator might be modified
	 * slightly for use in this project. To generate a random permutation of 1
	 * through n, use the <a href=
	 * "http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm"
	 * >"shuffle" algorithm</a>
	 * 
	 * @param n
	 *            Number of random rankings to create
	 * @throws IllegalArgumentException
	 *             if n < 1
	 */
	
	public Ranking(int n) 
	{
		int[] a = new int[n];
		
		if(n < 1)
		{
			throw new IllegalArgumentException();
		}
		
		for(int i = 0; i < n; i++)
		{
			a[i] = i+1;
		}
	
		for(int i = 1; i < n; i++)
		{
			int j = RandomSingleton.getInstance().nextInt(n-1);
			int temp = a[j];
			a[j] = a[i];
			a[i] = temp;
		}
		rank_array = a;
		items = a.length;
	}

	/**
	 * Constructs a ranking o of the set U = {1,...,rank.length}, where o(i) =
	 * rank[i-1]. Throws a NullPointerException if rank is null. Throws an
	 * IllegalArgumentException if rank does not consist of distinct elements
	 * between 1 and rank.length. Must run in O(n log n) time or better, where n
	 * = rank.length.
	 * 
	 * @param rank
	 *            Ranking set
	 * @throws NullPointerException
	 *             if rank is null
	 * @throws IllegalArgumentException
	 *             if rank does not consist of distinct elements between 1 and
	 *             rank.length
	 */
	public Ranking(int[] rank) 
	{
		if(rank == null)
		{
			throw new NullPointerException();
		}
		
		items = rank.length;
		rank_array = new int[items];
		rank_array = helper2(rank, rank_array);
		
		if(helper1(rank_array) == false)
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Constructs a ranking of the set U = {1,...,scores.length}, where element
	 * i gets rank k if and only if scores[i-1] is the kth largest element in
	 * the array scores. Throws a NullPointerException if scores is null. Throws
	 * an IllegalArgumentException if scores contains duplicate values. Must run
	 * in O(n log n) time, where n = scores.length. <br>
	 * <br>
	 * Example: <br>
	 * Suppose scores = (0.75, 0.36, 0.65, -1.5, 0.85). Then, the corresponding
	 * ranking is o = (2, 4, 3, 5, 1).
	 * 
	 * @param scores
	 *            Scores set
	 * @throws NullPointerException
	 *             if scores is null
	 * @throws IllegalArgumentException
	 *             if scores contains duplicate values
	 */
	public Ranking(float[] scores) 
	{
		if(scores == null)
		{
			throw new NullPointerException();
		}
		
		items = scores.length;
		rank_array = new int[items];
		rank_array = helper3(scores, rank_array);
		
		if(helper1(rank_array) == false)
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Checks for duplicates, and out of bounds integers
	 * @return true if the array checks out
	 * 			false if the array does not consist of distinct elements between 1 and rank.length
	 */
	private static boolean helper1(int[] r)
	{
		int i = 0;
		while(i < r.length)
		{
			if(r[i] > r.length || r[i] < 1)
			{
				return false;
			}
			
			i++;
		}
		
		int[] x = r.clone();
		Arrays.sort(x); //O(nlogn)
		int j = 0;
		while(j < x.length-1)
		{
			if(x[j] == x[j+1])
			{
				return false;
			}
			
			j++;
		}
		
		
		return true;
	}
	
	/**
	 * Creates the ranking array for the int constructor
	 * @param ra is the "rank" input of ranking
	 * @param rank_object is the int array of Ranking
	 * @return rank_obect
	 */
	
	private static int[] helper2(int[] ra, int[] rank_object)
	{
		int[] unsorted = ra.clone();
		int[] sorted = ra.clone();
		Arrays.sort(sorted); //O(nlogn)
		int j = 0;
		int[] ranking_numbers = new int[ra.length];
		
		for(int i = 0; i < ra.length; i++)
		{
			ranking_numbers[i] = ra.length - i;
		}
		
		for(int i = 0; i < ra.length; i++)
		{
			if(j < ra.length)
			{
				if(sorted[i] == unsorted[j])
				{
					rank_object[j] = ranking_numbers[i];
					i = -1;
					j++;
				}
			}
			
			else
			{
				break;
			}
		}
		
		return rank_object;
	}
	
	/**
	 * Creates the ranking array for the float constructor
	 * @param ra is the "scores" input of ranking
	 * @param rank_object is the int array of Ranking
	 * @return rank_object
	 */
	private static int[] helper3(float[] ra, int[] rank_object)
	{
		float[] unsorted = ra.clone();
		float[] sorted = ra.clone();
		Arrays.sort(sorted);
		int j = 0;
		int[] ranking_numbers = new int[ra.length];
		
		for(int i = 0; i < ra.length; i++)
		{
			ranking_numbers[i] = ra.length - i;
		}
		
		for(int i = 0; i < ra.length; i++)
		{
			if(j < ra.length)
			{
				if(sorted[i] == unsorted[j])
				{
					rank_object[j] = ranking_numbers[i];
					i = -1;
					j++;
				}
			}
			
			else
			{
				break;
			}
		}
		
		return rank_object;
	}
	/**
	 * @return the number of items in the ranking. Must run in O(1) time
	 */
	public int getNumItems() 
	{
		return items;
	}

	/**
	 * Returns the rank of item i. Throws an IllegalArgumentException if item i
	 * is not present in the ranking. Must run in O(1) time.
	 * 
	 * @param i
	 *            Item to get rank for
	 * @return the rank of item i
	 * @throws IllegalArgumentException
	 *             if item i is not present in the ranking
	 */
	public int getRank(int i) 
	{
		return rank_array[i-1];
	}

	/**
	 * Returns the foot-rule distance between r1 and r2. Throws a
	 * NullPointerException if either r1 or r2 is null. Throws an
	 * IllegalArgumentException if r1 and r2 have different lengths. Must run in
	 * O(n) time, where n is the number of elements in r1 (or r2).
	 * 
	 * @param r1
	 *            Ranking object
	 * @param r2
	 *            Ranking object
	 * @return the foot-rule distance between r1 and r2
	 * @throws NullPointerException
	 *             if either r1 or r2 is null
	 * @throws IllegalArgumentException
	 *             if r1 and r2 have different lengths
	 */
	public static int footrule(Ranking r1, Ranking r2) 
	{
		if(r1 == null || r2 == null)
		{
			throw new NullPointerException();
		}
		
		if(r1.getNumItems() > r2.getNumItems() || r1.getNumItems() < r2.getNumItems())
		{
			throw new IllegalArgumentException();
		}
		
		int total = 0;
		 
		 for(int i = 1; i <= r1.getNumItems(); i++)
		 {
			 total += Math.abs(r1.getRank(i) - r2.getRank(i));    
		 }
		 
		 return total;
	
	}

	/**
	 * Returns the Kemeny distance between r1 and r2. Throws a
	 * NullPointerException if either r1 or r2 is null. Throws an
	 * IllegalArgumentException if r1 and r2 have different lengths. Must run in
	 * O(n log n) time, where n is the number of elements in r1 (or r2).
	 * 
	 * @param r1
	 *            Ranking object
	 * @param r2
	 *            Ranking object
	 * @return the Kemeny distance between r1 and r2
	 * @throws NullPointerException
	 *             if either r1 or r2 is null
	 * @throws IllegalArgumentException
	 *             if r1 and r2 have different lengths
	 */
	public static int kemeny(Ranking r1, Ranking r2) 
	{
		if(r1.getNumItems() > r2.getNumItems() || r1.getNumItems() < r2.getNumItems())
		{
			throw new IllegalArgumentException();
		}
		
		if(r1 == null || r2 == null)
		{
			throw new NullPointerException();
		}
		
		int l = r1.rank_array.length;
		
		int[] unsort = r1.rank_array.clone();
		int[] sort = sortAndCount(r1.rank_array,l);
		int[] re_sort = new int[l];
		int[] difference = new int[l];
		
		for(int i = 0; i < l; i++)
		{
			int index_unsort = i;
			int index_sort = Arrays.binarySearch(sort,unsort[i]);
			
			difference[i] = index_sort - index_unsort;
		}
		
		for(int i = 0; i < l; i++)
		{
			re_sort[i + difference[i]] = r2.rank_array[i];
		}
		
		inv = 0;
		sortAndCount(re_sort, l);
		
		return inv;
	}

	/**
	 * Returns the footrule distance between this and other. Throws a
	 * NullPointerException if other is null. Throws an IllegalArgumentException
	 * if this and other have different lengths. Must run in O(n) time, where n
	 * is the number of elements in this (or other).
	 * 
	 * @param other
	 *            Ranking object
	 * @return the footrule distance between this and other
	 * @throws NullPointerException
	 *             if other is null
	 * @throws IllegalArgumentException
	 *             if this and other have different lengths
	 */
	public int fDist(Ranking other) 
	{
		return footrule(this, other);
	}

	/**
	 * Returns the Kemeny distance between this and other. Throws a
	 * NullPointerException if other is null. Throws an IllegalArgumentException
	 * if this and other have different lengths. Must run in O(n log n) time,
	 * where n is the number of elements in this (or other).
	 * 
	 * @param other
	 *            Ranking object
	 * @return the Kemeny distance between this and other
	 * @throws NullPointerException
	 *             if other is null
	 * @throws IllegalArgumentException
	 *             if this and other have different lengths
	 */
	public int kDist(Ranking other) 
	{
		return kemeny(this, other);
	}

	/**
	 * Returns the number of inversions in this ranking. Should run in O(n log
	 * n) time, where n is the number of elements in this.
	 * 
	 * @return the number of inversions in t
	 */
	public int invCount() 
	{
		inv = 0;
		sortAndCount(this.rank_array, this.rank_array.length);
		return inv;
	}
	
	/**
	 * Sorts the rank_array and in the process finds the inversion count
	 * @param r is the rank_array
	 * @param n is the number of elements in rank_array
	 * @return the sorted rank_array
	 */
	private static int[] sortAndCount(int[] r, int n)
	{
		int m = n/2;
		int k = n - m;
		
	    if(n == 1)
	    {
	    
	    }
	    
	    else
	    {
	    	//rhs split
			int[] right = new int[k];
			for(int j = 0; j < right.length; j++)
			{
				right[j] = r[m+j];
			}
	    	
			//lhs split
			int[] left = new int[m];
			for(int i = 0; i < left.length; i++)
			{
				left[i] = r[i];
			}
			
			left = sortAndCount(left, left.length);
			right = sortAndCount(right, right.length);
			r = mergeAndCount(left, right);
	    }
	    
	    return r;
	}
	
	/**
	 * Merges the left and right hand side of the array while counting the inversions
	 * @param lhs left part of the rank_array
	 * @param rhs right part of the rank_array
	 * @return rank_array
	 */
	private static int[] mergeAndCount(int[] lhs, int[] rhs)
	{
		int p = lhs.length;
		int q = rhs.length;
		int[] y = new int[p+q];
		
		int i = 0;
		int j = 0;
		int k = 0;
		while(i < p && j < q)
		{
			if(lhs[i] < rhs[j])
			{
				y[k] = lhs[i];
				i++;
				k++;
			}
			
			
			else if(lhs[i] > rhs[j])
			{
				y[k] = rhs[j];
				inv += (p - i);
				j++;
				k++;
			}
		}
		
		if(i > p - 1)
		{
			for(int x = 0; x < y.length; x++)
			{
				if(x+j < rhs.length)
				{
					y[k] = rhs[x+j];
					k++;
				}
			}
		}
		
		if(j > q - 1)
		{
			for(int x = 0; x < y.length; x++)
			{
				if(x+i < lhs.length)
				{
					y[k] = lhs[x+i];
					k++;
				}
			}
		}
		return y;
	}

}
