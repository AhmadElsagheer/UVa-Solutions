package v102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class SimplyEmirp_UVa10235 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			String s;
			StringBuilder sb = new StringBuilder(s = sc.next());
			BigInteger a = new BigInteger(s);
			BigInteger b = new BigInteger(sb.reverse().toString());
			if(!a.isProbablePrime(10))
				out.printf("%s is not prime.\n", s);
			else if(!b.isProbablePrime(10) || a.compareTo(b) == 0)
				out.printf("%s is prime.\n", s);
			else
				out.printf("%s is emirp.\n", s);
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