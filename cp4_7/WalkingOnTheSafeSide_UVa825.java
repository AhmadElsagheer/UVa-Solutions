package cp4_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WalkingOnTheSafeSide_UVa825 {

	static final int UNCAL = -1;
	static int[][] memo;
	
	static int W;
	static int N;
	
	public static int dp(int i, int j)
	{
		if(i==W+1 || j == N+1 )
			return 0;

		if(memo[i][j]!=UNCAL)
			return memo[i][j];
		
		int east = dp(i,j+1);
		int	south = dp(i+1, j);
	
		return memo[i][j] = east + south;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			memo = new int[W+1][N+1];
			for(int i = 0; i <= W; i++)
				Arrays.fill(memo[i], UNCAL);
			memo[W][N] = 1;
			for(int i = 1; i <= W; i++)
			{
				st = new StringTokenizer(br.readLine());
				int w = Integer.parseInt(st.nextToken());
				while(st.hasMoreTokens())
				{
					int n = Integer.parseInt(st.nextToken());
					memo[w][n] = 0;
				}
			}
			int ways = dp(1,1);

			sb.append(ways+"\n");
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
