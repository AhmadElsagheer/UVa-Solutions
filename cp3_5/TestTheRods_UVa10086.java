package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TestTheRods_UVa10086 {

	static StringBuilder sb = new StringBuilder();
	static int[][][] memo;
	static final int UNCAL = -1, INF = 100000000;
	static int N;
	static int[][][] cost;
	
	public static int dp(int i, int T1, int T2)
	{
		if(T1 < 0 || T2 < 0)
			return INF;
		if(i==N)
			return 0;
		if(memo[i][T1][T2]!=UNCAL)
			return memo[i][T1][T2];
		int min = INF;
		int m = cost[i].length - 1;
		for(int k = 0; k <= m; k++)
		{
			int cur = cost[i][k][0] + cost[i][m-k][1] + dp(i+1,T1-k,T2- (m-k));
			min = Math.min(min, cur);
		}
		return memo[i][T1][T2] = min;
	}
	
	public static void print(int i, int T1, int T2)
	{
		if(i==N)
			return;
		int optimal = dp(i,T1,T2);
		int m = cost[i].length - 1;
		for(int k = 0; k <= m; k++)
		{
			int cur = cost[i][k][0] + cost[i][m-k][1] + dp(i+1,T1-k,T2- (m-k));
			if(optimal == cur)
			{
				sb.append(k).append(i!=N-1?" ":"\n\n");
				print(i+1,T1-k,T2-(m-k));
				return;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T1 = Integer.parseInt(st.nextToken());
			int T2 = Integer.parseInt(st.nextToken());
			if(T1 ==0 && T2==0)
				break;
			N = Integer.parseInt(br.readLine());
			cost = new int[N][][];
			for(int i = 0; i < N; i++)
			{
				int m = Integer.parseInt(br.readLine());
				cost[i] = new int[m+1][2];
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= m; j++)
					cost[i][j][0] = Integer.parseInt(st.nextToken());
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= m; j++)
					cost[i][j][1] = Integer.parseInt(st.nextToken());
			}
			memo = new int[N][T1+1][T2+2];
			for(int i = 0; i < N; i++)
				for(int j = 0; j <= T1; j++)
					Arrays.fill(memo[i][j], UNCAL);
			sb.append(dp(0,T1,T2)).append("\n");
			print(0,T1,T2);
		}
		System.out.print(sb);
	}
}
