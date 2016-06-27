package v107;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AgainPrimeNoTime_UVa10780 {

	static final int upperBound = 10000, INF = (int)1e9;
	static ArrayList<Integer> primes;
	
	static void sieve(int N)
	{
		boolean[] isComposite = new boolean[N];
		primes = new ArrayList<Integer>(N / 10);
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				for(int j = i * i; j < N; j += i)
					isComposite[j] = true;
			}
	}
	
	static ArrayList<Factor> primeFactors(int N)
	{
		ArrayList<Factor> factors = new ArrayList<Factor>();

		int idx = 0, p = primes.get(0);
		while(p * p <= N)
		{
			int e = 0;
			while(N % p == 0) { N /= p; ++e; }
			if(e != 0)
				factors.add(new Factor(p, e));
			p = primes.get(++idx);
		}
		if(N != 1)
			factors.add(new Factor(N, 1));
		return factors;
	}
	
	static ArrayList<Factor> merge(ArrayList<Factor> x, ArrayList<Factor> y)
	{
		int sz1 = x.size(), sz2 = y.size();
		ArrayList<Factor> ret = new ArrayList<Factor>(sz1 + sz2 >> 1);
		for(int i = 0, j = 0; i < sz1 || j < sz2; )
			if(i == sz1)
				ret.add(y.get(j++));
			else if(j == sz2)
				ret.add(x.get(i++));
			else
			{
				Factor fx = x.get(i), fy = y.get(j);
				if(fx.p == fy.p)
				{
					ret.add(new Factor(fx.p, fx.e + fy.e)); ++i; ++j;
				}
				else if(fx.p < fy.p)
				{
					ret.add(fx); ++i;
				}
				else
				{
					ret.add(fy); ++j;
				}
			}
		return ret;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		sieve(upperBound);
		ArrayList<Factor>[] primeFactors = new ArrayList[upperBound];
		primeFactors[1] = new ArrayList<Factor>(0);
		for(int i = 2; i < upperBound; ++i)
			primeFactors[i] = merge(primeFactors(i), primeFactors[i-1]);
		
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int M = sc.nextInt(), N = sc.nextInt();
			ArrayList<Factor> fn = primeFactors[N], fm = primeFactors(M);
			int[] powers = new int[Math.max(N, M) + 1];
			for(Factor f: fn)
				powers[f.p] = f.e;
			int max = INF;
			for(Factor f: fm)
				max = Math.min(max, powers[f.p] / f.e);
			if(max == 0)
				out.printf("Case %d:\nImpossible to divide\n", t);
			else
				out.printf("Case %d:\n%d\n", t, max);
		}
		out.flush();
		out.close();
	}
	
	static class Factor
	{
		int p, e;
		
		Factor(int a, int b) { p = a; e = b; }
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