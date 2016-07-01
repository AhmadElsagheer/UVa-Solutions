package v120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 4c = 4ab - 2a - 2b + 4
 * 4c = (2a - 1) (2b - 1) + 3
 * 4c - 3 = (2a - 1) (2b - 1) = p q => Pairs = numDiv(4c-3)
 */
public class FindSolutions_UVa12005 {

	static final int upperBound = (int)3e7;
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

		while(true)
		{
			long N = sc.nextLong();
			if(N == 0)
				break;
			out.printf("%d %d\n", N, numDiv(4 * N - 3));
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