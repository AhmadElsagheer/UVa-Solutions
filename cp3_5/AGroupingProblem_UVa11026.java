package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AGroupingProblem_UVa11026 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			st = new StringTokenizer(br.readLine());
			long[] val = new long[N];
			for(int i = 0; i < N; i++)
				val[i] = Integer.parseInt(st.nextToken());
			long max = 0;
			long[][] dp = new long[N+1][N+1];
			for(int i = 0; i <= N; i++)
				dp[0][i] = 1;
			for(int k = 1; k <= N; k++)
			{
				long f = 0;
				for(int i = N - 1; i >= 0; i--)
				{
					long cur =  (val[i] * dp[k-1][i+1])%M;
					f = (f + cur)%M;
					dp[k][i] = (cur + dp[k][i+1])%M;
				}
				max = Math.max(f, max);
			}
			
			sb.append(max).append("\n");
		}
		System.out.print(sb);
	}
}
