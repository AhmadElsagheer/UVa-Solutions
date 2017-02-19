package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WalkingOnGrid_UVa10913 {

	static final long INF = 10000000000000000L, UNCAL = 100000000000000000L;
	static long[][][][] memo;
	static int[][] grid;
	static int N;
	static int[] dx = new int[]{0,0,1};
	static int[] dy = new int[]{-1,1,0};
	static long dp(int i, int j, int k, int dir)	//dir 1 for left 0 for right 2 for up
	{
		if(grid[i][j] < 0 && k==0)
			return -INF;
		if(i==N-1 && j==N-1)
			return grid[i][j];
		if(memo[i][j][k][dir]!=UNCAL)
			return memo[i][j][k][dir];
		int nxtK = k;
		if(grid[i][j] < 0)
			nxtK--;
		long max = -INF;
		for(int l = 0; l < 3; l++)
		if(l + dir != 1)
		{
			int x = i + dx[l];
			int y = j + dy[l];
			if(valid(x,y))
				max =Math.max(dp(x,y,nxtK,l) + grid[i][j], max);
		}
		return memo[i][j][k][dir] = max;
		
	}
	
	static boolean valid(int i, int j)
	{
		if(j==-1 || i== N || j == N)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if(N==0 && k==0)
				break;
			memo = new long[N][N][k+1][3];
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					for(int l = 0; l <= k; l++)
						Arrays.fill(memo[i][j][l], UNCAL);
			grid = new int[N][N];
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
					grid[i][j] = Integer.parseInt(st.nextToken());
			}
			long ans = dp(0,0,k,2);
			sb.append("Case "+tc+++": ");
			if(ans < -1000000000000L)
				sb.append("impossible\n");
			else
				sb.append(ans+"\n");
		}
		System.out.print(sb);
	}
	
}
