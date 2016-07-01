package v113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ADifferentKindOfSorting_UVa11353 {

	static final int upperBound = (int)2e6;
	static int[] pf;
	
	static void sieve(int N)
	{
		pf = new int[N];
		boolean[] isComposite = new boolean[N];

		pf[1] = 1;
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				++pf[i];
				for(int j = i<<1; j < N; j += i)
				{
					isComposite[j] = true;
					int x = j;
					while(x % i == 0)
					{
						++pf[j];
						x /= i;
					}
				}
			}
	}

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
	
		sieve(upperBound + 1);
		Num[] ans = new Num[upperBound];
		
		for(int i = 1; i <= upperBound; ++i)
			ans[i-1] = new Num(i, pf[i]);
		Arrays.sort(ans);
		
		int tc = 1;
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			out.printf("Case %d: %d\n", tc++, ans[N-1].val);
		}
		out.flush();
		out.close();
	}
	
	static class Num implements Comparable<Num>
	{
		int val, pf;
		
		Num(int x, int y) { val = x; pf = y; }
		
		public int compareTo(Num n)
		{
			if(pf != n.pf)
				return pf - n.pf;
			return val - n.val;
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