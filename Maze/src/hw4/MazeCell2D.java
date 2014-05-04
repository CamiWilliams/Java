package hw4;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Implementation of MazeCell that has a location in a 2D plane.
 */
public class MazeCell2D implements MazeCell
{
  /**
   * List of neighboring cells.
   */
  private ArrayList<MazeCell> neighbors;
  
  /**
   * Status of this cell.
   */
  private Status status;
  
  /**
   * Observer to be notified when this cell's status changes.
   */
  private MazeObserver observer;
  
  /**
   * Location of this cell.
   */
  private Point location;
  
  /**
   * Constructs a maze cell at the given location.
   * @param row
   * @param col
   */
  public MazeCell2D(int row, int col)
  {
    location = new Point(row, col);
  }
  
  /**
   * Adds an observer for changes in this cell's status.
   * @param obs
   */
  public void setObserver(MazeObserver obs)
  {
    observer = obs;    
  }
  
  /**
   * Returns this cell's location.
   * @return
   */
  public Point getLocation()
  {
    return location;
  }
  
  /**
   * Adds a neighbor to this cell.
   * @param m
   */
  public void addNeighbor(MazeCell2D m)
  {
    if (neighbors == null)
    {
      neighbors = new ArrayList<MazeCell>();
    }
    neighbors.add(m);
  }
  
  @Override
  public ArrayList<MazeCell> getNeighbors()
  {
    return neighbors;
  }

  @Override
  public void setStatus(Status s)
  {
    status = s;
    if (observer != null)
    {
      observer.updateStatus(this);
    }
  }

  @Override
  public Status getStatus()
  {
    return status;
  }

  @Override
  public String toString()
  {
    return "(" + location.x + ", " + location.y + ")";
  }
}
