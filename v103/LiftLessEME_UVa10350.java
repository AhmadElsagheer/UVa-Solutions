package v103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LiftLessEME_UVa10350 {

	static final int INF = 10000000;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(br.ready())
		{
			String head = br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][][] time = new int[n-1][m][m];
			for(int k = 0; k < n - 1; k++)
				for(int i = 0; i < m; i++)
				{
					st = new StringTokenizer(br.readLine());
					for(int j = 0; j < m; j++)
						time[k][i][j] = Integer.parseInt(st.nextToken());
				}
			int[][] dp = new int[n][m];
			for(int i = 1; i < n; i++)
				Arrays.fill(dp[i], INF);
			for(int k = 1; k < n; k++)
				for(int j = 0; j < m; j++)
					for(int i = 0; i < m; i++)
						dp[k][j] = Math.min(dp[k][j], dp[k-1][i] + time[k-1][i][j]);
			int min = INF;
			for(int j = 0; j < m; j++)
				min = Math.min(dp[n-1][j], min);
			min += 2 * (n-1);
			sb.append(head+"\n"+min+"\n");
			
		}
		
		System.out.print(sb);
	}
}
