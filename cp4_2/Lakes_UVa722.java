package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Lakes_UVa722 {

	static int[][] grid;
	static boolean[][] visited;
	static int R, C;
	static int[] dx = new int[]{0,0,1,-1};
	static int[] dy = new int[]{-1,1,0,0};
	static int counter;
	public static void dfs(int i, int j)
	{
		visited[i][j] = true;
		counter++;int x,y;
		for(int k = 0; k < 4; k++)
		{
			x = i + dx[k];
			y = j + dy[k];
			if(valid(x,y) && !visited[x][y])
				dfs(x,y);
		}
	}
	
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==R || j==C || grid[i][j]==1)
			return false;
		return true;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		br.readLine();
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			String line;
			R = 0;
			grid = new int[100][100];
			visited = new boolean[100][100];
			while(br.ready() && !(line=br.readLine()).equals(""))
			{
				line = new StringTokenizer(line).nextToken();
				C = line.length();
				
				for(int i = 0; i < C; i++)
					grid[R][i] = line.charAt(i) - '0';
				R++;			
			}
			counter = 0;
			dfs(x,y);
			out.println(counter);
			if(TC!=0)
				out.println();
		}
		out.flush();
		
	}
}
