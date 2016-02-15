package cp3_2;

import java.io.*;

import java.util.StringTokenizer;


public class  CoinCollector_UVa11264 {
		

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			int n = Integer.parseInt(br.readLine());
			int[] coins = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				coins[i] = Integer.parseInt(st.nextToken());
			int currentSum = 0;
			int maxCoins = 0;
			for(int i = 0; i < n; i++)
			{
				if(coins[i]<=currentSum)
					continue;
				maxCoins++;
				currentSum += coins[i];
					
			}
			sb.append(maxCoins+"\n");
		}

		System.out.print(sb);
	}
	

}
