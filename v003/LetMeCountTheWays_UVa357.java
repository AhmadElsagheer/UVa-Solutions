package v003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//count ways --> long!!!!!!!!!!!!!
public class LetMeCountTheWays_UVa357 {

	static final int UNCAL = -1;
	static long[][] memo;
	static int[] value = new int[]{1,5,10,25,50};
	
	
	public static long dp(int coin, int rem)
	{
		if(rem==0)
			return 1;
		if(rem<0 || coin == 5)
			return 0;
		
		if(memo[coin][rem]!=UNCAL)
			return memo[coin][rem];
		long take = dp(coin,rem-value[coin]);
		long leave = dp(coin+1,rem);
		return memo[coin][rem] = take + leave;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		memo = new long[5][30001];
		for(int i = 0; i < 5; i++)
			Arrays.fill(memo[i], UNCAL);
		
		
		while(br.ready())
		{
			
			int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			
			for(int i = 1; i <= 9; i++)
				dp(0,n/10*i);
			
			long ways = dp(0,n);
			if(ways==1)
				sb.append("There is only 1 way to produce "+n+" cents change.\n");
			else
				sb.append("There are "+ways+" ways to produce "+n+" cents change.\n");
		}
		System.out.print(sb);
		
		
	}
}
