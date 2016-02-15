package cp7_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ReservoirLogs_UVa11447 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			Point[] g = new Point[n+1];
			for(int i = 0; i < n; i++) g[i] = new Point(sc.nextInt(), sc.nextInt());
			g[n] = g[0];
			
			double total = area(g) * sc.nextDouble(), v = total * sc.nextDouble() / 100.0;
			
			v -= sc.nextDouble();
			if(v + EPS < 0)
			{
				out.print("Lack of water. ");
				v = 0.0;
			}
			
			v += sc.nextDouble();
			if(v > total + EPS)
			{
				out.print("Excess of water. ");
				v = total;
			}
			out.format("Final percentage: %d%c\n", (long)(v * 100.0/ total),'%');
			
		}
		out.flush();
	}
	
	static double area(Point[] g)
	{
		double ret = 0.0;
		for(int i = 0; i < g.length - 1; i++)
			ret += (double) g[i].x * g[i+1].y - (double) g[i].y * g[i+1].x;
		return ret / 2.0;
	}
	static class Point
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
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
