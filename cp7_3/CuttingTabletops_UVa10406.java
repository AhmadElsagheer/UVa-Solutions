package cp7_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CuttingTabletops_UVa10406 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			double d = sc.nextDouble();
			int n = sc.nextInt();
			if(n == 0) break;
			
			Point[] g = new Point[n + 1];
			for(int i = 0; i < n; i++) g[i] = new Point(sc.nextDouble(), sc.nextDouble());
			g[n] = g[0];
			double ans = area(g) - cut(g, d);
			out.format("%.3f\n", ans);
		}
		out.flush();
	}	
	
	static double angle(Point a, Point o, Point b)
	{
		Vector oa = new Vector(o, a), ob = new Vector(o, b);
		return Math.acos(oa.dot(ob) / Math.sqrt(oa.norm2() * ob.norm2()));
	}
	
	static double cut(Point[] g, double d)
	{
		double ret = 0.0;
		
		double[] x = new double[g.length];
		for(int i = 0; i < g.length - 1; i++)
		{
			double angle = angle(i == 0 ? g[g.length - 2] : g[i-1], g[i], g[i+1]);
			if(Math.abs(angle - Math.PI) < EPS)
				continue;
			x[i] = d / Math.tan(angle / 2.0);
		}
		x[g.length - 1] = x[0];
		
		for(int i = 0; i < g.length - 1; i++)
			ret += g[i].dist(g[i+1]) - x[i+1];
	
		return ret * d;
	}
	
	static double area(Point[] g)
	{
		double ret = 0.0;
		for(int i = 0; i < g.length - 1; i++)
			ret += g[i].x * g[i+1].y - g[i+1].x * g[i].y;
		return Math.abs(ret) / 2.0;
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		double dist(Point p) { return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y -y)); }
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point a, Point b) { x = b.x - a.x; y = b.y - a.y; }
		
		double dot(Vector v) { return x * v.x + y * v.y; }
		
		double norm2() { return x * x + y * y; }
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
