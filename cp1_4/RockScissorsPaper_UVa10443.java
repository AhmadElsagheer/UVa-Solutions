package cp1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RockScissorsPaper_UVa10443 {

	static char[][] grid;
	static int R,C;
	static int[] dx = new int[]{0,0,-1,1};
	static int[] dy = new int[]{-1,1,0,0};
	
	static void wage()
	{
		char[][] f = new char[R][C];
		for(int i = 0; i < R; i++)
			for(int j = 0; j < C; j++)
			{
				char c;
				switch(grid[i][j])
				{
				case 'R':c = 'P';break;
				case 'S':c = 'R';break;
					default:c = 'S';break;
				}
				if(isNeighbor(i,j,c))
					f[i][j] = c;
				else
					f[i][j] = grid[i][j];
			}
		grid = f;
		
	}
	
	static boolean isNeighbor(int i, int j, char c)
	{
		for(int k = 0; k < 4; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y) && grid[x][y] == c)
				return true;
		}
		return false;
	}
	static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==R || j==C)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			grid = new char[R][C];
			for(int i = 0; i < R; i++)
			{
				String line = br.readLine();
				for(int j = 0; j < C; j++)
					grid[i][j] = line.charAt(j);
			}
					
			while(N-->0)
				wage();
			
			for(int i = 0; i < R; i++)
			{
				for(int j = 0; j < C; j++)
					sb.append(grid[i][j]);
				sb.append("\n");
			}
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
