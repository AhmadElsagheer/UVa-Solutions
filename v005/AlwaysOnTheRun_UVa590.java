package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AlwaysOnTheRun_UVa590 {

	static int[][][] flights;
	static int[][] memo;
	static int N, K;
	static final int UNCAL = -1, INF = 100000000;
	
	static int dp(int city, int day)
	{
		if(day == K)
			return city == N - 1 ? 0: INF;
		if(memo[city][day] != UNCAL)
			return memo[city][day];
		int min = INF;
		for(int i = 0; i < N; i++)
			if(i != city && flights[city][i][day%flights[city][i].length] != 0)
				min = Math.min(min, flights[city][i][day%flights[city][i].length] + dp(i,day + 1));
		return memo[city][day] = min;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			flights = new int[N][N][];
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					if(i != j)
					{
						st = new StringTokenizer(br.readLine());
						int d = Integer.parseInt(st.nextToken());
						flights[i][j] = new int[d];
						for(int k = 0; k < d; k++)
							flights[i][j][k] = Integer.parseInt(st.nextToken());
					}
			memo = new int[N][K+1];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			int min = dp(0,0);
			sb.append("Scenario #"+tc+++"\n");
			if(min==INF)
				sb.append("No flight possible.\n\n");
				else
					sb.append("The best flight costs "+min+".\n\n");
		}
		System.out.print(sb);
	}
}
