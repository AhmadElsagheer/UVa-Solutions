package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class MakingChange_UVa166 {

	static int[][] memo;
	static int[] value = new int[]{5,10,20,50,100,200};
	static int[] coins;
	static final int UNCAL = -1;
	static final int INF = 1000000;
	
	public static int dp(int i, int rem)
	{
		if(rem==0)
			return 0;
		if(i==6 || rem < 0)
			return INF;
		if(memo[i][rem]!=UNCAL)
			return memo[i][rem];
		return memo[i][rem] = Math.min(dp(i+1,rem), 1 + dp(i,rem-value[i]));
				
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		memo = new int[6][2001];
		for(int i = 0; i < 6;i++)
			Arrays.fill(memo[i], UNCAL);
		
		while(true)
		{
			coins = new int[6];
			int sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 6; i++)
			{
				coins[i] = Integer.parseInt(st.nextToken());
				sum += coins[i]*value[i];
			}
			if(sum==0)
				break;
			st = new StringTokenizer(st.nextToken(),".");
			int cents = Integer.parseInt(st.nextToken())*100;
			if(st.hasMoreTokens())
			{
				String x = st.nextToken();
				if(x.length()==1)
					cents += Integer.parseInt(x)*10;
				else
					cents += Integer.parseInt(x);
			}
			memo2 = new int[6][2001];
			for(int i = 0; i < 6; i++)
				Arrays.fill(memo2[i], UNCAL);
		
			int min = INF;
			for(int i = cents; i <= sum && i <= 2000 ; i+=5)
			{
				int x = dp2(0,i);
				int y = dp(0,i-cents);
				
				min = Math.min(min,x+y);
			}
				
			
			
			String output = "" + min;
			while(output.length()<3)
				output = " "+output;
			out.println(output);
		}
		out.flush();
		out.close();
	}
	static int[][] memo2;
	public static int dp2(int i, int rem)
	{
		if(rem==0)
			return 0;
		if(rem < 0 || i  == 6)
			return INF;
		if(memo2[i][rem]!=UNCAL)
			return memo2[i][rem];
		int min = INF;
		for(int j = 0; j <= coins[i]; j++)
			min = Math.min(j + dp2(i+1,rem-j*value[i]), min);
		return memo2[i][rem] = min;
	}
}
