package edu.iastate.cs228.hw2;

/**
 * Interface to help you not accidentally change any method declarations. Do not
 * change anything within this file.
 * 
 * @author Camryn Williams
 * 
 */
public interface IRanking 
{
	
	public int getNumItems();
	public int getRank(int i);
	public int fDist(Ranking other);
	public int kDist(Ranking other);
	public int invCount();
}
