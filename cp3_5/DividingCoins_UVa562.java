package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DividingCoins_UVa562 {

	static final int INF = 100000; 
	static final int UNCAL = -1;
	
	static int M;
	static int[] value;
	static int[][] memo;
	
	//Top-Down Approach
	public static int dp(int coin, int remSum)
	{
		if(remSum<0)
			return -INF;
		if(coin==M || remSum == 0)
			return 0;
		if(memo[coin][remSum]!=UNCAL)
			return memo[coin][remSum];
		int take = value[coin] + dp(coin+1, remSum - value[coin]);
		int leave = dp(coin+1,remSum);
		return memo[coin][remSum] = Math.max(take, leave);
		
	}
	//Bottom-Up Approach
	static int[] memoBottom;
	
	public static int dp(int sum)
	{
		memoBottom = new int[sum+1];
		for(int i = 1; i <= sum; i++)
		{
			int max = 0;
			for(int j = 0; j < M; j++)
			{
				if(i-value[j]<0)
					continue;
				max = Math.max(max, value[j]+memoBottom[i-value[j]]);
			}
			memoBottom[i] = max;
		}
		return memoBottom[sum];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		while(N-->0)
		{
			M = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			value = new int[M];
			int sum = 0;
			for(int i = 0; i < M; i++)
			{
				value[i] = Integer.parseInt(st.nextToken());
				sum += value[i];
			}
			int maxSum = sum / 2;
			memo = new int[M][maxSum+1];
			for(int i = 0; i < M; i++)
				Arrays.fill(memo[i], UNCAL);
			int sum1 = dp(0,maxSum);
			int sum2 = sum - sum1;
			sb.append(sum2 - sum1+"\n");
//			int sum3 = dp(maxSum);
//			int res = sum - sum3*2;
//			sb.append(res+"\n");
		}
		System.out.print(sb);
	}
}
