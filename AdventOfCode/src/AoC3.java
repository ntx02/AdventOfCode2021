import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC3
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scnr = new Scanner(new File("input3.txt"));
		int lengthBinary = scnr.nextLine().length();
		scnr.close();
		
		int[] gammaRate = new int[lengthBinary];
		int[] epsilonRate = new int[lengthBinary];
		
		for (int i = 0; i < lengthBinary; i++)
		{
			Scanner scnr1 = new Scanner(new File("input3.txt"));
			
			int numberZeros = 0;
			int numberOnes = 0;
			
			while (scnr1.hasNextLine())
			{
				int digitAt = Integer.parseInt(scnr1.nextLine().substring(i, i + 1));
				
				switch (digitAt)
				{
					case 0:
						numberZeros ++;
						break;
					case 1:
						numberOnes ++;
						break;
				}
			}
			
			if (numberZeros > numberOnes)
			{
				gammaRate[i] = 0;
				epsilonRate[i] = 1;
			}
			else
			{
				gammaRate[i] = 1;
				epsilonRate[i] = 0;
			}
			
			scnr1.close();
		}
		
		Scanner scnr1 = new Scanner(new File("input3.txt"));
		ArrayList<String> binaries = new ArrayList<String>();
		ArrayList<String> binaries1 = new ArrayList <String>();
		
		while (scnr1.hasNextLine())
		{
			String line = scnr1.nextLine();
			binaries.add(line);
			binaries1.add(line);
		}
		scnr1.close();
		
		int oxygenRating = generateOxygenRating(0, binaries);
		int scrubberRating = generateScrubberRating(0, binaries1);
		
		System.out.print("Gamma Rate in binary: ");
		for (int i = 0; i < lengthBinary; i++)
		{
			System.out.print(gammaRate[i]);
		}
		System.out.println();
		
		System.out.print("Epsilon Rate in binary: ");
		for (int i = 0; i < lengthBinary; i++)
		{
			System.out.print(epsilonRate[i]);
		}
		System.out.println();
		
		System.out.println("Gamma Rate in baseTen: " + binaryToBaseTen(gammaRate));
		System.out.println("Epsilon Rate in baseTen: " + binaryToBaseTen(epsilonRate));
		System.out.println("Oxygen Rating in baseTen: " + oxygenRating);
		System.out.println("Scrubber Rating in baseTen: " + scrubberRating);
		
		System.out.println("Power Consumption in baseTen: " + binaryToBaseTen(gammaRate) * binaryToBaseTen(epsilonRate));
		System.out.println("Life Support Rating in baseTen: " + oxygenRating * scrubberRating);
	}
	
	
	private static int binaryToBaseTen(int[] binaryNumber)
	{
		int sum = 0;
		
		for (int i = 0; i < binaryNumber.length; i++)
		{
			int powerOfTwo = binaryNumber.length - 1 - i;
			
			sum += binaryNumber[i] * Math.pow(2, powerOfTwo);
		}
		
		return sum;
	}
	
	private static int generateOxygenRating(int position, ArrayList<String> stList)
	{
		if (stList.size() == 1)
		{
			String finalNum = stList.get(0);
			int[] binaryNumber = new int[finalNum.length()];
			
			for (int i = 0; i < binaryNumber.length; i++)
			{
				binaryNumber[i] = Integer.parseInt(finalNum.substring(i, i + 1));
			}
			
			return binaryToBaseTen(binaryNumber);
		}
		
		int numZero = 0;
		int numOne = 0;
		boolean keepOne = true;
		
		for (int i = 0; i < stList.size(); i++)
		{
			int currNum = Integer.parseInt(stList.get(i).substring(position, position + 1));
			
			switch (currNum)
			{
				case 0:
					numZero ++;
					break;
				case 1:
					numOne ++;
					break;
			}
		}
		
		if (numZero > numOne)
		{
			keepOne = false;
		}
		
		if (keepOne)
		{
			for (int i = 0; i < stList.size(); i++)
			{
				if (Integer.parseInt(stList.get(i).substring(position, position + 1)) != 1)
				{
					stList.remove(i);
					i--;
				}
			}
		}
		else
		{
			for (int i = 0; i < stList.size(); i++)
			{
				if (Integer.parseInt(stList.get(i).substring(position, position + 1)) == 1)
				{
					stList.remove(i);
					i--;
				}
			}
		}
		
		return generateOxygenRating(position + 1, stList);
	}
	
	private static int generateScrubberRating(int position, ArrayList<String> stList)
	{
		if (stList.size() == 1)
		{
			String finalNum = stList.get(0);
			int[] binaryNumber = new int[finalNum.length()];
			
			for (int i = 0; i < binaryNumber.length; i++)
			{
				binaryNumber[i] = Integer.parseInt(finalNum.substring(i, i + 1));
			}
			
			return binaryToBaseTen(binaryNumber);
		}
		
		int numZero = 0;
		int numOne = 0;
		boolean keepOne = false;
		
		for (int i = 0; i < stList.size(); i++)
		{
			int currNum = Integer.parseInt(stList.get(i).substring(position, position + 1));
			
			switch (currNum)
			{
				case 0:
					numZero ++;
					break;
				case 1:
					numOne ++;
					break;
			}
		}
		
		if (numZero > numOne)
		{
			keepOne = true;
		}
		
		if (keepOne)
		{
			for (int i = 0; i < stList.size(); i++)
			{
				if (Integer.parseInt(stList.get(i).substring(position, position + 1)) != 1)
				{
					stList.remove(i);
					i--;
				}
			}
		}
		else
		{
			for (int i = 0; i < stList.size(); i++)
			{
				if (Integer.parseInt(stList.get(i).substring(position, position + 1)) == 1)
				{
					stList.remove(i);
					i--;
				}
			}
		}
		
		return generateScrubberRating(position + 1, stList);
	}
}
