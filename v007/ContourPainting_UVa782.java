package v007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ContourPainting_UVa782 {

	static char[][] grid;
	static boolean[][] visited;
	static int R;
	static int[] dx = new int[]{0,0,-1,1};
	static int[] dy = new int[]{1,-1,0,0};
	
	static void color(int i, int j)
	{
		visited[i][j] = true;
		for(int k = 0; k < 4; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y) && !visited[x][y])
			{
				if(grid[x][y]!=' ')
				{
					if(grid[x][y]!='#')
						grid[i][j] = '#';
				}
				else
					color(x,y);
			}
		}
	}
	static boolean valid(int i, int j)
	{
		if(i<=-1 || j<=-1 || i>=R || j>=100)
			return false;
		return true;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		while(TC-->0)
		{
			grid = new char[35][100];
			for(int i = 0; i < 35; i++)
				for(int j = 0; j < 100; j++)
					grid[i][j] = ' ';
			R = 0;
			while(true)
			{
				String line = br.readLine();
				for(int i = 0; i < line.length(); i++)
					grid[R][i] = line.charAt(i);
				R++;
				int k = 0;
				while(k <line.length() && line.charAt(k)==' ')
					k++;
				if(k<line.length() && line.charAt(k)=='_')
				{
					R--;break;
				}
				
			}
			visited = new boolean[35][100];
			for(int i = 0; i < R; i++)
				for(int j = 0; j < grid[i].length; j++)
					if(grid[i][j]=='*')
					{
						grid[i][j] = ' ';
						color(i,j);
						break;
					}
			R++;
			for(int i = 0; i < R; i++)
			{
				int end = 99;
				while(end>=0 && (grid[i][end]==' '))end--;
				for(int j = 0; j <= end; j++)
					sb.append(grid[i][j]);
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
}
