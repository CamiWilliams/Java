package edu.iastate.cs228.hw2;

/**
 * 
 * This is the main class where you can run your code. This will not be graded.
 * 
 * @author Camryn Williams
 * 
 */
public class Launcher 
{

	/**
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) 
	{
		int[] int_array = {4,3,1,5,2};
		float[] float_array = {0.75f,0.36f,0.65f,-1.5f,0.85f};
		int[] n = null;
	
		Ranking rank_float = new Ranking(float_array);
		Ranking rank_int = new Ranking(int_array);
		Ranking rank_random = new Ranking(5);
		//works Ranking bad = new Ranking(float_array_bad);
		//Ranking bad2 = new Ranking(n);
		
		System.out.println("getNumItems should be 5: " + rank_random.getNumItems());
		System.out.println("getNumItems should be 5: " + rank_int.getNumItems());
		System.out.println("getNumItems should be 5: " + rank_float.getNumItems());
		
		System.out.println();
		
		System.out.println("getRank should be 2: " + rank_int.getRank(1));
		System.out.println("getRank should be 4: " + rank_float.getRank(2));
		
		System.out.println();
		
		int[] r1 = {4,3,1,5,2};
		int[] r2 = {5,4,1,3,2};
		
		Ranking r_1 = new Ranking(r1);
		Ranking r_2 = new Ranking(r2);
		System.out.println("Footrule distance should be 4: " + Ranking.footrule(r_1,r_2));
		
		System.out.println();
		
		int[] b = {1,5,6,3,4,7,2};
		Ranking in1 = new Ranking(b);
		System.out.println("invCount should be 12: " + in1.invCount());
		int[] c = {9,3,7,6,1,8,2,4,5};
		Ranking in2 = new Ranking(c); //rank is 1,7,3,4,9,2,8,6,5
		System.out.println("invCount should be 14: " + in2.invCount());
		int[] d = {4,2,5,1,3};//{2,4,1,5,3};
		Ranking in3 = new Ranking(d);
		System.out.println("invCount should be 4: " + in3.invCount());
		
		System.out.println();
		
		int[] city1 = {2,3,5,1,4}; //rank is {4,3,1,5,2}
		int[] city2 = {1,2,5,3,4}; //{5,4,1,3,2}
		Ranking rank_city1 = new Ranking(city1);
		Ranking rank_city2 = new Ranking(city2);
		System.out.println("Kemeny distance should be 2: " + Ranking.kemeny(rank_city1, rank_city2));
		
	}

}
