package v101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class AllYouNeedIsLove_UVa10193 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			BigInteger a = new BigInteger(sc.next(), 2);
			BigInteger b = new BigInteger(sc.next(), 2);
			if(a.gcd(b).compareTo(BigInteger.ONE) > 0)
				out.printf("Pair #%d: All you need is love!\n", t);
			else
				out.printf("Pair #%d: Love is not all you need!\n", t);	
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

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}