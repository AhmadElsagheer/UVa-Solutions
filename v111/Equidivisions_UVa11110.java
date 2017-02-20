package v111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Equidivisions_UVa11110 {

	static int N;
	static int[][] grid;
	static int[] dx = new int[]{0,0,1,-1};
	static int[] dy = new int[]{-1,1,0,0};
	
	static int dfs(int i, int j)
	{
		int count  = 1;
		int num = grid[i][j];
		grid[i][j] = -1;
		for(int k = 0; k < 4; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y) && grid[x][y]==num)
				count += dfs(x,y);
		}
		
		return count;
	}
	static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==N || j==N)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			grid = new int[N][N];
			for(int k = 1; k <= N - 1; k++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				while(st.hasMoreTokens())
				{
					int i = Integer.parseInt(st.nextToken()) - 1;
					int j = Integer.parseInt(st.nextToken()) - 1;
					grid[i][j] = k;
				}
			}
			int count = 0;
			boolean good = true;
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N && good; j++)
					if(grid[i][j]!=-1)
					{
						count++;
						if(dfs(i,j)!=N)
							good = false;
					}
			if(count!=N)
				good = false;
			if(good)
				sb.append("good\n");
			else
				sb.append("wrong\n");
		}
		System.out.print(sb);
	}
}
