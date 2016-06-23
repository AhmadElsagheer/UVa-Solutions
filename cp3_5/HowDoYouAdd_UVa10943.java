package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HowDoYouAdd_UVa10943 {

	static final int UNCAL = -1;
	
	static int[][] memo;
	
	public static int dp(int k, int sum)
	{
		if(sum<0)
			return 0;
		if(k==0)
			return sum==0?1:0;
		if(memo[k][sum]!=UNCAL)
			return memo[k][sum];
		int cur = 0;
		for(int i = 0; i <= sum; i++)
			cur = (cur+dp(k-1,sum-i))%1000000;
		return memo[k][sum] = cur;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			if(N==0 && K==0)
				break;
			memo = new int[K+1][N+1];
			for(int i = 0; i <= K; i++)
				Arrays.fill(memo[i], UNCAL);
			sb.append(dp(K,N)+"\n");
		}
		System.out.print(sb);
	}
}
