package cp4_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TheSeasonalWar_UVa352 {

	static int N;
	static int[][] grid;
	static int[] dx = new int[]{-1,-1,-1,0,0,1,1,1};
	static int[] dy = new int[]{-1,0,1,-1,1,-1,0,1};
	static boolean[][] visited;
	
	public static void dfs(int i, int j)
	{
		visited[i][j] = true;
		for(int k = 0; k < 8; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y) && !visited[x][y])
				dfs(x,y);
		}
	}
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i == N || j == N || grid[i][j]==0)
			return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(br.ready())
		{
			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];
			for(int i = 0; i < N; i++)
			{
				String line = br.readLine();
				for(int j = 0; j < N; j++)
					grid[i][j] = line.charAt(j)=='0'?0:1;
			}
			int count = 0;
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					if(!visited[i][j] && grid[i][j]==1)
					{
						count++;
						dfs(i,j);
					}
			out.printf("Image number %d contains %d war eagles.\n",tc++,count);
					
		}
		
		out.flush();
		out.close();
	}
}
