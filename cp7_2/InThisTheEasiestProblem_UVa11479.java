package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class InThisTheEasiestProblem_UVa11479 {

	static final double EPS = 1e-11;

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int k = 1; k <= tc; k++)
		{
			long x = sc.nextInt(), y = sc.nextInt(), z = sc.nextInt();
			long a = Math.max(Math.max(x, y), z);
			long b = Math.min(Math.min(x, y), z);
			long c = x + y + z - a - b;
			String r;
			if(x <= 0 || y <= 0 || z <= 0 || a >= b + c)
				r = "Invalid";
			else
				if(a == b && a == c)
					r = "Equilateral";
				else
					if(a == b || a == c || b == c)
						r = "Isosceles";
					else
						r = "Scalene";
			out.printf("Case %d: %s\n", k, r);
		}
		out.flush();

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

		public boolean nextEmpty() throws IOException
		{
			st = new StringTokenizer(br.readLine());
			return st.countTokens() == 0;
		}

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
