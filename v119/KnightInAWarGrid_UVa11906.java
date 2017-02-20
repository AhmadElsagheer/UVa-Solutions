package v119;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class KnightInAWarGrid_UVa11906 {

	static int R, C, N, M;
	static boolean[][] water;
	static int[][] reaching;
	static boolean[][] visited;
	static boolean[][] checked;
	static int[] dx;
	static int[] dy;
	static void dfs(int i, int j)
	{
		visited[i][j] = true;
		for(int k = 0; k < 8; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y) && !checked[x][y])
			{
				reaching[i][j]++;
				checked[x][y] = true;
			}
		}
		for(int k = 0; k < 8; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y))
				checked[x][y] = false;
		}
		for(int k = 0; k < 8; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y) && !visited[x][y])
				dfs(x,y);
		}
	}
	
	static boolean valid(int i, int j)
	{
		if(i < 0 || j < 0 || i >= R || j >= C || water[i][j])
			return false;
		return true;
	}
	public static void main(String[] args) throws  IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int k = 1; k <= TC; k++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(br.readLine());
			water = new boolean[R][C];
			reaching = new int[R][C];
			visited = new boolean[R][C];
			checked = new boolean[R][C];
			while(W-->0)
			{
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				water[x][y] = true;
			}
			dx = new int[]{N,N,-N,-N,M,M,-M,-M};
			dy = new int[]{M,-M,M,-M,N,-N,N,-N};
			int odd = 0, even = 0;
			dfs(0,0);
			for(int i = 0; i < R; i++)
				for(int j = 0; j < C; j++)
					if(!water[i][j] && reaching[i][j]>0)
					{
						if(reaching[i][j]%2==0)
							even++;
						else
							odd++;
					}
			if(reaching[0][0]==0)
				even++;
			sb.append("Case "+k+": "+even+" "+odd+"\n");
		}
		System.out.print(sb);
	}
	
}
