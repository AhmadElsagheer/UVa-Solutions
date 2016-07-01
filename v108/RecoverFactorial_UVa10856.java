package v108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RecoverFactorial_UVa10856 {

	static final int upperBound = (int)1e7 + 2;
	static int[] pf;
	
	static void sieve(int N)
	{
		pf = new int[N];
		for(int i = 2; i < N; ++i)
			if(pf[i] == 0)
			{
				for(int j = i; j < N; j += i)
				{
					int p = 0, k = j;
					while(k % i == 0)
					{
						k /= i;
						++p;
					}
					pf[j] += p;
				}
			}
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		sieve(2740000);					//this number came from the coming while loop, it's = max valid idx
		int[] ans = new int[upperBound];
		Arrays.fill(ans, -1);
		ans[0] = 0;
		int sum = 1, idx = 2;
		while(sum < upperBound)
		{
			ans[sum] = idx;
			sum += pf[++idx];
		}
		
		
		int tc = 1;
		while(true)
		{
			int N = sc.nextInt();
			if(N < 0)
				break;
			
			if(ans[N] == -1)
				out.printf("Case %d: Not possible.\n", tc++);
			else
				out.printf("Case %d: %d!\n", tc++, ans[N]);
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