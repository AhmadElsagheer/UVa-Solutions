package v101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SuperSale_UVa10130 {

	static final int INF = 100000;
	static final int UNCALCULATED = -1;
	static int[][] memo;
	
	static int N;
	static int[] price;
	static int[] weight;
	
	public static int dp(int item, int remW)
	{
		if(remW<0)
			return -INF;
		if(item==N || remW == 0)
			return 0;
		if(memo[item][remW]!=UNCALCULATED)
			return memo[item][remW];
		int take = price[item] + dp(item + 1, remW - weight[item]);
		int leave = dp(item + 1, remW);
		return memo[item][remW] = Math.max(take, leave);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb  = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			N = Integer.parseInt(br.readLine());
			price = new int[N];
			weight = new int[N];
			for(int i = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				price[i] = Integer.parseInt(st.nextToken());
				weight[i] = Integer.parseInt(st.nextToken());
			}
			int maxSum = 0;
			int G = Integer.parseInt(br.readLine());
			while(G-->0)
			{
				int maxW = Integer.parseInt(br.readLine());
				memo = new int[N][maxW+1];
				for(int i = 0; i < N; i++)
					Arrays.fill(memo[i], UNCALCULATED);
				maxSum += dp(0,maxW);
			}
			sb.append(maxSum+"\n");
		}
		System.out.print(sb);
		
		
	}
}
