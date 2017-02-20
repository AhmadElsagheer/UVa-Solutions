package v103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PayThePrice_UVa10313 {

	static final int UNCAL = -1;
	static int L1, L2;
	static long[][][] memo;
	
	public static long dp(int money, int val, int i)
	{
		if(money==0 && i==0)
			return 1;
		if(val > 300 || money < 0 || i==0)
			return 0;
		if(memo[money][val][i]!=UNCAL)
			return memo[money][val][i];
		return memo[money][val][i] = dp(money-val,val,i-1) + dp(money,val+1,i);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		memo = new long[301][301][301];
		for(int i = 0; i <=300; i++)
			for(int j = 0; j <= 300; j++)
				Arrays.fill(memo[i][j], UNCAL);
		
		while(br.ready())
		{
			st = new StringTokenizer(br.readLine());
			int money = Integer.parseInt(st.nextToken());
			int count = st.countTokens();
			L1 = 0; L2 = 300;
			if(count >= 1)
				if(count==1)
					L2 = Integer.parseInt(st.nextToken());
				else
				{
					L1 = Integer.parseInt(st.nextToken());; 
					L2 = Integer.parseInt(st.nextToken());
				}
			if(L2>300)
				L2 = 300;
			if(L1>300)
				L1 = 300;
			long res = 0;
			for(int i = L1; i <= L2;i++)
				res += dp(money,1,i);
	
			out.println(res);	
			

		}
		out.flush();
		out.close();
	}
}
