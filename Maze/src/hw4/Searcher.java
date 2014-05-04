package hw4;
import java.util.ArrayList;
/**
 * @author Camryn Williams
 */
/**
 * Utility class for searching a maze described by a collection of MazeCell
 * objects.
 */
public class Searcher
{
	/**
	 * Recursively searches a given MazeCell and all of its unexplored
	 * neighbors. Returns true if the current cell is the goal or if the goal is
	 * found in a search initiated from this cell by searching a neighbor.
	 * Returns false if the goal is not found. If the goal is found, then when
	 * this method returns, the given ArrayList will contain a path from the
	 * current cell to the goal.
	 * 
	 * @param current
	 *            the current cell at which a search is being initiated
	 * @param solution
	 *            an empty array list
	 * @return true if the goal was found through this search, false otherwise
	 */
	public static boolean search(MazeCell current, ArrayList<MazeCell> solution)
	{	
		ArrayList<MazeCell> arr = current.getNeighbors();
		
		for(MazeCell cell : arr) //Search through array of neighbors
		{				
			if(cell.getStatus() == Status.GOAL)
			{
				solution.add(current);
				solution.add(cell);
				return true;
			}
			if(cell.getStatus() == Status.SUCCEEDED) //Cell is success or goal
			{
				solution.add(current);
				return true;
			}
			
			else if(cell.getStatus() == Status.UNEXPLORED) //Goal has not been found yet
			{
				cell.setStatus(Status.EXPLORING);
				solution.add(current);
				
				if(search(cell,solution) == false) //cell-- for each unexplored neighbor
				{
					cell.setStatus(Status.FAILED);
					solution.remove(current);
				}
				
				else
				{
					cell.setStatus(Status.SUCCEEDED);
					return true;
				}
			}
			
		}
		
		return false;
	}
}
