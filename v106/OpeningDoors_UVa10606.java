package v106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class OpeningDoors_UVa10606 {

	static final BigInteger TWO = BigInteger.valueOf(2);
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			String s = sc.next();
			if(s.equals("0"))
				break;
			BigInteger num = new BigInteger(s);
			BigInteger ans = null, lo = BigInteger.ZERO, hi = num;
			while(lo.compareTo(hi) <= 0)
			{
				BigInteger mid = lo.add(hi).divide(TWO);
				BigInteger mid2 = mid.multiply(mid);
				if(mid2.compareTo(num) <= 0)
				{
					ans = mid2;
					lo = mid.add(BigInteger.ONE);
				}
				else
					hi = mid.subtract(BigInteger.ONE);
			}
			out.println(ans);
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