package cp7_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheSultansProblem_UVa11265 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int k = 1;
		while(sc.ready())
		{
			int n = sc.nextInt(), w = sc.nextInt(), h = sc.nextInt();
			Point c = new Point(sc.nextInt(), sc.nextInt());
			Point[] g = new Point[5];
			g[0] = g[4] = new Point(0, 0);
			g[1] = new Point(w, 0);
			g[2] = new Point(w, h);
			g[3] = new Point(0, h);
			while(n-->0)
			{
				Point a = new Point(sc.nextInt(), sc.nextInt());
				Point b = new Point(sc.nextInt(), sc.nextInt());
				if(ccw(a, b, c))
					g = cutPolygon(g, a, b);
				else
					g = cutPolygon(g, b, a);
			}
			out.format("Case #%d: %.3f\n", k++, area(g));
		}
		out.flush();
	}
	
	static boolean ccw(Point a, Point b, Point c)
	{
		return new Vector(a, b).cross(new Vector(a, c)) > 0;
	}
	
	static double area(Point[] g)
	{
		double ret = 0.0;
		for(int i = 0; i < g.length - 1; i++)
			ret += g[i].x * g[i+1].y - g[i].y * g[i+1].x;
		return ret / 2.0;
	}
	
	static Point[] cutPolygon(Point[] g, Point a, Point b)
	{
		Point[] ans = new Point[g.length<<2];
		int size = 0;
		Line l = new Line(a, b);
		for(int i = 0; i < g.length; i++)
		{
			Vector v = new Vector(a, b);
			double left1 = v.cross(new Vector(a, g[i]));
			double left2 = i == g.length - 1 ? 0 : v.cross(new Vector(a, g[i+1]));
			
			if(left1 + EPS >= 0) ans[size++] = g[i];
			if(left1 * left2 + EPS < 0)
				ans[size++] = l.intersect(new Line(g[i], g[i+1]));
		}
		if(size != 0 && ans[0] != ans[size-1])
			ans[size++] = ans[0];
		return Arrays.copyOf(ans, size);
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point a, Point b)
		{
			x = b.x - a.x;
			y = b.y - a.y;
		}
		
		double cross(Vector v)
		{
			return x * v.y - y * v.x;
		}
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		
	}
	
	static class Line
	{
		double a, b, c;
		
		Line(Point p, Point q)
		{
			if(Math.abs(p.x - q.x) < EPS)
			{
				a = 1.0; b = 0.0; c = -p.x;
			}
			else
			{
				a = (q.y - p.y) / (p.x - q.x);
				b = 1.0;
				c = - a * p.x - p.y; 
			}
		}
		
		boolean parallel(Line l)
		{
			return Math.abs(l.a - a) < EPS && Math.abs(l.b - b) < EPS;
		}
		
		Point intersect(Line l)
		{
			if(parallel(l)) return null;
			
			double x = (b * l.c - c * l.b) /(a * l.b - b * l.a);
			double y;
			if(Math.abs(b) > EPS) y = - a * x - c;
			else 				  y = - l.a * x - l.c;
			return new Point(x, y);
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


	}
}
