package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LowestPriceInTown_UVa10980 {

	static final int UNCAL = -1, INF = 50000000;
	static int[][] memo;
	static int[] value;
	static int[] quantity;
	static int N;
	
	static int dp(int idx, int K)
	{
		if(K <= 0)
			return 0;
		if(idx==N)
			return INF;
		if(memo[idx][K]!=UNCAL)
			return memo[idx][K];
		int takeOne = value[idx] + dp(idx, K - quantity[idx]);
		int leaveOffer = dp(idx+1, K);
		return memo[idx][K] = Math.min(takeOne, leaveOffer);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int k = 1;
		while(br.ready())
		{
			out.printf("Case %d:\n",k++);
			StringTokenizer st = new StringTokenizer(br.readLine());
			int unit = doubleToInt(st.nextToken());
			N = Integer.parseInt(st.nextToken()) + 1;
			value = new int[N];quantity = new int[N];
			value[0] = unit;quantity[0] = 1;
			for(int i = 1; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				quantity[i] = Integer.parseInt(st.nextToken());
				value[i] = doubleToInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			memo = new int[N][101];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			while(st.hasMoreTokens())
			{
				int K = Integer.parseInt(st.nextToken());
				out.printf("Buy %d for $%s\n",K,new DecimalFormat("0.00").format(dp(0,K)/100.0));
			}
		}
		out.flush();
	}
	
	
	
	
	
	
	
	static int doubleToInt(String x)
	{
		StringBuilder y = new StringBuilder();
		for(int i = 0; i < x.length(); i++)
			if(x.charAt(i) >= '0' && x.charAt(i) <= '9')
				y.append(x.charAt(i));
		return Integer.parseInt(y.toString());
	}
}
