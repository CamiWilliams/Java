package hw4;
/**
 * Maze consisting of cells arranged in a 2D grid.
 */
public class Maze implements MazeObserver
{
  /**
   * The cells for this maze.
   */
  private MazeCell2D[][] cells;
  
  /**
   * Cell representing the starting location for searching
   * this maze.
   */
  private MazeCell start;
  
  /**
   * Observer to be notified in case of changes to cells
   * in this maze.
   */
  private MazeObserver observer;
  

  /**
   * Constructs a maze based on a 2D grid.  The given strings
   * represent rows of the maze, where '#' represents a wall,
   * a blank represents a possible path, 'S' represents the 
   * starting cell, and '$' represents the goal.
   * @param rows
   */
  public Maze(String[] rows)
  {
    //you 227 kids looking, I see you.
    //nice try
   }

  /**
   * Returns the cell at the given position.
   * @param row
   * @param col
   * @return
   */
  public MazeCell2D getCell(int row, int col)
  {
    return cells[row][col];
  }
  
  /**
   * Returns the number of rows in the grid for this maze.
   * @return
   */
  public int getRows()
  {
    return cells.length;
  }
  
  /**
   * Returns the number of columns in the grid for this maze.
   * @return
   */
  public int getColumns()
  {
    return cells[0].length;
  }
  
  /**
   * Returns the starting cell for this maze.
   * @return
   */
  public MazeCell getStart()
  {
    return start;
  }
  
  /**
   * Sets the observer for the cells of this maze.
   * @param obs
   */
  public void setObserver(MazeObserver obs)
  {
    observer = obs;
  }

  /**
   * Returns true if the given cell is not a wall.
   * @param cell
   * @return
   */
  private boolean isAccessible(MazeCell2D cell)
  {
    return cell != null;
  }

  @Override
  public void updateStatus(MazeCell2D cell)
  {
    if (observer != null)
    {
      observer.updateStatus(cell);
    }
  }

}
