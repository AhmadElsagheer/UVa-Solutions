package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class ChestOfDrawers_UVa11420 {

	static BigInteger[][][] memo;
	static int N;
	
	static BigInteger dp(int top, int idx, int rem)
	{
		if(rem < 0)
			return BigInteger.ZERO;
		if(idx==N)
			if(rem==0)
				return BigInteger.ONE;
			else
				return BigInteger.ZERO;
		if(memo[top][idx][rem]!=null)
			return memo[top][idx][rem];
		if(top==1)
			memo[top][idx][rem] = dp(1,idx+1,rem-1).add(dp(0,idx+1,rem));
		else
			memo[top][idx][rem] = dp(1,idx+1,rem).add(dp(0,idx+1,rem));
		return memo[top][idx][rem];
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			StringTokenizer st= new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			if(N<0)
				break;
			memo = new BigInteger[2][N+10][S+10];
			out.println(dp(1,0,S).toString());
		}
		
		out.flush();
	}
}
