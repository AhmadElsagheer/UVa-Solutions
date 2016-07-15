package v002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Safebreaker_UVa296 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int g = sc.nextInt();
			int[] guess = new int[g], a = new int[g], b = new int[g];
			for(int i = 0; i < g; ++i)
			{
				guess[i] = sc.nextInt();
				String s = sc.next();
				a[i] = s.charAt(0) - '0';
				b[i] = s.charAt(2) - '0';
			}
			int ans = -1;
			for(int i = 0; i < 10000; ++i)
				if(matchGuesses(i, guess, a, b))
					if(ans != -1)
					{
						ans = -2; break;
					}
					else
						ans = i;
			if(ans == -1)
				out.println("impossible");
			else if(ans == -2)
				out.println("indeterminate");
			else
				out.printf("%04d\n", ans);
				
		}
		out.flush();
		out.close();
	}
	
	static boolean matchGuesses(int num, int[] guess, int[] a, int[] b)
	{
		for(int i = 0; i < guess.length; ++i)
		{
			int x = 0, y = 0, z = num, w = guess[i], f1[] = new int[10], f2[] = new int[10];
			for(int j = 0; j < 4; ++j, z /= 10, w /= 10)
				if(z%10 == w%10)
					++x;
				else
				{
					f1[z%10]++;
					f2[w%10]++;
				}
			for(int j = 0; j < 10; ++j)
				y += Math.min(f1[j], f2[j]);
			if(x != a[i] || y != b[i])
				return false;
		}
		return true;
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

	}
}