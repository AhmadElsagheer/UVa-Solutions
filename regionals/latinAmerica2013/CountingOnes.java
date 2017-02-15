package regionals.latinAmerica2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CountingOnes {
	
	
	static long[][][] memo = new long[64][2][64];
	static int[] limit;
	
	static long dp(int idx, int less, int sum)
	{
		if(idx == -1)
			return sum;
		if(memo[idx][less][sum] != -1)
			return memo[idx][less][sum];
		
		if(less == 1)
			return memo[idx][less][sum] = dp(idx - 1, less, sum) + dp(idx - 1, less, sum + 1);
		if(limit[idx] == 0)
			return memo[idx][less][sum] = dp(idx - 1, less, sum);
		return memo[idx][less][sum] = dp(idx - 1, 1, sum) + dp(idx - 1, 0, sum + 1);
		
	}
	
	
	static long f(long bound)
	{
		limit = new int[64];
		int idx = 0;
		while(bound > 0)
		{
			limit[idx++] = (int)(bound & 1);
			bound >>= 1;
		}
		for(int i = 0; i < idx; ++i)
			for(int j = 0; j < 2; ++j)
				Arrays.fill(memo[i][j], -1);
		return dp(idx - 1, 0, 0);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			long L = Long.parseLong(st.nextToken());
			long U = Long.parseLong(st.nextToken());

			
			out.println(f(U) - f(L - 1));
		}
		out.flush();
		
	}

}



