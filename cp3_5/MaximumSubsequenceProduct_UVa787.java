package cp3_5;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class MaximumSubsequenceProduct_UVa787 {

	static BigInteger[] minMemo;
	static BigInteger[] maxMemo;
	static BigInteger[] memo;
	static int N;
	static BigInteger[] value;
	public static BigInteger min(int i)
	{
		if(i==N-1)
			return value[i];
		if(minMemo[i]!=null)
			return minMemo[i];
		BigInteger nextMin = min(i+1);
		BigInteger nextMax = max(i+1);
		BigInteger min = value[i];
		if(!nextMin.equals(BigInteger.ZERO))
			min = min.min(nextMin.multiply(value[i]));
		if(!nextMax.equals(BigInteger.ZERO))
			min = min.min(nextMax.multiply(value[i]));
		return minMemo[i] = min;
	}
	public static BigInteger max(int i)
	{
		if(i==N - 1)
			return value[i];
		if(maxMemo[i]!=null)
			return maxMemo[i];
		BigInteger nextMin = min(i+1);
		BigInteger nextMax = max(i+1);
		BigInteger max = value[i];
		if(!nextMin.equals(BigInteger.ZERO))
			max = max.max(nextMin.multiply(value[i]));
		if(!nextMax.equals(BigInteger.ZERO))
			max = max.max(nextMax.multiply(value[i]));
		return maxMemo[i] = max;
	}
	
	public static BigInteger dp(int i)
	{
		if(i==N - 1)
			return value[i];
		if(memo[i]!=null)
			return memo[i];
		return memo[i] = dp(i+1).max(max(i));
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(br.ready())
		{
			N = 0;
			value = new BigInteger[100];
			st = new StringTokenizer(br.readLine());
			while(true)
			{
				while(!st.hasMoreTokens())
					st = new StringTokenizer(br.readLine());
				
				BigInteger num =  new BigInteger(st.nextToken());
				if(num.equals(BigInteger.valueOf(-999999)))
					break;
				value[N++] = num;
			}

			minMemo = new BigInteger[N];
			maxMemo = new BigInteger[N];
			memo = new BigInteger[N];
			
			out.println(dp(0));
		}
		out.flush();
		out.close();
	}
}
