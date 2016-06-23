package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CoinChange_UVa674 {

	static final int UNCAL = -1;
	
	static int[] value = new int[]{1,5,10,25,50};
	static long[][] memo;
	
	public static long dp(int coin, int rem)
	{
		if(rem < 0)
			return 0;
		if(coin==5)
			if(rem==0)
				return 1;
			else
				return 0;
		if(memo[coin][rem]!=UNCAL)
			return memo[coin][rem];
		return memo[coin][rem] = dp(coin,rem-value[coin]) + dp(coin+1,rem);
				
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		memo = new long[5][7500];
		for(int i = 0; i < 5; i++)
			Arrays.fill(memo[i], UNCAL);
		
		
		while(br.ready())
		{
			int n = Integer.parseInt(br.readLine());
			long ways = dp(0,n);
			sb.append(ways+"\n");
		}
		System.out.print(sb);
	}
}
