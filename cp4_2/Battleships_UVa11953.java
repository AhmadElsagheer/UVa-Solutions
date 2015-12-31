package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Battleships_UVa11953 {

	static char[][] grid;
	static int[] dx = new int[]{0,0,1,-1};
	static int[] dy = new int[]{1,-1,0,0};
	static int N;
	
	static boolean dfs(int i, int j)
	{
		boolean flag = grid[i][j] == 'x';
		grid[i][j] = '.';
		for(int k = 0; k < 4; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y))
				flag = dfs(x,y) || flag;
		}
		return flag;
	}
	
	static boolean valid(int i, int j)
	{
		if(i == -1 || j == -1 || i == N || j == N || grid[i][j] =='.')
			return false;
		return true;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for(int t = 1; t <= tc; t++)
		{
			N = Integer.parseInt(br.readLine());
			grid = new char[N][];
			for(int i = 0; i < N; i++)
				grid[i] = br.readLine().toCharArray();
			
			int count = 0;
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					if(grid[i][j] != '.' && dfs(i,j))
						count++;
			sb.append("Case "+t+": "+count+"\n");
		}
		System.out.print(sb);
	}
}
