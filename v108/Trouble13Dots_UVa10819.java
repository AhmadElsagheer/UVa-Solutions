package v108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Trouble13Dots_UVa10819 {

	static final int UNCAL = -1;
	static final int INF = 10000;
	static int[][] memo;
	static boolean risk;
	
	static int enteredBudget;
	static int budget;
	static int N;
	static int[] favor;
	static int[] price;
	
	public static int dp(int item, int money)
	{
		if(money < 0)
			return -INF;
		if(money == 0)
			return 0;
		if(item==N)
		{
			if(risk)
			{
				int bought = enteredBudget - money;
				if(bought<=budget || bought > 2000)
					return 0;
				return -INF;
			}
			return 0;
		}
		if(memo[item][money]!=UNCAL)
			return memo[item][money];
		int take = favor[item] + dp(item+1, money - price[item]);
		int leave = dp(item+1, money);
		
		return memo[item][money] = Math.max(take, leave);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			budget = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			favor = new int[N];
			price = new int[N];
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				price[i] = Integer.parseInt(st.nextToken());
				favor[i] = Integer.parseInt(st.nextToken());
			}
			if(budget>1800)
			{
				enteredBudget = budget + 200;
				risk = true;
			}
			else
			{
				enteredBudget = budget;
				risk = false;
			}
			memo = new int[N][enteredBudget+1];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			int maxF =  dp(0,enteredBudget);
			
				
			sb.append(maxF+"\n");
		}
		
		
		System.out.print(sb);
	}
}
