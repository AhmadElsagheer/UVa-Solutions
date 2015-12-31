package cp4_2;

import java.util.*;
import java.io.*;

public class OilDeposits_UVa572 {

	static char[][] grid;
	static int R,C;
	static int[] dx = new int[]{-1,-1,-1,0,0,1,1,1};
	static int[] dy = new int[]{-1,0,1,-1,1,-1,0,1};
	public static void dfs(int i, int j)
	{
		grid[i][j] = '*';
		for(int k = 0; k < 8; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y)&&grid[x][y]=='@')
				dfs(x,y);
		}
		
	}
	
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==R || j==C)
			return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(R==0 && C==0)
				break;
			grid = new char[R][];
			for(int i = 0; i < R; i++)
				grid[i] = br.readLine().toCharArray();
			
			int count = 0;
			for(int i = 0; i < R; i++)
				for(int j = 0; j < C; j++)
					if(grid[i][j]=='@')
					{
						count++;
						dfs(i,j);
					}
			out.println(count);
		}
		
		out.flush();
		out.close();
	}
}
