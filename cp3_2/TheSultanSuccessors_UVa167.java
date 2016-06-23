package cp3_2;
import java.io.PrintWriter;
import java.util.Scanner;
public class TheSultanSuccessors_UVa167 {

	static int[] col;
	static int[][] board;
	
	public static int backtrack(int row, int score)
	{
		if(row==8)
			return score;
		int max = 0;
		for(int i = 0; i < 8; i++)
			if(validCol(row,i))
			{
				col[row] = i;
				max = Math.max(max, backtrack(row+1,score+board[row][i]));
			}
		return  max;
	}
	
	public static boolean validCol(int row, int tryCol)
	{
		for(int i = 0; i < row; i++)
			if(col[i]==tryCol || Math.abs(tryCol-col[i])==Math.abs(row-i))
				return false;
		return true;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int TC = sc.nextInt();
		while(TC-->0)
		{
			board = new int[8][8];
			for(int i = 0; i < 8; i++)
				for(int j = 0; j < 8; j++)
					board[i][j] = sc.nextInt();
			col = new int[8];
			String best = ""+backtrack(0,0);
			while(best.length()<5)
				best = " "+best;
			out.println(best);
			
		}
		out.flush();
	}
}

