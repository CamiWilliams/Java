package hw4;
import java.util.ArrayList;
import java.util.Scanner;


public class Main
{
  private static final String[] data = {
	  "####################",
	   "#           ########",
	   "# ##################",
	   "# ######     #######",
	   "# ###### ### #######",
	   "#    ###$### #######",
	   "#### ####### #######",
	   "#### ####### #######",
	   "####            ####",
	   "######## ###### ####",
	   "##   ### ###### ####",
	   "## # #   ###### ####",
	   "## #   ########    #",
	   "## # # ######## ####",
	   "## ### #####    ####",
	   "## #   ######## ####",
	   "#  # ########## ####",
	   "## #    ############",
	   "## #### ############",
	   "##S#################",
  };
      

  public static void main(String[] args)
  {
    // Create the maze from the string description
    Maze maze = new Maze(data);   
    
    // Start the UI, using a 500 millisecond delay
    MazePanel.start(maze, 100);
    
    // Create empty array list to store the path
    ArrayList<MazeCell> path = new ArrayList<MazeCell>();
    
    // Search beginning at the designated start cell
    Searcher.search(maze.getStart(), path);
    
    // Optionally, print the path to check it
    System.out.println(path);
  }

  
}
