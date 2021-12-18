import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AoC2
{
	
	public static void main(String[] args) throws FileNotFoundException
	{
		int xAxis = 0;
		int yAxis = 0;
		int aim = 0;
		
		Scanner scnr = new Scanner(new File("input2.txt"));
		
		while (scnr.hasNextLine())
		{
			twoVals directions = format(scnr.nextLine());
			
			switch (directions.getDirection())
			{
				case 'y':
					aim += directions.getDuration();
					break;
				case 'x':
					xAxis += directions.getDuration();
					yAxis += aim * directions.getDuration();
					break;
			}
		}
		
		System.out.printf("x axis: %d y axis: %d \n", xAxis, yAxis);
		
		System.out.println("Magnitude: " + (xAxis * yAxis));
	}
	
	public static twoVals format(String text)
	{
		if (text.charAt(0) == 'u')
		{
			return new twoVals('y', Integer.parseInt(text.substring(3)) * -1);
		}
		else if (text.charAt(0) == 'd')
		{
			return new twoVals('y', Integer.parseInt(text.substring(5)));
		}
		else
		{
			return new twoVals('x', Integer.parseInt(text.substring(8)));
		}
	}
	
	private static class twoVals
	{
		char direction;
		int duration;
		
		public twoVals(char direction, int duration)
		{
			this.direction = direction;
			this.duration = duration;
		}
		
		public char getDirection()
		{
			return direction;
		}
		
		public int getDuration()
		{
			return duration;
		}
	}
}
