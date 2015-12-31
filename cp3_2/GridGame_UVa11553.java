package cp3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GridGame_UVa11553 {

	static int N;
	static int[][] grid;
	static boolean[] used_row;
	static boolean[] used_col;
	
	static int backtrack(int k, int score)
	{
		if(k==N)
			return score;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++)
			if(!used_col[i])
			{
				used_col[i] = true;
				int min_col = Integer.MAX_VALUE;
				int r = -1;
				for(int j = 0; j < N; j++)
					if(!used_row[j] && grid[j][i]<min_col)
					{
						min_col = grid[j][i];
						r = j;
					}
				used_row[r] = true;
				min = Math.min(min, backtrack(k+1,min_col+score));
				used_row[r] = false;
				used_col[i] = false;
			}
		return min;
	}
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		while(TC-->0)
		{
			N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			grid = new int[N][N];
			for(int i = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
					grid[i][j] = Integer.parseInt(st.nextToken());
			}
			used_row = new boolean[N];
			used_col = new boolean[N];
			int sol = backtrack(0,0);
			sb.append(sol).append("\n");
			
		}
		System.out.print(sb);
	}
}
