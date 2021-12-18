import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AoC1
{
	public static void main(String[] args) throws FileNotFoundException
	{
		partTwo();
	}
	
	public static void partOne() throws FileNotFoundException
	{
		Scanner scnr = new Scanner(new File("input1.txt"));
		
		if (!scnr.hasNextLine())
		{
			System.out.println("Number of times: 0");
			System.exit(0);
		}
		
		int currVal = Integer.parseInt(scnr.nextLine());
		int numLarger = 0;
		
		while (scnr.hasNextLine())
		{
			int curr = Integer.parseInt(scnr.nextLine());
			
			if (curr > currVal)
			{
				numLarger++;
			}
			
			currVal = curr;
		}
		
		System.out.println("Number of times: " + numLarger);
	}
	
	public static void partTwo() throws FileNotFoundException
	{	
		int numLarger = 0;
		int advanced = 0;
		
		while (true)
		{
			try
			{
				Scanner newScan = new Scanner(new File("input1.txt"));
				
				for (int i = 0; i < advanced + 1; i++)
				{
					newScan.nextLine();
				}
				
				int a = Integer.parseInt(newScan.nextLine());
				int b = Integer.parseInt(newScan.nextLine());
				int c = Integer.parseInt(newScan.nextLine());
				int d = Integer.parseInt(newScan.nextLine());
				
				int sumOne = a + b + c;
				int sumTwo = b + c + d;
				
				System.out.printf("Sum 1: %d, Sum 2: %d\n", sumOne, sumTwo);
				
				if (sumTwo > sumOne)
				{
					numLarger ++;
				}
				
				advanced ++;
				newScan.close();
			}
			catch (Exception e)
			{
				break;
			}
	
		}
		
		System.out.println("Number of times: " + numLarger);
	}
}
