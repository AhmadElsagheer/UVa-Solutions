package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dollars_UVa147 {

	static long[][] memo;
	static final int UNCAL = -1;
	static int[] value = new int[]{5,10,20,50,100,200,500,1000,2000,5000,10000};
	
	public static long dp(int i, int rem)
	{
		if(rem < 0)
			return 0;
		if(rem == 0)
			return 1;
		if(i==11)
			if(rem == 0)
				return 1;
			else
				return 0;
		if(memo[i][rem]!=UNCAL)
			return memo[i][rem];
		
		return memo[i][rem] = dp(i,rem-value[i]) + dp(i+1, rem);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =  new StringBuilder();
		
		memo = new long[11][30001];
		for(int i = 0; i < 11; i++)
			Arrays.fill(memo[i], UNCAL);
		dp(0,30000);
		while(true)
		{
			String in = br.readLine();
			StringTokenizer st = new StringTokenizer(in,".");
			int cents = Integer.parseInt(st. nextToken()) * 100 + Integer.parseInt(st.nextToken());
			if(cents==0)
				break;
			while(in.length() < 6)
				in = " " + in;
			String out = "" + memo[0][cents];
			while(out.length() < 17)
				out = " " + out;
			sb.append(in+out+"\n");
		}
		System.out.print(sb);
	}
}
