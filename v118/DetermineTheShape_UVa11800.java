package v118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DetermineTheShape_UVa11800 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; t++)
		{
			Point a = new Point(sc.nextInt(), sc.nextInt());
			Point b = new Point(sc.nextInt(), sc.nextInt());
			Point c = new Point(sc.nextInt(), sc.nextInt());
			Point d = new Point(sc.nextInt(), sc.nextInt());
			out.format("Case %d: %s\n", t, new Shape(a, b, c, d).findType());
		}
		out.flush();
		
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		boolean between(Point a, Point b)
		{
			return x <= Math.max(a.x, b.x) + EPS && x + EPS >= Math.min(a.x, b.x) && y <= Math.max(a.y, b.y) + EPS && y + EPS >= Math.min(a.y, b.y);
		}
		
		double dist(Point p)
		{
			return Math.sqrt((p.x -x) * (p.x - x) + (p.y - y) * (p.y - y));
		}
		
		public String toString()
		{
			return x + " " + y;
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
	
	static class Vector
	{
		double x, y;
		
		Vector(Point a, Point b) { x = b.x - a.x; y = b.y - a.y; }
		
		double dot(Vector v)
		{
			return x * v.x + y * v.y;
		}
	}
	static class Shape
	{
		Point a, b, c, d;
		
		Shape(Point x, Point y, Point z, Point l)
		{
			if(!new LineSegment(x, y).intersect(new LineSegment(z, l)))
			{
				if(!new LineSegment(x, l).intersect(new LineSegment(z, y)))
				{				
					a = x; b = y; c = z; d = l;
				}
				else
				{
					a = x; b = y; c = l; d = z;
				}
			}
			else
			{
				a = x; b = l; c = y; d = z;
			}
			 
		}
		
		String findType()
		{
			if(new Line(a, b).parallel(new Line(c, d)))
				if(new Line(b, c).parallel(new Line(d, a)))
				{
					Vector v1 = new Vector(a, b);
					Vector v2 = new Vector(b, c);
					boolean per = Math.abs(v1.dot(v2)) < EPS; 
					
					if(Math.abs(a.dist(b) - b.dist(c)) < EPS)
						if(per)	return "Square";
						else return "Rhombus";
					else
						if(per) return "Rectangle";
					return "Parallelogram";
				}
				else
					return "Trapezium";
			else
				if(new Line(b, c).parallel(new Line(d, a)))
					return "Trapezium";
			return "Ordinary Quadrilateral";
			
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
		
		boolean intersect(LineSegment ls)
		{
			Line l1 = new Line(p, q), l2 = new Line(ls.p, ls.q);
			if(l1.parallel(l2))
			{
				if(l1.same(l2))
					if(p.between(ls.p, ls.q) || q.between(ls.p, ls.q) || ls.p.between(p, q) || ls.q.between(p, q))
						return true;
				return false;
			}
			Point c = l1.intersect(l2);
			if(c.between(p, q) && c.between(ls.p, ls.q))
				return true;
			return false;
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
