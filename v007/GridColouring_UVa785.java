package v007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class GridColouring_UVa785 {

	static char[][] grid;
	static boolean[][] visited;
	static char markingC;
	static int R;
	static int[] dx = new int[]{0,0,1,-1};
	static int[] dy = new int[]{-1,1,0,0};
	
	static void colorZone(int i, int j)
	{
		visited[i][j] = true;
		for(int k = 0; k < 4; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y) && !visited[x][y])
			{
				grid[x][y] = grid[i][j];
				colorZone(x,y);
			}
		}
	}
	
	static boolean valid(int i, int j)
	{
		if(i < 0 || j < 0 || i > R || j > grid[i].length || grid[i][j]==markingC)
			return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			grid = new char[50][];
			R = 0;String line  = "";
			while(br.ready())
			{
				line = br.readLine();
				if(line==null)
					break;
				int k = 0;
				while(k < line.length() && line.charAt(k)==' ')k++;
				if(k < line.length() && line.charAt(k)=='_')break;
				if(k<line.length())
					markingC = line.charAt(k);
				grid[R] = line.toCharArray();
				R++;
			}
			visited = new boolean[R][80];
			for(int i = 0; i < R; i++)
			{
				for(int j = 0; j < grid[i].length; j++)
					if(!visited[i][j] && grid[i][j]!=markingC && grid[i][j]!=' ')
						colorZone(i, j);
			}
			for(int i = 0; i < R; i++)
			{
				for(int j =0; j < grid[i].length; j++)
					sb.append(grid[i][j]);
				sb.append("\n");
			}
			sb.append(line).append("\n");
		}
		
		System.out.print(sb);
	}
}
