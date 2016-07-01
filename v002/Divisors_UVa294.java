package v002;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Divisors_UVa294 {

	static ArrayList<Integer> primes;
	
	static void sieve(int N)
	{
		primes = new ArrayList<Integer>();
		boolean[] isComposite = new boolean[N];
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				for(int j = i * i; j < N; j += i)
					isComposite[j] = true;
			}
	}
	
	static int numDiv(int N)
	{
		int ans = 1, idx = 0, p = primes.get(0);
		while(p * p <= N)
		{
			int e = 0;
			while(N % p == 0) { N /= p; ++e; }
			ans *= (e + 1);
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
		sieve(40000);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int L = sc.nextInt(), R = sc.nextInt(), P = -1, D = 0;
			for(int i = L; i <= R; ++i)
			{
				int d = numDiv(i);
				if(d > D)
				{
					P = i;
					D = d;
				}
			}
			out.printf("Between %d and %d, %d has a maximum of %d divisors.\n", L, R, P, D);
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