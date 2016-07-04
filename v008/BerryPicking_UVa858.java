package v008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BerryPicking_UVa858 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			Point[] g = new Point[n + 1];
			for(int i = 0; i < n; i++) g[i] = new Point(sc.nextDouble(), sc.nextDouble());
			g[n] = g[0];
			int t = sc.nextInt(), x = sc.nextInt();
			double d = compute(findIntersection(g, x));
			if(d + EPS >= t)
				out.print("YES\n");
			else
				out.print("NO\n");
		}
		out.flush();
	}	
	
	static double compute(double[] y)
	{
		Arrays.sort(y);
		
		double ret = 0.0;
		for(int i = 0; i < y.length; i += 2)
			ret += y[i+1] - y[i];
		return ret;
	}
	
	static double[] findIntersection(Point[] g, int x)
	{
		double[] ans = new double[g.length - 1];
		int size = 0;
		for(int i = 0; i < g.length - 1; i++)
		{
			double u = x - g[i].x, v = x - g[i+1].x;
			if(u * v + EPS < 0)
				ans[size++] = (g[i+1].y - g[i].y)  * (x - g[i].x) / (g[i+1].x - g[i].x) + g[i].y;
		}
		
		return Arrays.copyOf(ans, size);
	}

	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
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
