package v103;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Tiling_UVa10359 {
	
	static final BigInteger TWO = BigInteger.valueOf(2);
	static BigInteger[] memo = new BigInteger[260];
	
	static BigInteger dp(int n)
	{
		if(n == 0)
			return BigInteger.ONE;
		if(memo[n] != null)
			return memo[n];
		BigInteger op1 = BigInteger.ZERO, op2 = dp(n - 1);
		if(n > 1)
			op1 = TWO.multiply(dp(n - 2));
		return memo[n] = op1.add(op2);
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
			out.println(dp(sc.nextInt()));
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
