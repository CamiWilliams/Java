package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.iastate.cs228.hw1.Animal.Gender;

/**
 * 
 * @author Camryn Williams
 * 
 */
public class RiverSimulator 
{
	/**
	 * Repeatedly simulates evolutions of river ecosystems. Each iteration
	 * should execute as the Project Description describes.
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		int trial = 1;
		
		System.out.println("River Ecosystem Simulator");
		System.out.println("keys: 1 (random river) 2 (file input) 3 (exit)");
		System.out.println();
		
		while(trial > 0)
		{
			int counter = 1;
			System.out.print("Trial " + trial + ": ");
			int type = scanner.nextInt();
		
			if(type == 1)
			{
				System.out.println("Random river");
				System.out.print("Enter river length: ");
				int length = scanner.nextInt();
				System.out.print("Enter number of cycles: ");
				int cycles = scanner.nextInt();
			
				River river = new River(length);
			
				System.out.println("Initial river: ");
				System.out.println();
				System.out.println(river.toString());
				System.out.println();
				
				while(counter <= cycles)
				{
					System.out.println("After cycle " + counter + ":");
					river.updateRiver();
					System.out.println();
					System.out.println(river.toString());
					System.out.println();
					counter++;
				}
			
			}
		
			else if(type == 2)
			{
				System.out.println("File input");
				System.out.print("Enter complete file name: ");
				String f = scanner.next();
				try
				{
					River river = new River(f);
					System.out.print("Enter number of cycles: ");
					int cycles = scanner.nextInt();
					System.out.println("Initial river: ");
					System.out.println();
					System.out.println(river.toString());
					System.out.println();
					
					while(counter <= cycles)
					{
						System.out.println("After cycle " + counter + ":");
						river.updateRiver();
						System.out.println();
						System.out.println(river.toString());
						System.out.println();
						counter++;
					}
				}
				
				catch(FileNotFoundException e)
				{
					System.out.println("This file does not exist. Goodbye.");
					System.exit(-1);
				}
				
			}
		
			else if(type == 3)
			{
				System.out.println("exit");
				System.exit(-1);
			}
		
			else
			{
				System.out.println("This is wrong, Goodbye.");
				System.exit(-1);
			}
			
			trial++;
		}
	}
}