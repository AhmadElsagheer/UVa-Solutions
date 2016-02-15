package cp3_5;


import java.io.*;
import java.util.*;

public class WeddingShopping_UVa11450 {
		
	static int totalMoney;
	static int nGarments;
	static int[][] prices;
	
	static int[][] memo;
	
	public static int buy(int money, int currentGarment)
	{
		if(currentGarment == 0)
			return 0;
		if(memo[money][currentGarment]!=0)
			return memo[money][currentGarment];
		
		int bestPrice = -1;
		for(int tryModel  = 1; tryModel < prices[currentGarment].length; tryModel++)
		{
			int modelPrice = prices[currentGarment][tryModel];
			if(modelPrice>money)
				break;
			int subPrice = buy(money-modelPrice,currentGarment-1);
			if(subPrice==-1)
				break;
			int currentPrice = modelPrice + subPrice;
			if(currentPrice>bestPrice)
				bestPrice = currentPrice;
		}
		memo[money][currentGarment] = bestPrice;
		return bestPrice;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			totalMoney = Integer.parseInt(st.nextToken());
			nGarments = Integer.parseInt(st.nextToken());
			prices = new int[nGarments+1][];
			
			for(int i = 1; i <= nGarments; i++)
			{
				st = new StringTokenizer(br.readLine());
				int kModels = Integer.parseInt(st.nextToken());
				prices[i] = new int[kModels+1];
				for(int j = 1; j <= kModels; j++)
					prices[i][j] = Integer.parseInt(st.nextToken());
				Arrays.sort(prices[i]);
			}
			memo = new int[totalMoney+1][nGarments+1];
			
			
			
			int maxMoney = buy(totalMoney,nGarments);
			if(maxMoney == -1)
				sb.append("no solution\n");
			else
				sb.append(maxMoney+"\n");
			
		}	
		System.out.print(sb);
	}
	

	

}
