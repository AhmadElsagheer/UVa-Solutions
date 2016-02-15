package cp3_5;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class YumCha_UVa11566 {

	static final int UNCAL = -1;
	static final int INF = 1000000;
	
	static int N, T, K;
	static int[] favor;
	static int[] price;
	static int[][][] memo;
	
	public static int dp(int kind, int remMoney, int nDishes)
	{
		if(remMoney<0 || nDishes > 2*(N+1))
			return -INF;
		if(kind==K || remMoney == 0 || nDishes == 2*(N+1))
			return 0;
		
		if(memo[kind][remMoney][nDishes]!=UNCAL)
			return memo[kind][remMoney][nDishes];
		
		int buy1 = favor[kind] + dp(kind+1,remMoney-price[kind],nDishes+1);
		int buy2 = favor[kind]*2 + dp(kind+1,remMoney-price[kind]*2,nDishes+2);
		int skip = dp(kind+1,remMoney,nDishes);
		
		return memo[kind][remMoney][nDishes] = Math.max(skip, Math.max(buy1, buy2));
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(true)
		{
			String x;
			while((x=br.readLine()).equals(""));
				
			StringTokenizer st = new StringTokenizer(x);
			N = Integer.parseInt(st.nextToken());
			int remMoney = Integer.parseInt(st.nextToken())*(N+1);
			T = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			remMoney -= T*(N+1);
			remMoney = (remMoney*10-T*(N+1))/11;
			
			if(N==0)
				break;
			
			memo = new int[K][remMoney+1][N*2+2];
			for(int i = 0; i < K; i++)
				for(int  j =0; j <= remMoney; j++)
					Arrays.fill(memo[i][j], UNCAL);
			
			price = new int[K];
			favor  = new int[K];
			for(int i = 0; i < K; i++)
			{
				st = new StringTokenizer(br.readLine());
				price[i] = Integer.parseInt(st.nextToken());
				for(int j = 0; j <= N; j++)
					favor[i] += Integer.parseInt(st.nextToken());
			}
			
			int maxFlavor = dp(0,remMoney,0);
				
			sb.append(new DecimalFormat("0.00").format(1.0*maxFlavor/(N+1))+"\n");
		}
		System.out.print(sb);
	}
	
	
}
