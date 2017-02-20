package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UnidirectionalTSP_UVa116 {

	static long[][] memo;
	static int[][] grid;
	static int[][] next;
	static int N,M;
	static final long UNCAL  = -10000000000000L;
	
	public static long dp(int i, int j)
	{
		
		if(memo[i][j]!=UNCAL)
			return memo[i][j];
	
		int[] rows = new int[]{fixRow(i-1),i,fixRow(i+1)};
		Arrays.sort(rows);
		long up = dp(rows[0],j+1);
		long middle = dp(rows[1],j+1);
		long down = dp(rows[2],j+1);
		
		memo[i][j] = grid[i][j];
		if(up<= middle)
		{
			if(up<=down)
			{
				next[i][j] = rows[0];
				memo[i][j] += up;
			}
			else
			{
				next[i][j] = rows[2];
				memo[i][j] += down;
			}
		}
		else
			if(middle<=down)
			{
				next[i][j] = rows[1];
				memo[i][j] += middle;
			}
			else
			{
				next[i][j] = rows[2];
				memo[i][j] += down;
			}
		return memo[i][j];
	}
	
	public static int fixRow(int x)
	{
		if(x==-1)
			return M - 1;
		if(x==M)
			return 0;
		return x;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			grid = new int[M][N];
			for(int i = 0; i < M; i++)
				for(int j = 0; j < N; j++)
				{
					if(!st.hasMoreTokens())
						st = new StringTokenizer(br.readLine());
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
					
			
			memo = new long[M][N];
			for(int i = 0; i < M; i++)
			{
				Arrays.fill(memo[i], UNCAL);
				memo[i][N-1] = grid[i][N-1];
			}
			next = new int[M][N];
			long min = dp(0,0); int rowInd = 0;
			for(int i = 1; i < M; i++)
			{
				long cur = dp(i,0);
				if(cur<min)
				{
					min = cur;
					rowInd = i;
				}
			}
			for(int i = 0; i < N - 1; i++)
			{
				sb.append(rowInd+1).append(" ");
				rowInd = next[rowInd][i];
			}
			sb.append(rowInd+1).append("\n");
			sb.append(min).append("\n");
				
		}
		System.out.print(sb);
		
	}
}
