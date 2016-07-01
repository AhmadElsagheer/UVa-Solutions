package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * m / x + n / y = 1 / p
 * n / y = 1 / p - m / x = (x - pm) / px
 * y = pnx / (x - pm) = pn(1 + pm / (x - pm) = pn + ppnm/(x-pm)
 * ppnm = (y-pn)(x-pm)
 * Solution = numDiv(ppnm) multiplied by 2 (to count -ve divisors) minus 1 (to exclude x = 0, y = 0)
 */
public class HowManySolutions_UVa10958 {

	static final int upperBound = (int)2e6;
	static ArrayList<Integer> primes;

	static void sieve(int N)
	{
		primes = new ArrayList<Integer>(N / 10);
		boolean[] isComposite = new boolean[N];
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				if(1l * i *i < N)
					for(int j = i * i; j < N; j += i)
						isComposite[j] = true;
					
			}
	}
	
	static int numDiv(long N)
	{
		N = Math.abs(N);
		int ans = 1, idx = 0, p = primes.get(0);
		while(1l * p * p <= N)
		{
			int e = 0;
			while(N % p == 0) { ++e; N /= p; }
			ans *= e + 1;
			p = primes.get(++idx);
		}
		if(N != 1)
			ans <<= 1;
		return ans;
	}

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		sieve(upperBound + 1);

		int tc = 1;
		while(true)
		{
			int m = sc.nextInt(), n = sc.nextInt(), p = sc.nextInt();
			if(m == 0 && n == 0 && p == 0)
				break;
			out.printf("Case %d: %d\n", tc++, numDiv(1l * p * p * n * m) * 2 - 1);
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
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}