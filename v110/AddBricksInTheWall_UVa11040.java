package v110;

import java.io.PrintWriter;
import java.util.Scanner;

public class AddBricksInTheWall_UVa11040 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int TC = sc.nextInt();
		
		while(TC-->0)
		{
			int[][] wall = new int[9][];
			for(int i = 0; i < 9; i ++)
			{
				wall[i] = new int[i+1];
				if(i%2==1)
					continue;
				for(int j = 0; j <= i; j+=2)
					wall[i][j] = sc.nextInt();
			}
			
			for(int i = 8; i > 1; i -=2)
			{
				for(int j = 1, k = 0; j < i; j+=2, k+=2)
					wall[i][j] = (wall[i-2][k] - (wall[i][j-1] + wall[i][j+1]))/2;
				for(int j = 0; j < i; j++)
					wall[i-1][j] = wall[i][j] + wall[i][j+1];
			}
			
			for(int i = 0; i < 9; i++)
			{
				for(int j = 0; j < wall[i].length - 1; j++)
					out.print(wall[i][j]+" ");
				out.println(wall[i][wall[i].length-1]);
			}
				
			
					
		}
		
		out.flush();
		out.close();
	}
}
