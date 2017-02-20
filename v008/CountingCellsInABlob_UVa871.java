package v008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountingCellsInABlob_UVa871 {

	static int[][] grid;
	static int[] dx = new int[]{0,0,-1,-1,-1,1,1,1};
	static int[] dy = new int[]{-1,1,-1,1,0,-1,1,0};
	static int R,C;
	static int counter;
	
	static void dfs(int i, int j)
	{
		counter++;
		grid[i][j] = 0;
		for(int k = 0; k < 8; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y))
				dfs(x,y);
		}
	}
	
	static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==R || j==C || grid[i][j]==0)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		br.readLine();
		while(TC-->0)
		{
			grid = new int[25][25];
			R = 0;
			while(br.ready())
			{
				String line = br.readLine();
				if(line.equals(""))
					break;
				C = line.length();
				for(int i = 0; i < C; i++)
					grid[R][i] = line.charAt(i) - '0';
				R++;
			}
			int max = 0;
			for(int i = 0; i < R; i++)
				for(int j = 0; j < C; j++)
					if(grid[i][j]==1)
					{
						counter = 0;
						dfs(i,j);
						max = Math.max(max, counter);
					}
			sb.append(max+"\n");
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
