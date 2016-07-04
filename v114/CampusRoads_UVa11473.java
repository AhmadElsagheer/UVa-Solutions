package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CampusRoads_UVa11473 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; t++)
		{
			int n = sc.nextInt(), trees = sc.nextInt();
			Point[] g = new Point[n];
			for(int i = 0; i < n; i++) g[i] = new Point(sc.nextDouble(), sc.nextDouble());
			
			double d = length(g) / (trees - 1);
			out.format("Road #%d:\n", t);
			double remDistance = 0.0;
			Point p = g[0], q = g[1];
			for(int i = 0, nextPoint = 2; i < trees; i++)
			{
				double dist;
				while(remDistance > (dist = p.dist(q)) + EPS)
				{
					remDistance -= dist;
					p = q;
					q = g[nextPoint++];
				}
				Vector v = new Vector(p, q);
				p = p.translate(v.scale(remDistance / v.norm()));
				out.format("%.2f %.2f\n", p.x, p.y);
				remDistance = d;
			}
			out.println();
		}
		out.flush();
		
	}
	
	static double length(Point[] g)
	{
		double ret = 0.0;
		for(int i = 0; i < g.length - 1; i++)
			ret += g[i].dist(g[i+1]);
		return ret;
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		double dist(Point p)
		{
			return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
		
		public String toString()
		{
			return x + " " + y;
		}
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point p, Point q)
		{
			x = q.x  - p.x; y = q.y - p.y;
		}
		
		Vector(double a, double b) { x = a; y = b; }
		
		Vector scale(double s)
		{
			return new Vector(x * s, y * s);
		}
		
		double norm() { return Math.sqrt(x * x + y * y); }
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
