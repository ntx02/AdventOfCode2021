import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class AoC4
{

	public static void main(String[] args) throws FileNotFoundException
	{
		int[] beginning = numbers("input4.txt");
		
		ArrayList<BingoBoard> boards = getBingoBoards("input4.txt");
		ArrayList<BingoBoard> boards1 = getBingoBoards("input4.txt");
		
		partOne(boards, beginning);
		partTwo(boards1, beginning);
	}
	
	private static ArrayList<BingoBoard> getBingoBoards(String fileName) throws FileNotFoundException
	{
		Scanner scnr = new Scanner(new File(fileName));
		scnr.nextLine();
		String all = "";
		
		while (scnr.hasNextLine())
		{
			all += scnr.nextLine() + "\n";
		}
		String[] preBoards = all.split("\n\\s*\n");
		
		for (int i = 0; i < preBoards.length; i++)
		{
			preBoards[i] = preBoards[i].trim();
		}
		
		ArrayList<BingoBoard> boards = new ArrayList<BingoBoard>();
		
		for (int i = 0; i < preBoards.length; i++)
		{
			String currentBoard = preBoards[i];
			ArrayList<ArrayList<customInt>> rowsBoard = new ArrayList<ArrayList<customInt>>();
			
			String[] rows = currentBoard.split("\n");
			
			for (int j = 0; j < rows.length; j++)
			{
				rows[j] = rows[j].replace("  ", " ");
			}
			
			for (int j = 0; j < rows.length; j++)
			{
				String[] vals = rows[j].split(" ");
				
				for (int k = 0; k < vals.length; k++)
				{
					vals[k] = vals[k].trim();
				}
				
				int[] values = new int[vals.length];
				
				for (int k = 0; k < vals.length; k++)
				{
					try
					{
						values[k] = Integer.parseInt(vals[k]);
					}
					catch (Exception e)
					{
						continue;
					}
				}
				
				ArrayList<customInt> iterRow = new ArrayList<customInt>();
				
				for (int k = 0; k < values.length; k++)
				{
					iterRow.add(new customInt(values[k]));
				}
				
				rowsBoard.add(iterRow);
			}
			
			boards.add(new BingoBoard(rowsBoard.get(0), rowsBoard.get(1), rowsBoard.get(2), rowsBoard.get(3), rowsBoard.get(4)));
		}
		
		return boards;
	}
	
	private static void partOne(ArrayList<BingoBoard> boards, int[] beginning)
	{
		for (int i = 0; i < beginning.length; i++)
		{
			int currentNumber = beginning[i];
			
			for (int j = 0; j < boards.size(); j++)
			{
				boards.get(j).mark(currentNumber);
			}
			
			for (int j = 0; j < boards.size(); j++)
			{
				if (boards.get(j).isWinner())
				{
					System.out.println("First Winner: " + currentNumber * boards.get(j).getUnmarkedSum());
					return;
				}
			}
		}
	}
	
	private static void partTwo(ArrayList<BingoBoard> boards, int[] beginning)
	{
		for (int i = 0; i < beginning.length; i++)
		{
			int currentNumber = beginning[i];
			
			for (int j = 0; j < boards.size(); j++)
			{
				boards.get(j).mark(currentNumber);
			}
			
			for (int j = 0; j < boards.size(); j++)
			{
				if (boards.get(j).isWinner())
				{
					if (boards.size() > 1)
					{
						boards.remove(j);
					}
					else
					{
						System.out.println("Last Winner: " + boards.get(j).getUnmarkedSum() * currentNumber);
						return;
					}
				}
			}
		}
	}
	
	private static int[] numbers(String file) throws FileNotFoundException
	{
		Scanner scnr = new Scanner(new File(file));
		String nums = scnr.nextLine();
		scnr.close();
		
		String[] numbers = nums.split(",");
		
		int[] numbArray = new int[numbers.length];
		
		for (int i = 0; i < numbers.length; i++)
		{
			numbArray[i] = Integer.parseInt(numbers[i]);
		}
		
		return numbArray;
	}
	
	private static class BingoBoard
	{
		ArrayList<customInt> row1;
		ArrayList<customInt> row2;
		ArrayList<customInt> row3;
		ArrayList<customInt> row4;
		ArrayList<customInt> row5;
		
		public BingoBoard(ArrayList<customInt> row1, ArrayList<customInt> row2, ArrayList<customInt> row3, ArrayList<customInt> row4, ArrayList<customInt> row5)
		{
			this.row1 = row1;
			this.row2 = row2;
			this.row3 = row3;
			this.row4 = row4;
			this.row5 = row5;
		}
		
		public void mark(int ball)
		{
			for (int i = 0; i < row1.size(); i++)
			{
				if (row1.get(i).value() == ball)
				{
					row1.get(i).setMarked();
					break;
				}
				
				if (row2.get(i).value() == ball)
				{
					row2.get(i).setMarked();
					break;
				}
				
				if (row3.get(i).value() == ball)
				{
					row3.get(i).setMarked();
					break;
				}
				
				if (row4.get(i).value() == ball)
				{
					row4.get(i).setMarked();
					break;
				}
				
				if (row5.get(i).value() == ball)
				{
					row5.get(i).setMarked();
					break;
				}
			}
		}
		
		public int getUnmarkedSum()
		{
			ArrayList<ArrayList<customInt>> arrays = new ArrayList<ArrayList<customInt>>(Arrays.asList(row1, row2, row3, row4, row5));
			int sum = 0;
			
			for (int i = 0; i < arrays.size(); i++)
			{
				ArrayList<customInt> currentRow = arrays.get(i);
				
				for (int j = 0; j < currentRow.size(); j++)
				{
					if (!currentRow.get(j).isMarked())
					{
						sum += currentRow.get(j).value();
					}
				}
			}
			
			return sum;
		}
		
		public boolean isWinner()
		{
			return checkAcross() || checkDown();
		}
		
		private boolean checkAcross()
		{
			ArrayList<ArrayList<customInt>> arrays = new ArrayList<ArrayList<customInt>>(Arrays.asList(row1, row2, row3, row4, row5));
			
			for (int i = 0; i < arrays.size(); i++)
			{
				ArrayList<customInt> currentRow = arrays.get(i);
				
				boolean allMarked = true;
				
				for (int j = 0; j < currentRow.size(); j++)
				{
					if (!currentRow.get(j).isMarked())
					{
						allMarked = false;
						break;
					}
				}
				
				if (allMarked)
				{
					return true;
				}
			}
			
			return false;
		}
		
		private boolean checkDown()
		{
			ArrayList<ArrayList<customInt>> arrays = new ArrayList<ArrayList<customInt>>(Arrays.asList(row1, row2, row3, row4, row5));
			
			for (int i = 0; i < arrays.size(); i++)
			{
				boolean allMarked = true;
				
				for (int j = 0; j < arrays.size(); j++)
				{
					if (!arrays.get(j).get(i).isMarked())
					{
						allMarked = false;
						break;
					}
				}
				
				if (allMarked)
				{
					return true;
				}
			}
			
			return false;
		}
	}
	
	private static class customInt
	{
		private int value;
		private boolean marked = false;
		
		public customInt(int integer)
		{
			value = integer;
		}
		
		public void setMarked()
		{
			marked = true;
		}
		
		public boolean isMarked()
		{
			return marked;
		}
		
		public int value()
		{
			return value;
		}
	}
}
