package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Continents_UVa11094 {

	static int R, C, counter;
	static char[][] world;
	static boolean[][] visited;
	static int[] dx = new int[]{0,0,-1,1};
	static int[] dy = new int[]{-1,1,0,0};
	static char land;
	static void dfs(int i, int j)
	{
		visited[i][j] = true;
		counter++;
		for(int k = 0; k < 4; k++)
		{
			int x = i + dx[k];
			int y = fixCol(j + dy[k]);
			if(x!=-1 && x != R && !visited[x][y] && world[x][y]==land)
				dfs(x,y);
		}
	}
	
	
	static int fixCol(int j)
	{
		if(j==-1) return C - 1;
		if(j==C) return 0;
		return j;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			world = new char[R][];
			for(int i = 0; i < R; i++)
				world[i] = br.readLine().toCharArray();
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			visited = new boolean[R][C];
			land = world[x][y];
			dfs(x,y);
			int max = 0;
			for(int i = 0; i < R; i++)
				for(int j = 0; j < C; j++)
					if(world[i][j] == land && !visited[i][j])
					{
						counter = 0;
						dfs(i,j);
						max = Math.max(max, counter);
					}
			sb.append(max).append("\n");
			br.readLine();
		}
		System.out.print(sb);
	}
}
