package hw4;
import java.util.ArrayList;

/**
 * Abstract representation of a location in a maze.
 */
public interface MazeCell
{
  /**
   * Returns the neighboring cells.
   * @return
   *   list of neighbors of this cell
   */
  public ArrayList<MazeCell> getNeighbors();
  
  /**
   * Sets the status of this cell.
   * @param s
   *   new status for this cell
   */
  public void setStatus(Status s);
  
  /**
   * Returns the status of this cell.
   * @return
   *   status of this cell
   */
  public Status getStatus();
  
}

