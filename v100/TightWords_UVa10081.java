package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TightWords_UVa10081 {

	static BigInteger[][][] memo;
	
	static BigInteger dp(int lst, int k, int n) 
	{
		if(n == 0)
			return BigInteger.ONE;
		if(memo[lst][k][n] != null)
			return memo[lst][k][n];
		BigInteger ret = dp(lst, k, n - 1);
		if(lst > 0)
			ret = ret.add(dp(lst - 1, k, n - 1));
		if(lst < k)
			ret = ret.add(dp(lst + 1, k, n - 1));
		return memo[lst][k][n] = ret;
	}
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		memo = new BigInteger[10][10][101];

		while(sc.ready())
		{
			int k = sc.nextInt(), n = sc.nextInt();
			BigInteger ans = BigInteger.ZERO;
			for(int i = 0; i <= k; ++i)
				ans = ans.add(dp(i, k, n - 1));
			BigDecimal num = new BigDecimal(ans);
			BigDecimal den = BigDecimal.valueOf(k + 1).pow(n);
			BigDecimal res = num.multiply(BigDecimal.valueOf(10000000)).divide(den, BigDecimal.ROUND_HALF_UP);
			out.printf("%.5f\n", res.doubleValue() / 100000.0);
		}
		out.flush();
		out.close();
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}