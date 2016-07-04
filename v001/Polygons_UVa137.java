package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Polygons_UVa137 {
	
	static final double EPS = 1e-11;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			
			Polygon p1, p2;
			Point[] points = new Point[n+1];
			for(int i = n; i > 0; i--) points[i] = new Point(sc.nextDouble(), sc.nextDouble());
			points[0] = points[n];
			p1 = new Polygon(points);
			
			n = sc.nextInt(); points = new Point[n+1];
			for(int i = n; i > 0; i--) points[i] = new Point(sc.nextDouble(), sc.nextDouble());
			points[0] = points[n];
			
			p2 = new Polygon(points);
			
			double ans = p1.area() + p2.area() - p1.intersect(p2).area() * 2;
			out.format("%8.2f",ans);
		}
		out.println();	
		out.flush();
	}
	
	static boolean ccw(Point a, Point b, Point c)
	{
		return new Vector(a, b).cross(new Vector(a, c)) > 0;
	}
	
	static double angle(Point a, Point o, Point b)
	{
		Vector oa = new Vector(o, a), ob = new Vector(o, b);
		return Math.acos(oa.dot(ob) / Math.sqrt(oa.norm2() * ob.norm2()));
	}
	
	static class Point implements Comparable<Point>
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		boolean between(Point a, Point b)
		{
			return x <= Math.max(a.x, b.x) + EPS && x + EPS >= Math.min(a.x, b.x) && y <= Math.max(a.y, b.y) + EPS && y + EPS >= Math.min(a.y, b.y);
		}
		
		public int compareTo(Point p)
		{
			if(Math.abs(x - p.x) > EPS)
				return x > p.x ? 1 : -1;
			if(Math.abs(y - p.y) > EPS)
				return y > p.y ? 1 : -1;
			return 0;
		}
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point a, Point b) { x = b.x - a.x; y = b.y - a.y; }
		
		double cross(Vector v) { return x * v.y - y * v.x; }
		
		double dot(Vector v) { return x * v.x + y * v.y; }
		
		double norm2() { return x * x + y * y; }
	}
	
	static class Polygon
	{
		Point[] g;
		
		Polygon(Point[] p) { g = p; }
		
		double area()
		{
			double ret = 0.0;
			for(int i = 0; i < g.length - 1; i++)
				ret += g[i].x * g[i+1].y - g[i].y * g[i+1].x;
			return ret / 2.0;
		}
		
		boolean inside(Point p)
		{
			double sum = 0.0;
			for(int i = 0; i < g.length - 1; i++)
			{
				double angle = angle(g[i], p, g[i+1]);
				if(ccw(p, g[i], g[i+1]))
					sum += angle;
				else
					sum -= angle;
			}
			return Math.abs(2 * Math.PI - Math.abs(sum)) < EPS;
		}
		
		Polygon intersect(Polygon o)
		{
			ArrayList<Point> ans = new ArrayList<Point>();
			for(int i = 0; i < g.length - 1; i++)
				if(o.inside(g[i]))
					ans.add(g[i]);
			for(int i = 0; i < o.g.length - 1; i++)
				if(inside(o.g[i]))
					ans.add(o.g[i]);
			
			for(int i = 0; i < g.length - 1; i++)
				for(int j = 0; j < o.g.length - 1; j++)
				{
					LineSegment s1 = new LineSegment(g[i], g[i+1]);
					LineSegment s2 = new LineSegment(o.g[j], o.g[j+1]);
					Point p = s1.intersect(s2);
					if(p != null)
						ans.add(p);
				}
			return new Polygon(CH(ans));
		}
		
		static Point[] CH(ArrayList<Point> points)
		{
			Collections.sort(points);
			int n = points.size();
			
			Point[] ans = new Point[n<<1];
			
			int start = 0, size = 0;
			for(int i = 0; i < n; i++)
			{
				Point p = points.get(i);
				while(size - start >= 2 && !ccw(ans[size-2], ans[size-1], p)) size--;
				ans[size++] = p;
			}
			start = --size;
			for(int i = n - 1; i >= 0; i--)
			{
				Point p = points.get(i);
				while(size - start >= 2 && !ccw(ans[size-2], ans[size-1], p)) size--;
				ans[size++] = p;
			}
			if(size < 0)
				size = 0;
			return Arrays.copyOf(ans, size);
		}
	}
	
	static class LineSegment
	{
		Point p, q;
		
		LineSegment(Point a, Point b)
		{
			p = a;
			q = b;
		}
		
		Point intersect(LineSegment ls)
		{
			Line l1 = new Line(p, q), l2 = new Line(ls.p, ls.q);
			if(l1.parallel(l2))
				return null;
			
			Point c = l1.intersect(l2);
			if(c.between(p, q) && c.between(ls.p, ls.q))
				return c;
			return null;
		}
		
	}
	static class Line 
	{
		double a, b, c; 
		
		Line(Point p1, Point p2)
		{
			if(Math.abs(p1.x - p2.x) < EPS) {a = 1.0; b = 0.0; c = -p1.x;}
			else
			{
				a = -(p1.y - p2.y) / (p1.x - p2.x);
				b = 1.0;
				c = -(a * p1.x) - p1.y;
			}
		}
		
		boolean parallel(Line l) {return Math.abs(a - l.a) < EPS && Math.abs(b - l.b) < EPS;}
		
		boolean same(Line l) {	return parallel(l) && Math.abs(c - l.c) < EPS; }
		
		Point intersect(Line l)
		{
			if(this.parallel(l)) return null;
			
			double x = (b * l.c - c * l.b) / (a * l.b - b * l.a);
			double y;
			if(Math.abs(b) > EPS) y = -(a * x + c);
			else				  y = -(l.a * x + l.c);
			
			return new Point(x,y);
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
