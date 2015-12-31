package cp8_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class SuperLuckyNumbers_UVa10722 {
	
	static BigInteger[][][] memo;
	static int N, B;
	
	static BigInteger dp(int idx, int pre3)
	{
		if(idx == -1)
			return BigInteger.ONE;
		if(memo[idx][pre3][B] != null)
			return memo[idx][pre3][B];
		BigInteger ans = dp(idx - 1, 1).add(dp(idx - 1, 0).multiply(BigInteger.valueOf(B - 3)));
		if(idx != 0)
			ans = ans.add(dp(idx - 1, 0));
		if(pre3 != 1)
			ans = ans.add(dp(idx - 1, 0));
		return memo[idx][pre3][B] = ans;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		memo = new BigInteger[100][2][129];
		while(true)
		{
			B = sc.nextInt();
			N = sc.nextInt();
			if(B + N == 0)
				break;
			out.println(dp(N - 1, 0));
		}
		out.flush();
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
