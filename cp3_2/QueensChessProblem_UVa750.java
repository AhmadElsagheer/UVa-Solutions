package cp3_2;

import java.io.PrintWriter;
import java.util.Scanner;

public class QueensChessProblem_UVa750 {

	static PrintWriter out = new PrintWriter(System.out);
	static int counter;
	static int[] row = new int[9];
	static int a,b;
	public static void backtrack(int col)
	{
		if(col==9)
		{
			if(row[b]==a)
				print();
			return;
		}
		for(int i = 1; i <= 8;i++)
			if(valid(col,i))
			{
				row[col] = i;
				backtrack(col+1);
			}
		
	}
	
	public static boolean valid(int col, int tryRow)
	{
		for(int i = 1; i < col; i++)
			if(row[i]==tryRow || Math.abs(tryRow-row[i])== Math.abs(col-i))
				return false;
		return true;
	}
	
	public static void print()
	{
		out.printf("%2d     ",++counter );
		for(int i = 1; i < 9; i++)
			out.printf(" %d",row[i]);
		out.println();
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		while(TC-->0)
		{
			out.println("SOLN       COLUMN\n #      1 2 3 4 5 6 7 8\n");
			counter = 0;
			a = sc.nextInt();
			b = sc.nextInt();
			
			backtrack(1);
			
			if(TC!=0)
				out.println();
		}
		out.flush();
	}
}
