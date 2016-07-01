package v003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PerfectCubes_UVa386 {

	public static void main(String[] args) throws Exception 
	{
		PrintWriter out = new PrintWriter(System.out);

		for(int a = 2; a <= 200; ++a)
			for(int b = 2; b <= a; ++b)
				for(int c = b; b * b * b + c * c * c <= a * a * a; ++c)
				{
					int d3 = a * a * a - (b * b * b + c * c * c);
					int d = (int) Math.pow(d3, 1.0 / 3.0);
					if(d * d * d != d3)
						++d;

					if(d * d * d == d3 && d >= c)
						out.printf("Cube = %d, Triple = (%d,%d,%d)\n", a, b, c, d);
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