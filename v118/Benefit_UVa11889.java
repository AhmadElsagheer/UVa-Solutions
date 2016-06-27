package v118;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Benefit_UVa11889 {

	static ArrayList<Integer> primes;

	static void sieve(int N)
	{
		boolean[] isComposite = new boolean[N];
		primes = new ArrayList<Integer>(N / 10);
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				if(1l * i * i < N)
					for(int j = i * i; j < N; j += i)
						isComposite[j] = true;
			}
	}

	static ArrayList<Factor> primeFactors(int N)
	{
		ArrayList<Factor> factors = new ArrayList<Factor>();
		int idx = 0, p = primes.get(idx);
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

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		sieve(10000);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int A = sc.nextInt(), B = 1, C = sc.nextInt();
			ArrayList<Factor> Fa = primeFactors(A);
			ArrayList<Factor> Fc = primeFactors(C);
			
			boolean possible = true;
			for(int i = 0, j = 0, sz1 = Fa.size(), sz2 = Fc.size(); possible && (i < sz1 || j < sz2); )
				if(i == sz1)
					B *= Fc.get(j++).pow();
				else if(j == sz2)
					possible = false;
				else
				{
					Factor f1 = Fa.get(i), f2 = Fc.get(j);
					if(f1.p == f2.p)
						if(f1.e > f2.e)
							possible = false;
						else
						{
							if(f1.e < f2.e)
								B *= f2.pow();
							++i; ++j;
						}
					else if(f1.p < f2.p)
						possible = false;
					else
					{
						B *= f2.pow();
						++j;
					}	
				}
			if(possible)
				out.println(B);
			else
				out.println("NO SOLUTION");
		}
		out.flush();
		out.close();
	}
	
	static class Factor
	{
		int p, e;
		
		Factor(int a, int b) { p = a; e = b; }
		
		int pow()
		{
			int r = 1;
			while(e > 0)
			{
				if((e & 1) == 1)
					r *= p;
				p *= p;
				e >>= 1;
			}
			return r;
		}
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