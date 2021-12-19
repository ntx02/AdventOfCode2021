import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC5
{

	public static void main(String[] args) throws FileNotFoundException
	{
		long time = System.currentTimeMillis();
		ArrayList<Point> points = getPoints("input5.txt");
		int totalPoints = points.size();
		int numberOfPoints = processPoints(points);
		time = System.currentTimeMillis() - time;
		System.out.println(numberOfPoints);
		System.out.println("Seconds taken to process through " + totalPoints + " points: " + time / 1000);
	}
	
	private static ArrayList<Point> getPoints(String file) throws FileNotFoundException
	{
		Scanner scnr = new Scanner(new File(file));
		ArrayList<Point> points = new ArrayList<Point>();
		
		while (scnr.hasNextLine())
		{
			String currLine = scnr.nextLine();
			currLine = currLine.replace(" -> ", ",");
			String[] coordinates = currLine.split(",");
			int[] intCoords = new int[coordinates.length];
			
			for (int i = 0; i < coordinates.length; i++)
			{
				intCoords[i] = Integer.parseInt(coordinates[i]);
			}
			
			Line line = new Line(intCoords[0], intCoords[1], intCoords[2], intCoords[3]);
			line.createPoints();
			ArrayList<Point> createdPoints = line.getPoints();
			
			for (int i = 0; i < createdPoints.size(); i++)
			{
				points.add(createdPoints.get(i));
			}
		}
		
		return points;
	}
	
	private static int processPoints(ArrayList<Point> points)
	{
		int overlappingLines = 0;
		int currentPosition = 0;
		
		while (points.size() > 1 && currentPosition < points.size())
		{
			int overlappingPoints = 0;
			ArrayList<Integer> positions = new ArrayList<Integer>();
			positions.add(currentPosition);
			
			Point currentPoint = points.get(currentPosition);
			int currentX = currentPoint.x;
			int currentY = currentPoint.y;
			
			for (int i = currentPosition + 1; i < points.size(); i++)
			{
				Point iterPoint = points.get(i);
				if (iterPoint.x == currentX && iterPoint.y == currentY)
				{
					positions.add(i);
					overlappingPoints++;
				}
			}
			
			if (overlappingPoints >= 1)
			{	
				overlappingLines++;
				
				for (int i = 0; i < positions.size(); i++)
				{
					points.remove(positions.get(i) - i);
				}
				
				continue;
			}
			
			currentPosition++;
		}
		
		return overlappingLines;
	}
	
	private static class Line
	{
		private ArrayList<Point> points = new ArrayList<Point>();
		
		private int startX;
		private int startY;
		private int endX;
		private int endY;
		
		public Line(int startX, int startY, int endX, int endY)
		{
			this.startX = startX;
			this.startY = startY;
			this.endX = endX;
			this.endY = endY;
		}
		
		public void createPoints()
		{
			if (startX == endX)
			{
				if (startY < endY)
				{
					for (int i = startY; i <= endY; i++)
					{
						points.add(new Point(startX, i));
					}
				}
				else
				{
					for (int i = endY; i <= startY; i++)
					{
						points.add(new Point(startX, i));
					}
				}
			}
			else if (startY == endY)
			{
				if (startX < endX)
				{
					for (int i = startX; i <= endX; i++)
					{
						points.add(new Point(i, startY));
					}
				}
				else
				{
					for (int i = endX; i <= startX; i++)
					{
						points.add(new Point(i, startY));
					}
				}
			}
			else
			{
				if (startX < endX && startY < endY)
				{
					int distance = endX - startX;
					
					for (int i = 0; i <= distance; i++)
					{
						points.add(new Point(startX + i, startY + i));
					}
				}
				else if (startX < endX && startY > endY)
				{
					int distance = endX - startX;
					
					for (int i = 0; i <= distance; i++)
					{
						points.add(new Point(startX + i, startY - i));
					}
				}
				else if (endX < startX && startY < endY)
				{
					int distance = startX - endX;
					
					for (int i = 0; i <= distance; i++)
					{
						points.add(new Point(startX - i, startY + i));
					}
				}
				else if (endX < startX && startY > endY)
				{
					int distance = startX - endX;
					
					for (int i = 0; i <= distance; i++)
					{
						points.add(new Point(startX - i, startY - i));
					}
				}
			}
		}
		
		public ArrayList<Point> getPoints()
		{
			return points;
		}
	}
	
	private static class Point
	{
		public int x;
		public int y;
		
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}
