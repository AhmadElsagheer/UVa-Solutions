package v007;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class MazeExploration_UVa784 {

	static int[][] grid;
	static int[] dx = new int[]{-1,0,0,1};
	static int[] dy = new int[]{0,-1,1,0};
	
	public static void dfs(int i, int j)
	{
		grid[i][j] = '#';
		for(int k = 0; k < 4; k++)
		{
			if(grid[i+dx[k]][j+dy[k]]==' ')
				dfs(i+dx[k],j+dy[k]);
		}
				
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			grid = new int[30][80];
			for(int i = 0; i < 30; i++)
				Arrays.fill(grid[i], -1);
			String line;int i = 0;int x = 0,y = 0;
			while(true)
			{
				line  = br.readLine();
				if(line.charAt(0)=='_')
					break;
				for(int j = 0; j < line.length(); j++)
				{
					char c = line.charAt(j);
					if(c=='*')
					{
						x = i; y = j;
					}
					grid[i][j] = c;
				}
				i++;
			}
			dfs(x,y);
			for(int k = 0; k < i; k++)
			{
				int j = 0;
				while(grid[k][j]!=-1)
					out.print((char)grid[k][j++]);
				out.println();
			}
			out.println(line);
		}	
		out.flush();
	}
	
	
}
