package v117;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class TheSuperPowers_UVa11752 {

	static final double EPS = 1e-9;

	static boolean[] isPrime;

	static void sieve(int N)
	{
		isPrime = new boolean[N];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for(int i = 2; i < N; ++i)
			if(isPrime[i])
				for(int j = i * i; j < N; j += i)
					isPrime[j] = false;
	}
	
	static boolean lessThanLimit(int b, int e)		//returns true if b^e <2^64 (condition comes from log)
	{
		return b + EPS < Math.pow(2, 64.0 / e);
	}
	

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		sieve(64);
		TreeSet<BigInteger> superPowers = new TreeSet<BigInteger>();
		superPowers.add(BigInteger.ONE);
		for(int i = 2; i < 100000; ++i)
			for(int j = 4; j < 64; ++j)
				if(!lessThanLimit(i, j))
					break;
				else if(!isPrime[j])
					superPowers.add(BigInteger.valueOf(i).pow(j));

		for(BigInteger x: superPowers)
				out.println(x);
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
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}

		public boolean ready() throws IOException {return br.ready();}

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}