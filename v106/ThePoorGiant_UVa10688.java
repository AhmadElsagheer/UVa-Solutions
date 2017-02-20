package v106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ThePoorGiant_UVa10688 {

	static final int UNCAL = -1;
	static final int INF = 10000000;
	static int[][] memo;
	static int k;
	
	static int dp(int b, int e)
	{
		if(e-b<=1)
			return 0;
		if(memo[b][e]!=UNCAL)
			return memo[b][e];
		int min = INF;
		for(int i = b; i < e; i++)
			min = Math.min(min,(e-b)*(i+k)+dp(b,i)+dp(i+1,e));
		return memo[b][e] = min;	
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int TC = Integer.parseInt(br.readLine());
		for(int i = 1; i <= TC; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			memo = new int[n+2][n+2];
			for(int j = 1; j <= n; j++)
				Arrays.fill(memo[j], UNCAL);
			out.printf("Case %d: %d\n",i,dp(1,n+1));
		}
		out.flush();
		out.close();
	}
	
	
}


